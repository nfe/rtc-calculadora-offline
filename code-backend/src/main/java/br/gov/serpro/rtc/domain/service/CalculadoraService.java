/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.roc.Objeto;
import br.gov.serpro.rtc.api.model.roc.ROC;
import br.gov.serpro.rtc.api.model.roc.Tributos;
import br.gov.serpro.rtc.api.model.roc.ValoresTotais;
import br.gov.serpro.rtc.domain.model.entity.ClassificacaoTributaria;
import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;
import br.gov.serpro.rtc.domain.service.calculotributo.CalculoTributoService;
import br.gov.serpro.rtc.domain.service.calculotributo.model.AliquotaImpostoSeletivoModel;
import br.gov.serpro.rtc.domain.service.calculotributo.model.OperacaoModel;
import br.gov.serpro.rtc.domain.service.calculotributo.model.TratamentoClassificacaoModel;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoVinculadaSituacaoTributariaException;
import br.gov.serpro.rtc.domain.service.exception.DesoneracaoNaoInformadaException;
import br.gov.serpro.rtc.domain.service.exception.ImpostoSeletivoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.IncompatibilidadeSuspensaoException;
import br.gov.serpro.rtc.domain.service.exception.ItemDuplicadoException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNbsSimultaneasException;
import br.gov.serpro.rtc.domain.service.exception.NomenclaturaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CalculadoraService {

    private final CalculoTributoService calculadorService;
    private final TratamentoClassificacaoService tratamentoClassificacaoService;
    private final ClassificacaoTributariaService classificacaoTributariaService;
    private final UfService ufService;
    private final AliquotaAdValoremProdutoService aliquotaAdValoremProdutoService;
    private final AliquotaAdRemProdutoService aliquotaAdRemProdutoService;
    private final AliquotaAdValoremServicoService aliquotaAdValoremServicoService;
    private final NcmAplicavelService ncmAplicavelService;
    private final NbsAplicavelService nbsAplicavelService;
    private final NcmService ncmService;
    private final NbsService nbsService;
    private final MunicipioService municipioService;
    private final SituacaoTributariaService situacaoTributariaService;
    
    public ROC calcularTributos(OperacaoInput operacao) {
        // Validar UF e Município
        ufService.validarUf(operacao.getUf());
        municipioService.validarMunicipio(operacao.getMunicipio(), operacao.getUf());
        
        final List<Objeto> detalhes = getDetalhesImposto(operacao);
        
        return ROC.builder()
            .objetos(detalhes)
            .total(ValoresTotais.create(detalhes))
        .build();
    }

    private List<Objeto> getDetalhesImposto(OperacaoInput operacao) {
        final LocalDate data = operacao.getDataHoraEmissao().toLocalDate();
        
        // para checar se existe item duplicado
        Set<Integer> numerosSeen = new HashSet<>();
        for (ItemOperacaoInput item : operacao.getItens()) {
            if (!numerosSeen.add(item.getNumero())) {
                throw new ItemDuplicadoException(item.getNumero().toString());
            }
        }
        
        return operacao.getItens()
                .parallelStream()
                .map(item -> Objeto.builder()
                        .nObj(item.getNumero())
                        .tribCalc(getImposto(operacao, item, data))
                        .build())
                .sorted()
                .toList();
    }

    private Tributos getImposto(OperacaoInput operacao, ItemOperacaoInput item, LocalDate data) {
        // sob demanda da plataforma
        if (item.getQuantidade() == null) {
            item.setQuantidade(BigDecimal.ONE);
        }

        // sob demanda da plataforma
        if (item.getUnidade() == null) {
            item.setUnidade("UN");
        }

        // Validar NCM e NBS
        validarNcmNbs(item.getNcm(), item.getNbs(), data);

        // Validar CST da CBS
        situacaoTributariaService.validarCst(item.getCst(), 2L, data);

        /////////////////////////////////////////////
        // tentar resolver a suspensão aqui
        TratamentoClassificacaoModel tratamentoClassificacao = obterTratamentoClassificacao(item, data);
        /////////////////////////////////////////////

        Long codigoUf = ufService.buscar(operacao.getUf()).getCodigo();

        OperacaoModel operacaoModel = OperacaoModel
                .builder()
                .data(operacao.getDataHoraEmissao())
                .codigoMunicipio(operacao.getMunicipio())
                .codigoUf(codigoUf)
                .ncm(item.getNcm())
                .nbs(item.getNbs())
                .item(item)
                .tratamentoClassificacao(tratamentoClassificacao)
                .build();
        return calculadorService
                .calcular(operacaoModel);
    }
    
    private TratamentoClassificacaoModel obterTratamentoClassificacao(ItemOperacaoInput item, LocalDate data) {

        ClassificacaoTributaria classificacaoTributariaCbsIbs = null;
        ClassificacaoTributaria classificacaoTributariaImpostoSeletivo = null;
        TratamentoClassificacao tratamentoClassificacaoCbsIbs = null;
        TratamentoClassificacao tratamentoClassificacaoImpostoSeletivo = null;
        TratamentoClassificacao tratamentoClassificacaoCbsIbsDesoneracao = null;

        String cst = null;
        String cClassTrib = null;
        Boolean temDesoneracao = false;
        String ncm = item.getNcm();
        String nbs = item.getNbs();

        classificacaoTributariaCbsIbs = classificacaoTributariaService
                .buscarClassificacaoTributariaCbsIbs(item.getCClassTrib(), data);
        validarClassificacaoTributariaCbsIbs(classificacaoTributariaCbsIbs, item.getCst(), ncm, nbs);
        tratamentoClassificacaoCbsIbs = tratamentoClassificacaoService
                .buscarTratamentoClassificacao(classificacaoTributariaCbsIbs.getId(), data);

        cst = item.getCst();
        cClassTrib = item.getCClassTrib();

        if (tratamentoClassificacaoCbsIbs.getTratamentoTributario().isInExigeGrupoDesoneracao()) {
            if (item.getTributacaoRegular() == null) {
                throw new DesoneracaoNaoInformadaException(cClassTrib, cst);
            }
            cst = item.getTributacaoRegular().getCst();
            cClassTrib = item.getTributacaoRegular().getCClassTrib();
            tratamentoClassificacaoCbsIbsDesoneracao = tratamentoClassificacaoCbsIbs;
            classificacaoTributariaCbsIbs = classificacaoTributariaService
                    .buscarClassificacaoTributariaCbsIbs(cClassTrib, data);
            validarClassificacaoTributariaCbsIbs(classificacaoTributariaCbsIbs, cst, ncm, nbs);
            tratamentoClassificacaoCbsIbs = tratamentoClassificacaoService
                    .buscarTratamentoClassificacao(classificacaoTributariaCbsIbs.getId(), data);
            if (tratamentoClassificacaoCbsIbs.getTratamentoTributario().isInIncompativelComSuspensao()) {
                throw new IncompatibilidadeSuspensaoException(cClassTrib, cst);
            }
            temDesoneracao = true;
        } else {
            if (item.getTributacaoRegular() != null) {
                //throw new DesoneracaoInformadaIndevidamenteException(cClassTrib, cst);
            }
        }
        
        if (isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
            ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaCbsIbs.getId(), classificacaoTributariaCbsIbs.getCodigo(), data, "CBS e IBS");
            nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaCbsIbs.getId(), classificacaoTributariaCbsIbs.getCodigo(), data, "CBS e IBS");
        }

        // if ncm not null and nbs null
        if (!isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
            ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaCbsIbs.getId(), classificacaoTributariaCbsIbs.getCodigo(), data, "CBS e IBS");
        }
        
        // if nbs not null and ncm null
        if (!isNullOrEmpty(nbs) && isNullOrEmpty(ncm)) {
            nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaCbsIbs.getId(), classificacaoTributariaCbsIbs.getCodigo(), data, "CBS e IBS");
        }
        
        AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo = analisarAliquotaImpostoSeletivo(item, data);

        if (aliquotaImpostoSeletivo != null) {
            if (item.getImpostoSeletivo() == null) {
                throw new ImpostoSeletivoNaoInformadoException(ncm, data);
            }
            // Validar CST do Imposto Seletivo
            situacaoTributariaService.validarCst(item.getImpostoSeletivo().getCst(), 1L, data);
            classificacaoTributariaImpostoSeletivo = classificacaoTributariaService
                    .buscarClassificacaoTributariaImpostoSeletivo(item.getImpostoSeletivo().getCClassTrib(), data);
            validarClassificacaoTributariaImpostoSeletivo(classificacaoTributariaImpostoSeletivo, item.getImpostoSeletivo().getCst());
            tratamentoClassificacaoImpostoSeletivo = tratamentoClassificacaoService
                    .buscarTratamentoClassificacao(classificacaoTributariaImpostoSeletivo.getId(), data);
        } else if (aliquotaImpostoSeletivo == null && item.getImpostoSeletivo() != null) {
            //throw new ImpostoSeletivoInformadoIndevidamenteException(ncm, data);
        }

        if (aliquotaImpostoSeletivo != null) {
            if (isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
                ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaImpostoSeletivo.getId(),
                        classificacaoTributariaImpostoSeletivo.getCodigo(), data, "Imposto Seletivo");
                nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaImpostoSeletivo.getId(),
                        classificacaoTributariaImpostoSeletivo.getCodigo(), data, "Imposto Seletivo");
            }

            // if ncm not null and nbs null
            if (!isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
                ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaImpostoSeletivo.getId(),
                        classificacaoTributariaImpostoSeletivo.getCodigo(), data, "Imposto Seletivo");
            }

            // if nbs not null and ncm null
            if (!isNullOrEmpty(nbs) && isNullOrEmpty(ncm)) {
                nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaImpostoSeletivo.getId(),
                        classificacaoTributariaImpostoSeletivo.getCodigo(), data, "Imposto Seletivo");
            }
        }

        return TratamentoClassificacaoModel
                .builder()
                .tratamentoClassificacaoCbsIbs(tratamentoClassificacaoCbsIbs)
                .tratamentoClassificacaoImpostoSeletivo(tratamentoClassificacaoImpostoSeletivo)
                .tratamentoClassificacaoCbsIbsDesoneracao(tratamentoClassificacaoCbsIbsDesoneracao)
                .aliquotaImpostoSeletivo(aliquotaImpostoSeletivo)
                .temDesoneracao(temDesoneracao)
                .build();
    }

private AliquotaImpostoSeletivoModel analisarAliquotaImpostoSeletivo(
            ItemOperacaoInput item, LocalDate data) {
        
        final String ncm = item.getNcm();
        final String nbs = item.getNbs();

        // preparação do imposto seletivo
        // sob demanda da plataforma
        if (StringUtils.length(ncm) == 8) { // NCM Completo
                BigDecimal aliquotaAdValorem = aliquotaAdValoremProdutoService
                        .buscarAliquotaAdValorem(ncm, 1L, data);
                
                BigDecimal aliquotaAdRem = aliquotaAdRemProdutoService
                        .buscarAliquotaAdRem(ncm, 1L, data);
                if (aliquotaAdValorem != null || aliquotaAdRem != null) {
                    return AliquotaImpostoSeletivoModel
                            .builder()
                            .aliquotaAdValorem(aliquotaAdValorem)
                            .aliquotaAdRem(aliquotaAdRem)
                            .build();
                }
            } else if (StringUtils.length(nbs) == 9) { // NBS Completo
                BigDecimal aliquotaAdValorem = aliquotaAdValoremServicoService
                        .buscarAliquotaAdValorem(nbs, 1L, data);
                if (aliquotaAdValorem != null) {
                    return AliquotaImpostoSeletivoModel
                            .builder()
                            .aliquotaAdValorem(aliquotaAdValorem)
                            .aliquotaAdRem(null)
                            .build();
                }
            }
        return null;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private void validarClassificacaoTributariaCbsIbs(ClassificacaoTributaria classificacaoTributaria, String cst, String ncm, String nbs) {

        // sob demanda da plataforma
        if (ncm == null && nbs == null) {
            return;
        }

        String nomenclatura = classificacaoTributaria.getNomenclatura();

        if ("NBS".equals(nomenclatura)) {
            if (!isNullOrEmpty(ncm)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.getCodigo() + " só se aplica a NBS (CBS e IBS)");
            }
        } else if ("NCM".equals(nomenclatura)) {
            if (!isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.getCodigo() + " só se aplica a NCM (CBS e IBS)");
            }
        } else if ("CIB".equals(nomenclatura)) {
            // ncm e nbs devem ser vazios
            if (!isNullOrEmpty(ncm) || !isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.getCodigo() + " só se aplica a CIB (CBS e IBS)");
            }
        } else if ("Não possui".equals(nomenclatura)) {
            // ncm e nbs devem ser vazios
            if (!isNullOrEmpty(ncm) || !isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.getCodigo() + " não se aplica a NCM, NBS ou CIB (CBS e IBS)");
            }
        } else if ("CIB ou NCM".equals(nomenclatura)) {
            // aceitar ncm ou vazio; não aceitar nbs
            if (!isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.getCodigo() + " só se aplica a NCM ou CIB (CBS e IBS)");
            }
        } else if ("NBS ou NCM".equals(nomenclatura)) {
            // aceitar ou ncm, ou nbs, ou vazio
        } else if (isNullOrEmpty(nomenclatura)) {
            // aceitar ou ncm, ou nbs, ou vazio
        } else {
            // aceitar ou ncm, ou nbs, ou vazio
        }

        if (!classificacaoTributaria.getSituacaoTributaria().getCodigo().equals(cst)) {
            throw new ClassificacaoTributariaNaoVinculadaSituacaoTributariaException(classificacaoTributaria.getCodigo(), cst, "CBS e IBS");
        }
    }

    private void validarClassificacaoTributariaImpostoSeletivo(
            ClassificacaoTributaria classificacaoTributaria, String cst) {

        if (!classificacaoTributaria.getSituacaoTributaria().getCodigo().equals(cst)) {
            throw new ClassificacaoTributariaNaoVinculadaSituacaoTributariaException(classificacaoTributaria.getCodigo(), cst, "Imposto Seletivo");
        }
    }

    private void validarNcmNbs(String ncm, String nbs, LocalDate data) {
        // sob demanda da plataforma
        // if (isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
        //     throw new NcmNbsNaoInformadasException();
        // }
        if (!isNullOrEmpty(ncm) && !isNullOrEmpty(nbs)) {
            throw new NcmNbsSimultaneasException();
        }
        if (!isNullOrEmpty(ncm) && !ncmService.existeNcm(ncm, data)) {
            throw new NcmNaoEncontradaException(ncm, data);
        }
        if (!isNullOrEmpty(nbs) && !nbsService.existeNbs(nbs, data)) {
            throw new NbsNaoEncontradaException(nbs, data);
        }
    }

}

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
import br.gov.serpro.rtc.api.model.roc.ObjetoDomain;
import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.roc.TributosDomain;
import br.gov.serpro.rtc.api.model.roc.ValoresTotaisDomain;
import br.gov.serpro.rtc.domain.model.dto.AliquotaAdRemDTO;
import br.gov.serpro.rtc.domain.model.dto.ClassificacaoTributariaDTO;
import br.gov.serpro.rtc.domain.model.dto.TratamentoClassificacaoDTO;
import br.gov.serpro.rtc.domain.model.enumeration.TipoWarningDadosSimulados;
import br.gov.serpro.rtc.domain.service.calculotributo.CalculoTributoService;
import br.gov.serpro.rtc.domain.service.calculotributo.model.AliquotaImpostoSeletivoModel;
import br.gov.serpro.rtc.domain.service.calculotributo.model.OperacaoModel;
import br.gov.serpro.rtc.domain.service.calculotributo.model.TratamentoClassificacaoModel;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoVinculadaSituacaoTributariaException;
import br.gov.serpro.rtc.domain.service.exception.ErroGenericoValidacaoException;
import br.gov.serpro.rtc.domain.service.exception.ImpostoSeletivoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.IncompatibilidadeSuspensaoException;
import br.gov.serpro.rtc.domain.service.exception.ItemDuplicadoException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNbsSimultaneasException;
import br.gov.serpro.rtc.domain.service.exception.NomenclaturaException;
import br.gov.serpro.rtc.domain.service.exception.TributacaoRegularNaoInformadaException;
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

    public ROCDomain calcularTributos(OperacaoInput operacao) {
        
        // Validar UF e Município
        if (operacao.getUf() == null) {
            operacao.setUf(municipioService.buscarUfPorMunicipio(operacao.getMunicipio()));
        }
        ufService.validarUf(operacao.getUf());
        municipioService.validarMunicipio(operacao.getMunicipio(), operacao.getUf());
        
        final List<ObjetoDomain> detalhes = getDetalhesImposto(operacao);
        
        return ROCDomain.builder()
            .objetos(detalhes)
            .total(ValoresTotaisDomain.create(detalhes))
        .build();
    }

    private List<ObjetoDomain> getDetalhesImposto(OperacaoInput operacao) {
        final LocalDate data = operacao.getFatoGeradorAplicavel();
        
        // para checar se existe item duplicado
        Set<Integer> numerosSeen = new HashSet<>();
        for (ItemOperacaoInput item : operacao.getItens()) {
            if (!numerosSeen.add(item.getNumero())) {
                throw new ItemDuplicadoException(item.getNumero().toString());
            }
        }
        
        return operacao.getItens()
                .parallelStream()
                .map(item -> ObjetoDomain.builder()
                        .nObj(item.getNumero())
                        .tribCalc(getImposto(operacao, item, data))
                        .build())
                .sorted()
                .toList();
    }

    private TributosDomain getImposto(OperacaoInput operacao, ItemOperacaoInput item, LocalDate data) {
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

        Long codigoUf = ufService.buscar(operacao.getUf());

        OperacaoModel operacaoModel = OperacaoModel
                .builder()
                .data(data)
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

        ClassificacaoTributariaDTO classificacaoTributariaCbsIbs = null;
        ClassificacaoTributariaDTO classificacaoTributariaImpostoSeletivo = null;
        TratamentoClassificacaoDTO tratamentoClassificacaoCbsIbs = null;
        TratamentoClassificacaoDTO tratamentoClassificacaoImpostoSeletivo = null;
        TratamentoClassificacaoDTO tratamentoClassificacaoCbsIbsDesoneracao = null;

        String cst = null;
        String cClassTrib = null;
        Boolean temDesoneracao = false;
        String ncm = item.getNcm();
        String nbs = item.getNbs();

        classificacaoTributariaCbsIbs = classificacaoTributariaService
                .buscarClassificacaoTributariaCbsIbs(item.getCClassTrib(), data);
        validarClassificacaoTributariaCbsIbs(classificacaoTributariaCbsIbs, item.getCst(), ncm, nbs);
        tratamentoClassificacaoCbsIbs = tratamentoClassificacaoService
                .buscarTratamentoClassificacao(classificacaoTributariaCbsIbs.id(), data);

        cst = item.getCst();
        cClassTrib = item.getCClassTrib();

        if (tratamentoClassificacaoCbsIbs.inExigeGrupoDesoneracao()) {
            if (item.getTributacaoRegular() == null) {
                throw new TributacaoRegularNaoInformadaException(cClassTrib, cst);
            }
            cst = item.getTributacaoRegular().getCst();
            cClassTrib = item.getTributacaoRegular().getCClassTrib();
            tratamentoClassificacaoCbsIbsDesoneracao = tratamentoClassificacaoCbsIbs;
            classificacaoTributariaCbsIbs = classificacaoTributariaService
                    .buscarClassificacaoTributariaCbsIbs(cClassTrib, data);
            validarClassificacaoTributariaCbsIbs(classificacaoTributariaCbsIbs, cst, ncm, nbs);
            tratamentoClassificacaoCbsIbs = tratamentoClassificacaoService
                    .buscarTratamentoClassificacao(classificacaoTributariaCbsIbs.id(), data);
            if (tratamentoClassificacaoCbsIbs.inIncompativelComSuspensao()) {
                throw new IncompatibilidadeSuspensaoException(cClassTrib, cst);
            }
            temDesoneracao = true;
        } else {
            if (item.getTributacaoRegular() != null) {
                //throw new DesoneracaoInformadaIndevidamenteException(cClassTrib, cst);
            }
        }
        
        if (isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
            ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaCbsIbs.id(), classificacaoTributariaCbsIbs.codigo(), data, "CBS e IBS");
            nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaCbsIbs.id(), classificacaoTributariaCbsIbs.codigo(), data, "CBS e IBS");
        }

        // if ncm not null and nbs null
        if (!isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
            ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaCbsIbs.id(), classificacaoTributariaCbsIbs.codigo(), data, "CBS e IBS");
        }
        
        // if nbs not null and ncm null
        if (!isNullOrEmpty(nbs) && isNullOrEmpty(ncm)) {
            nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaCbsIbs.id(), classificacaoTributariaCbsIbs.codigo(), data, "CBS e IBS");
        }
        
        AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo = analisarAliquotaImpostoSeletivo(item, data);

        if (aliquotaImpostoSeletivo != null) {
            if (item.getImpostoSeletivo() == null) {
                throw new ImpostoSeletivoNaoInformadoException(ncm != null ? "NCM" : "NBS", ncm != null ? ncm : nbs, data);
            }
            validarQuantidadeEUnidade(item, aliquotaImpostoSeletivo);
            // Validar CST do Imposto Seletivo
            situacaoTributariaService.validarCst(item.getImpostoSeletivo().getCst(), 1L, data);
            classificacaoTributariaImpostoSeletivo = classificacaoTributariaService
                    .buscarClassificacaoTributariaImpostoSeletivo(item.getImpostoSeletivo().getCClassTrib(), data);
            validarClassificacaoTributariaImpostoSeletivo(classificacaoTributariaImpostoSeletivo, item.getImpostoSeletivo().getCst());
            tratamentoClassificacaoImpostoSeletivo = tratamentoClassificacaoService
                    .buscarTratamentoClassificacao(classificacaoTributariaImpostoSeletivo.id(), data);
        } else if (aliquotaImpostoSeletivo == null && item.getImpostoSeletivo() != null) {
            //throw new ImpostoSeletivoInformadoIndevidamenteException(ncm, data);
        }

        if (aliquotaImpostoSeletivo != null) {
            if (isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
                ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaImpostoSeletivo.id(),
                        classificacaoTributariaImpostoSeletivo.codigo(), data, "Imposto Seletivo");
                nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaImpostoSeletivo.id(),
                        classificacaoTributariaImpostoSeletivo.codigo(), data, "Imposto Seletivo");
            }

            // if ncm not null and nbs null
            if (!isNullOrEmpty(ncm) && isNullOrEmpty(nbs)) {
                ncmAplicavelService.validarNcmAplicavel(ncm, classificacaoTributariaImpostoSeletivo.id(),
                        classificacaoTributariaImpostoSeletivo.codigo(), data, "Imposto Seletivo");
            }

            // if nbs not null and ncm null
            if (!isNullOrEmpty(nbs) && isNullOrEmpty(ncm)) {
                nbsAplicavelService.validarNbsAplicavel(nbs, classificacaoTributariaImpostoSeletivo.id(),
                        classificacaoTributariaImpostoSeletivo.codigo(), data, "Imposto Seletivo");
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
    
    protected void validarQuantidadeEUnidade(ItemOperacaoInput item, AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo) {
        if (aliquotaImpostoSeletivo.getAliquotaAdRem() != null) {
            if (item.getQuantidade() == null) { 
                throw new ErroGenericoValidacaoException("A quantidade não foi informada.");
            }
            if (item.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ErroGenericoValidacaoException("A quantidade deve ser maior do que zero.");
            }
            if (item.getUnidade() == null) {
                throw new ErroGenericoValidacaoException("A unidade de medida do item não foi informada.");
            }
            if (!item.getUnidade().equals(aliquotaImpostoSeletivo.getUnidadeMedida())) {
                throw new ErroGenericoValidacaoException("A unidade de medida informada " + item.getUnidade() + " é diferente da unidade de medida da alíquota " + aliquotaImpostoSeletivo.getUnidadeMedida());
            }
        }
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

            AliquotaAdRemDTO aliquotaAdRem = aliquotaAdRemProdutoService
                    .buscarAliquotaAdRem(ncm, 1L, data);
            if (aliquotaAdValorem != null) {
                return AliquotaImpostoSeletivoModel
                        .builder()
                        .aliquotaAdValorem(aliquotaAdValorem)
                        .aliquotaAdRem(aliquotaAdRem != null ? aliquotaAdRem.valor() : null)
                        .unidadeMedida(aliquotaAdRem != null ? aliquotaAdRem.unidadeMedida() : null)
                        .build();
            }
        } else if (StringUtils.length(nbs) == 9) { // NBS Completo
            BigDecimal aliquotaAdValorem = aliquotaAdValoremServicoService
                    .buscarAliquotaAdValorem(nbs, 1L, null, data);
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

    private void validarClassificacaoTributariaCbsIbs(ClassificacaoTributariaDTO classificacaoTributaria, String cst, String ncm, String nbs) {

        // sob demanda da plataforma
        if (ncm == null && nbs == null) {
            return;
        }

        String nomenclatura = classificacaoTributaria.nomenclatura();

        if ("NBS".equals(nomenclatura)) {
            if (!isNullOrEmpty(ncm)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.codigo() + " só se aplica a NBS (CBS e IBS)");
            }
        } else if ("NCM".equals(nomenclatura)) {
            if (!isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.codigo() + " só se aplica a NCM (CBS e IBS)");
            }
        } else if ("CIB".equals(nomenclatura)) {
            // ncm e nbs devem ser vazios
            if (!isNullOrEmpty(ncm) || !isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.codigo() + " só se aplica a CIB (CBS e IBS)");
            }
        } else if ("Não possui".equals(nomenclatura)) {
            // ncm e nbs devem ser vazios
            if (!isNullOrEmpty(ncm) || !isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.codigo() + " não se aplica a NCM, NBS ou CIB (CBS e IBS)");
            }
        } else if ("CIB ou NCM".equals(nomenclatura)) {
            // aceitar ncm ou vazio; não aceitar nbs
            if (!isNullOrEmpty(nbs)) {
                throw new NomenclaturaException("Classificação tributária de código " + classificacaoTributaria.codigo() + " só se aplica a NCM ou CIB (CBS e IBS)");
            }
        } else if ("NBS ou NCM".equals(nomenclatura)) {
            // aceitar ou ncm, ou nbs, ou vazio
        } else if (isNullOrEmpty(nomenclatura)) {
            // aceitar ou ncm, ou nbs, ou vazio
        } else {
            // aceitar ou ncm, ou nbs, ou vazio
        }

        if (!classificacaoTributaria.cst().equals(cst)) {
            throw new ClassificacaoTributariaNaoVinculadaSituacaoTributariaException(classificacaoTributaria.codigo(), cst, "CBS e IBS");
        }
    }

    private void validarClassificacaoTributariaImpostoSeletivo(
            ClassificacaoTributariaDTO classificacaoTributaria, String cst) {

        if (!classificacaoTributaria.cst().equals(cst)) {
            throw new ClassificacaoTributariaNaoVinculadaSituacaoTributariaException(classificacaoTributaria.codigo(), cst, "Imposto Seletivo");
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

        
    public TipoWarningDadosSimulados getWarningDadosSimulados(OperacaoInput operacao) {
        LocalDate dataFatoGerador = operacao.getFatoGeradorAplicavel();
        
        boolean temComprasGovernamentais = false;
        boolean temImpostoSeletivo = false;
        boolean temAliquotasFicticias = false;
        
        for (ItemOperacaoInput item : operacao.getItens()) {
            String cst = item.getCst();
            if ("010".equals(cst) || "220".equals(cst) || "221".equals(cst)) {
                temAliquotasFicticias = true;
            }
            
            if (item.getImpostoSeletivo() != null) {
                temImpostoSeletivo = true;
            }
            
            // TODO: Implementar lógica para detectar compras governamentais
        }
        
        if (temAliquotasFicticias && dataFatoGerador.isAfter(LocalDate.of(2025, 12, 31))) {
            return TipoWarningDadosSimulados.CASO_ALIQUOTAS_FICTICIAS;
        }
        
        if (dataFatoGerador.isBefore(LocalDate.of(2027, 1, 1))) {
            return null;
        }
        
        if (temComprasGovernamentais && temImpostoSeletivo) {
            return TipoWarningDadosSimulados.CASO_COMPRAS_GOVERNAMENTAIS_IS;
        }
        
        if (temImpostoSeletivo) {
            return TipoWarningDadosSimulados.CASO_IMPOSTO_SELETIVO;
        }
        
        if (temComprasGovernamentais) {
            return TipoWarningDadosSimulados.CASO_COMPRAS_GOVERNAMENTAIS;
        }
        
        return TipoWarningDadosSimulados.CASO_GERAL;
    }
    
}

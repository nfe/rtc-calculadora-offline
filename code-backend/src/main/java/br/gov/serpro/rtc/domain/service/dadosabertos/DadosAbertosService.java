/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.dadosabertos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.output.dadosabertos.AliquotaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.ClassificacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.FundamentacaoClassificacaoDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.MunicipioDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.NbsDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.NcmDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.SituacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.UfDadosAbertosOutput;
import br.gov.serpro.rtc.domain.model.entity.AliquotaPadrao;
import br.gov.serpro.rtc.domain.model.entity.AliquotaReferencia;
import br.gov.serpro.rtc.domain.model.entity.ClassificacaoTributaria;
import br.gov.serpro.rtc.domain.model.entity.FundamentacaoLegal;
import br.gov.serpro.rtc.domain.model.entity.SituacaoTributaria;
import br.gov.serpro.rtc.domain.repository.NbsRepository;
import br.gov.serpro.rtc.domain.repository.NcmRepository;
import br.gov.serpro.rtc.domain.repository.SituacaoTributariaRepository;
import br.gov.serpro.rtc.domain.repository.TratamentoClassificacaoRepository;
import br.gov.serpro.rtc.domain.service.AliquotaAdRemProdutoService;
import br.gov.serpro.rtc.domain.service.AliquotaAdValoremProdutoService;
import br.gov.serpro.rtc.domain.service.AliquotaAdValoremServicoService;
import br.gov.serpro.rtc.domain.service.AliquotaPadraoService;
import br.gov.serpro.rtc.domain.service.AliquotaReferenciaService;
import br.gov.serpro.rtc.domain.service.FundamentacaoClassificacaoService;
import br.gov.serpro.rtc.domain.service.MunicipioService;
import br.gov.serpro.rtc.domain.service.TributoSituacaoTributariaService;
import br.gov.serpro.rtc.domain.service.UfService;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoEncontradaException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DadosAbertosService {

    private final UfService ufService;
    private final MunicipioService municipioService;
    private final SituacaoTributariaRepository situacaoTributariaRepository;
    private final TratamentoClassificacaoRepository tratamentoClassificacaoRepository;
    private final NcmRepository ncmRepository;
    private final NbsRepository nbsRepository;
    private final AliquotaAdValoremProdutoService aliquotaAdValoremProdutoService;
    private final AliquotaAdValoremServicoService aliquotaAdValoremServicoService;
    private final AliquotaAdRemProdutoService aliquotaAdRemProdutoService;
    private final FundamentacaoClassificacaoService fundamentacaoClassificacaoService;
    private final TributoSituacaoTributariaService tributoSituacaoTributariaService;

    private final AliquotaReferenciaService aliquotaReferenciaService;
    private final AliquotaPadraoService aliquotaPadraoService;

    // FIXME aqui retornar somente os dados necessarios para a consulta via service
    public List<UfDadosAbertosOutput> consultarUfs() {
        return ufService.consultarTodos().stream()
                .map(uf -> UfDadosAbertosOutput.builder()
                        .sigla(uf.getSigla())
                        .nome(uf.getNome())
                        .codigo(uf.getCodigo())
                        .build())
                .toList();
    }

    // FIXME aqui retornar somente os dados necessarios para a consulta via service
    public List<MunicipioDadosAbertosOutput> consultarMunicipiosPorSiglaUf(String siglaUf) {
        return municipioService.consultarTodosPorSiglaUf(siglaUf).stream()
                .map(m -> MunicipioDadosAbertosOutput
                        .builder()
                        .codigo(m.getCodigo())
                        .nome(m.getNome())
                        .build())
                .toList();
    }

    public List<SituacaoTributariaDadosAbertosOutput> consultarSituacoesTributarias(Long idTributo, LocalDate data) {
        return situacaoTributariaRepository.consultarPorIdTributo(idTributo, data).stream()
                .map(m -> SituacaoTributariaDadosAbertosOutput
                        .builder()
                        .id(m.getId())
                        .codigo(m.getCodigo())
                        .descricao(m.getDescricao())
                        .build())
                .toList();
    }

    public List<ClassificacaoTributariaDadosAbertosOutput> consultarClassificacoesTributariasPorIdSituacaoTributaria(
            Long idSituacaoTributaria, LocalDate data) {
        return tratamentoClassificacaoRepository
                .consultarTratamentoClassificacaoPorIdSituacaoTributaria(idSituacaoTributaria, data).stream()
                .map(m -> ClassificacaoTributariaDadosAbertosOutput
                        .builder()
                        .codigo((String) m[0])
                        .descricao((String) m[1])
                        .tipoAliquota((String) m[2])
                        .nomenclatura((String) m[3])
                        .descricaoTratamentoTributario((String) m[4])
                        .incompativelComSuspensao(m[5] != null && ((Integer) m[5]) != 0)
                        .exigeGrupoDesoneracao(m[6] != null && ((Integer) m[6]) != 0)
                        .possuiPercentualReducao(m[7] != null && ((Integer) m[7]) != 0)
                        .build())
                .toList();
    }

    public NcmDadosAbertosOutput consultarNcm(String ncm, LocalDate data) {

        validarNcm(ncm);

        // Verifica se o NCM existe
        if (!ncmRepository.existeNcm(ncm, data)) {
            throw new NcmNaoEncontradaException(ncm, data);
        }

        BigDecimal aliquotaAdValorem = aliquotaAdValoremProdutoService.buscarAliquotaAdValorem(ncm, 1L, data);
        BigDecimal aliquotaAdRem = aliquotaAdRemProdutoService.buscarAliquotaAdRem(ncm, 1L, data);

        return NcmDadosAbertosOutput
                .builder()
                .tributadoPeloImpostoSeletivo(aliquotaAdValorem != null || aliquotaAdRem != null)
                .aliquotaAdValorem(aliquotaAdValorem)
                .aliquotaAdRem(aliquotaAdRem)
                .capitulo(ncmRepository.buscarDescricaoNcm(ncm, 2, data).orElse(null))
                .posicao(ncmRepository.buscarDescricaoNcm(ncm, 4, data).orElse(null))
                .subposicao(ncmRepository.buscarDescricaoNcm(ncm, 6, data).orElse(null))
                .item(ncmRepository.buscarDescricaoNcm(ncm, 7, data).orElse(null))
                .subitem(ncmRepository.buscarDescricaoNcm(ncm, 8, data).orElse(null))
                .build();
                
    }

    private static void validarNcm(String ncm) {
        if (ncm == null) {
            throw new ValidationException("O campo NCM é obrigatório.");
        }
        if (!ncm.matches("\\d+")) {
            throw new ValidationException("O campo NCM deve conter somente dígitos.");
        }
        // if (ncm.length() != 8) {
        //     throw new ValidationException("O campo NCM deve ter exatamente 8 dígitos.");
        // }
    }

    public NbsDadosAbertosOutput consultarNbs(String nbs, LocalDate data) {

        validarNbs(nbs);

        // Verifica se a NBS existe
        if (!nbsRepository.existeNbs(nbs, data)) {
            throw new NbsNaoEncontradaException(nbs, data);
        }

        BigDecimal aliquotaAdValorem = aliquotaAdValoremServicoService.buscarAliquotaAdValorem(nbs, 1L, data);
        //BigDecimal aliquotaAdRem = aliquotaAdRemProdutoService.buscarAliquotaAdRem(ncm, 1L, data);
        BigDecimal aliquotaAdRem = null;

        return NbsDadosAbertosOutput
                .builder()
                .tributadoPeloImpostoSeletivo(aliquotaAdValorem != null || aliquotaAdRem != null)
                .aliquotaAdValorem(aliquotaAdValorem)
                //.aliquotaAdRem(aliquotaAdRem)
                .capitulo(nbsRepository.buscarDescricaoNbs(nbs, 5, data).orElse(null))
                .posicao(nbsRepository.buscarDescricaoNbs(nbs, 6, data).orElse(null))
                .subposicao1(nbsRepository.buscarDescricaoNbs(nbs, 7, data).orElse(null))
                .subposicao2(nbsRepository.buscarDescricaoNbs(nbs, 8, data).orElse(null))
                .item(nbsRepository.buscarDescricaoNbs(nbs, 9, data).orElse(null))
                .build();
                
    }

    private static void validarNbs(String nbs) {
        if (nbs == null) {
            throw new ValidationException("O campo NBS é obrigatório.");
        }
        if (!nbs.matches("\\d+")) {
            throw new ValidationException("O campo NBS deve conter somente dígitos.");
        }
        // if (nbs.length() != 9) {
        //     throw new ValidationException("O campo NBS deve ter exatamente 9 dígitos.");
        // }
    }

    public List<FundamentacaoClassificacaoDadosAbertosOutput> consultarFundamentacoesLegais(LocalDate data) {
        return fundamentacaoClassificacaoService.buscarTodas(data).stream()
                .map(x -> {
                    final ClassificacaoTributaria classificacao = x.getClassificacaoTributaria();
                    final SituacaoTributaria situacao = classificacao.getSituacaoTributaria();
                    final Long idTributo = tributoSituacaoTributariaService
                            .consultarPorIdSituacaoTributaria(situacao.getId(), data);
                    final FundamentacaoLegal fundamentacao = x.getFundamentacaoLegal();
                    return FundamentacaoClassificacaoDadosAbertosOutput
                            .builder()
                            .codigoClassificacaoTributaria(classificacao.getCodigo())
                            .descricaoClassificacaoTributaria(classificacao.getDescricao())
                            .codigoSituacaoTributaria(situacao.getCodigo())
                            .descricaoSituacaoTributaria(situacao.getDescricao())
                            .conjuntoTributo(idTributo == 1 ? "Imposto Seletivo" : "CBS e IBS")
                            .texto(fundamentacao.getTexto())
                            .textoCurto(fundamentacao.getTextoCurto())
                            .referenciaNormativa(fundamentacao.getReferenciaNormativa())
                            .build();
                })
                .toList();
    }

    public List<ClassificacaoTributariaDadosAbertosOutput> consultarClassificacoesTributariasCbsIbs(LocalDate data) {
        return tratamentoClassificacaoRepository
                .consultarTratamentoClassificacaoCbsIbs(data).stream()
                .map(m -> ClassificacaoTributariaDadosAbertosOutput
                        .builder()
                        .codigo((String) m[0])
                        .descricao((String) m[1])
                        .tipoAliquota((String) m[2])
                        .nomenclatura((String) m[3])
                        .descricaoTratamentoTributario((String) m[4])
                        .incompativelComSuspensao(m[5] != null && ((Integer) m[5]) != 0)
                        .exigeGrupoDesoneracao(m[6] != null && ((Integer) m[6]) != 0)
                        .possuiPercentualReducao(m[7] != null && ((Integer) m[7]) != 0)
                        .indicaApropriacaoCreditoAdquirenteCbs(m[8] != null && ((Integer) m[8]) != 0)
                        .indicaApropriacaoCreditoAdquirenteIbs(m[9] != null && ((Integer) m[9]) != 0)
                        .indicaCreditoPresumidoFornecedor(m[10] != null && ((Integer) m[10]) != 0)
                        .indicaCreditoPresumidoAdquirente(m[11] != null && ((Integer) m[11]) != 0)
                        .creditoOperacaoAntecedente((String) m[12])
                        .percentualReducaoCbs(m[13] != null ? convertToBigDecimal(m[13]) : null)
                        .percentualReducaoIbsUf(m[14] != null ? convertToBigDecimal(m[14]) : null)
                        .percentualReducaoIbsMun(m[15] != null ? convertToBigDecimal(m[15]) : null)
                        .build())
                .toList();
    }

    public List<ClassificacaoTributariaDadosAbertosOutput> consultarClassificacoesTributariasImpostoSeletivo(LocalDate data) {
        return tratamentoClassificacaoRepository
                .consultarTratamentoClassificacaoImpostoSeletivo(data).stream()
                .map(m -> ClassificacaoTributariaDadosAbertosOutput
                        .builder()
                        .codigo((String) m[0])
                        .descricao((String) m[1])
                        .tipoAliquota((String) m[2])
                        .nomenclatura((String) m[3])
                        .descricaoTratamentoTributario((String) m[4])
                        .incompativelComSuspensao(m[5] != null && ((Integer) m[5]) != 0)
                        .exigeGrupoDesoneracao(m[6] != null && ((Integer) m[6]) != 0)
                        .possuiPercentualReducao(m[7] != null && ((Integer) m[7]) != 0)
                        .build())
                .toList();
    }

    public AliquotaDadosAbertosOutput consultarAliquota(Long idTributo, Long codigoUf, Long codigoMunicipio, LocalDate data) {
        AliquotaReferencia aliquotaReferencia = aliquotaReferenciaService.buscar(idTributo, data);
        AliquotaPadrao aliquotaPadrao = null;
        if (idTributo == 2) {
            aliquotaPadrao = aliquotaPadraoService.buscarAliquotaUniao(aliquotaReferencia, data);
        } else if (idTributo == 3) {
            aliquotaPadrao = aliquotaPadraoService.buscarAliquotaUf(aliquotaReferencia, codigoUf, data);
        } else if (idTributo == 4) {
            aliquotaPadrao = aliquotaPadraoService.buscarAliquotaMunicipio(aliquotaReferencia, codigoMunicipio, data);
        }
        return AliquotaDadosAbertosOutput
                .builder()
                .aliquotaReferencia(aliquotaReferencia.getValor())
                .aliquotaPropria(aliquotaPadrao != null ? aliquotaPadrao.getValorAplicavel() : null)
                .formaAplicacao(aliquotaPadrao != null ? aliquotaPadrao.getFormaAplicacao() : null)
                .build();
    }

    /**
     * Converte um Object para BigDecimal, tratando diferentes tipos numéricos
     */
    private BigDecimal convertToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Float) {
            return BigDecimal.valueOf(((Float) value).doubleValue());
        } else if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value);
        } else if (value instanceof Integer) {
            return BigDecimal.valueOf((Integer) value);
        } else if (value instanceof Long) {
            return BigDecimal.valueOf((Long) value);
        } else if (value instanceof String) {
            try {
                return new BigDecimal((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            // Para outros tipos, tenta converter para string e depois para BigDecimal
            try {
                return new BigDecimal(value.toString());
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

}
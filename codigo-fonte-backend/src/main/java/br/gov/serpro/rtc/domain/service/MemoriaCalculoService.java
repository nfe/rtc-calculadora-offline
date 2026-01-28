/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import static org.apache.commons.lang3.StringUtils.defaultString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.output.CbsIbsOutput;
import br.gov.serpro.rtc.api.model.roc.ImpostoSeletivoDomain;
import br.gov.serpro.rtc.domain.model.dto.FundamentacaoClassificacaoDTO;
import br.gov.serpro.rtc.domain.model.dto.TratamentoClassificacaoDTO;
import br.gov.serpro.rtc.domain.model.entity.TratamentoTributario;
import br.gov.serpro.rtc.domain.service.token.TokenizerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoriaCalculoService {

    private final TokenizerService tokenizerService;
    private final FundamentacaoClassificacaoService fundamentacaoClassificacaoService;
    private final TratamentoTributarioService tratamentoService;
    
    @Value("${application.memoriacalculo.enabled}")
    private boolean memoriaCalculoHabilitada;

    public void gerarMemoriaCalculoCbsIbs(TratamentoClassificacaoDTO tratamentoClassificacao, CbsIbsOutput tributo, BigDecimal quantidade, String unidade, LocalDate data) {
        if (memoriaCalculoHabilitada) {
            FundamentacaoClassificacaoDTO fundamentacaoClassificacao = fundamentacaoClassificacaoService
                    .buscar(tratamentoClassificacao.idClassificacaoTributaria(), data);
            
            TratamentoTributario tt = tratamentoService
                    .buscar(tratamentoClassificacao.idTratamentoTributario());
            
            String norma = fundamentacaoClassificacao.textoCurto();
            String tratamento = tt.getDescricao();
            String baseCalculo = tributo.getBaseCalculo().toString();
            String aliquotaAdValorem = tributo.getAliquota().toString();
            
            String aliquotaAdRem = null;
            if (tributo.getGrupoMonofasia() != null && tributo.getGrupoMonofasia().getTributoMonofasico() != null &&
                    tributo.getGrupoMonofasia().getTributoMonofasico().getAliquotaAdRem() != null) {
                aliquotaAdRem = tributo.getGrupoMonofasia().getTributoMonofasico().getAliquotaAdRem().toString();
            } else {
                aliquotaAdRem = "0";
            }
            
            String percentualReducao = null;
            if (tributo.getGrupoReducao() != null && tributo.getGrupoReducao().getPRedAliq() != null) {
                percentualReducao = tributo.getGrupoReducao().getPRedAliq().toString();
            } else {
                percentualReducao = "0";
            }
            
            String aliquotaDesoneracao = null;
            String montanteDesoneracao = null;
            // if (tributo.getTributacaoRegular() != null && tributo.getTributacaoRegular().getAliquotaEfetiva() != null &&
            //     tributo.getTributacaoRegular().getMontanteDesonerado() != null) {
            //     aliquotaDesoneracao = tributo.getTributacaoRegular()
            //         .getAliquotaEfetiva()
            //         .multiply(BigDecimal.valueOf(100))
            //         .setScale(2, RoundingMode.HALF_UP)
            //         .toString();
            //     montanteDesoneracao = tributo.getTributacaoRegular().getMontanteDesonerado().toString();
            // } else {
            //     aliquotaDesoneracao = "0";
            //     montanteDesoneracao = "0";
            // }
            
            String percentualDiferimento = null;
            String valorDiferimento = null;
            if (tributo.getGrupoDiferimento() != null && tributo.getGrupoDiferimento().getPDif() != null &&
                    tributo.getGrupoDiferimento().getVDif() != null) {
                percentualDiferimento = tributo.getGrupoDiferimento().getPDif().toString();
                valorDiferimento = tributo.getGrupoDiferimento().getVDif().toString();
            } else {
                percentualDiferimento = "0";
                valorDiferimento = "0";
            }
            Map<String, String> valores = Map.ofEntries(
                    Map.entry("norma", defaultString(norma)),
                    Map.entry("tratamento", defaultString(tratamento)),
                    Map.entry("base_calculo", defaultString(baseCalculo)),
                    Map.entry("aliquota_ad_valorem", defaultString(aliquotaAdValorem)),
                    Map.entry("aliquota_ad_rem", defaultString(aliquotaAdRem)),
                    Map.entry("quantidade", defaultString(quantidade.toString())),
                    Map.entry("unidade", defaultString(unidade)),
                    
                    Map.entry("percentual_reducao", defaultString(percentualReducao)),
                    Map.entry("percentual_diferimento", defaultString(percentualDiferimento)),
                    Map.entry("valor_diferimento", defaultString(valorDiferimento)),
                    Map.entry("aliquota_desoneracao", defaultString(aliquotaDesoneracao)),
                    Map.entry("montante_desoneracao", defaultString(montanteDesoneracao))
                    );
            String texto = fundamentacaoClassificacao.memoriaCalculo();
            String memoria = StringUtils.isBlank(texto) ? "Texto de memória de cálculo não encontrado" : tokenizerService.substituirPlaceholders(texto, valores);
            tributo.setMemoriaCalculo(memoria);
        }
    }

    public void gerarMemoriaCalculoImpostoSeletivo(TratamentoClassificacaoDTO tratamentoClassificacao, ImpostoSeletivoDomain tributo, BigDecimal quantidade, String unidade, LocalDate data) {
        if (memoriaCalculoHabilitada) {
            FundamentacaoClassificacaoDTO fundamentacaoClassificacao = fundamentacaoClassificacaoService
                    .buscar(tratamentoClassificacao.idClassificacaoTributaria(), data);
            String norma = fundamentacaoClassificacao.textoCurto();
            
            TratamentoTributario tt = tratamentoService
                    .buscar(tratamentoClassificacao.idTratamentoTributario());
            String tratamento = tt.getDescricao();
            
            String baseCalculo = tributo.getVBCIS().toString();
            String aliquotaAdValorem = tributo.getPIS() != null ? tributo.getPIS().toString() : null;
            String aliquotaAdRem = tributo.getPISEspec() != null ? tributo.getPISEspec().toString() : null;
            Map<String, String> valores = Map.ofEntries(
                    Map.entry("norma", defaultString(norma)),
                    Map.entry("tratamento", defaultString(tratamento)),
                    Map.entry("base_calculo", defaultString(baseCalculo)),
                    Map.entry("aliquota_ad_valorem", defaultString(aliquotaAdValorem)),
                    Map.entry("aliquota_ad_rem", defaultString(aliquotaAdRem)),
                    Map.entry("quantidade", Objects.toString(quantidade, "")),
                    Map.entry("unidade", defaultString(unidade)));
            String texto = fundamentacaoClassificacao.memoriaCalculo();
            String memoria = StringUtils.isBlank(texto) ? "Texto de memória de cálculo não encontrado": tokenizerService.substituirPlaceholders(texto, valores);
            tributo.setMemoriaCalculo(memoria);
        }
    }

}
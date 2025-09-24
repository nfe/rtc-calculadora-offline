/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import static org.apache.commons.lang3.StringUtils.defaultString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.output.CbsIbsOutput;
import br.gov.serpro.rtc.api.model.roc.ImpostoSeletivo;
import br.gov.serpro.rtc.domain.model.entity.FundamentacaoClassificacao;
import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;
import br.gov.serpro.rtc.domain.service.token.TokenizerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoriaCalculoService {

    private final TokenizerService tokenizerService;
    private final FundamentacaoClassificacaoService fundamentacaoClassificacaoService;

    public String gerarMemoriaCalculoCbsIbs(TratamentoClassificacao tratamentoClassificacao, CbsIbsOutput tributo, BigDecimal quantidade, String unidade, LocalDate data) {
        FundamentacaoClassificacao fundamentacaoClassificacao = fundamentacaoClassificacaoService
            .buscar(tratamentoClassificacao.getClassificacaoTributaria().getId(), data);
        
        String norma = fundamentacaoClassificacao.getFundamentacaoLegal().getTextoCurto();
        String tratamento = tratamentoClassificacao.getTratamentoTributario().getDescricao();
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
        String texto = fundamentacaoClassificacao.getClassificacaoTributaria().getMemoriaCalculo();
        if (StringUtils.isBlank(texto)) {
            return "Texto de memória de cálculo não encontrado";
        }
        return tokenizerService.substituirPlaceholders(texto, valores);
    }

    public String gerarMemoriaCalculoImpostoSeletivo(TratamentoClassificacao tratamentoClassificacao, ImpostoSeletivo tributo, BigDecimal quantidade, String unidade, LocalDate data) {
        FundamentacaoClassificacao fundamentacaoClassificacao = fundamentacaoClassificacaoService
            .buscar(tratamentoClassificacao.getClassificacaoTributaria().getId(), data);
        String norma = fundamentacaoClassificacao.getFundamentacaoLegal().getTextoCurto();
        String tratamento = tratamentoClassificacao.getTratamentoTributario().getDescricao();
        String baseCalculo = tributo.getVBCIS().toString();
        String aliquotaAdValorem = tributo.getPIS() != null ? tributo.getPIS().toString() : null;
        String aliquotaAdRem = tributo.getPISEspec() != null ? tributo.getPISEspec().toString() : null;
        Map<String, String> valores = Map.ofEntries(
            Map.entry("norma", defaultString(norma)),
            Map.entry("tratamento", defaultString(tratamento)),
            Map.entry("base_calculo", defaultString(baseCalculo)),
            Map.entry("aliquota_ad_valorem", defaultString(aliquotaAdValorem)),
            Map.entry("aliquota_ad_rem", defaultString(aliquotaAdRem)),
            Map.entry("quantidade", defaultString(quantidade.toString())),
            Map.entry("unidade", defaultString(unidade)));
        String texto = fundamentacaoClassificacao.getClassificacaoTributaria().getMemoriaCalculo();
        if (StringUtils.isBlank(texto)) {
            return "Texto de memória de cálculo não encontrado";
        }
        return tokenizerService.substituirPlaceholders(texto, valores);
    }

}
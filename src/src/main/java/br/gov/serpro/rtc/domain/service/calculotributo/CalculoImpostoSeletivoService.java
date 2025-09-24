/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo;

import static br.gov.serpro.rtc.core.util.CalculadoraUtils.CEM;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.AJUSTE;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.ALIQUOTA;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.ALIQUOTA_AD_REM;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.ALIQUOTA_AD_VALOREM;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.ALIQUOTA_EFETIVA;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.ALIQUOTA_PADRAO_OU_REFERENCIA;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.ALIQUOTA_REFERENCIA;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.BASE_CALCULO;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.BASE_CALCULO_INFORMADA;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.IMPOSTO_SELETIVO_CALCULADO;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.IMPOSTO_SELETIVO_INFORMADO;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.PERCENTUAL_REDUCAO;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.QUANTIDADE;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.REDUTOR;
import static br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao.TRIBUTO_CALCULADO;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Map.ofEntries;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import br.gov.serpro.rtc.api.model.roc.ImpostoSeletivo;
import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;
import br.gov.serpro.rtc.domain.model.entity.TratamentoTributario;
import br.gov.serpro.rtc.domain.service.calculotributo.domain.VariavelExpressao;
import br.gov.serpro.rtc.domain.service.calculotributo.model.AliquotaImpostoSeletivoModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CalculoImpostoSeletivoService {
    
    private final AvaliadorExpressaoAritmetica avaliador;

    public ImpostoSeletivo calcularImpostoSeletivo(
        Long idTributo,
        ItemOperacaoInput item,
        TratamentoClassificacao tratamentoClassificacao,
        AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo,
        LocalDate data) {

        TratamentoTributario tratamentoTributario = tratamentoClassificacao
                .getTratamentoTributario();

        String expressaoAliquota = tratamentoTributario.getExpressaoAliquota();
        String expressaoBaseCalculo = tratamentoTributario.getExpressaoBaseCalculo();
        String expressaoAliquotaEfetiva = tratamentoTributario.getExpressaoAliquotaEfetiva();
        String expressaoTributoCalculado = tratamentoTributario.getExpressaoTributoCalculado();
        String expressaoTributoDevido = tratamentoTributario.getExpressaoTributoDevido();
        // String expressaoPercentualDiferimento = tratamentoTributario.getExpressaoPercentualDiferimento();
        // String expressaoValorDiferimento = tratamentoTributario.getExpressaoValorDiferimento();
        // Boolean incompativelComSuspensao = tratamentoTributario.isInIncompativelComSuspensao();
        // Boolean incompativelComSuspensaoDiferimento = tratamentoTributario.isInExigeGrupoDesoneracao();
        // Boolean possuiPercentualReducao = tratamentoTributario.isInPossuiPercentualReducao();
        // Boolean possuiAjuste = tratamentoTributario.isInPossuiAjuste();
        // Boolean possuiRedutor = tratamentoTributario.isInPossuiRedutor();

        BigDecimal valorPercentualReducao = null;
        BigDecimal valorAjuste = null;
        BigDecimal valorRedutor = null;

        String cst = item.getImpostoSeletivo().getCst();
        String cClassTrib = item.getImpostoSeletivo().getCClassTrib();
        String unidade = item.getImpostoSeletivo().getUnidade();
        String memoriaCalculo = null;
        BigDecimal baseCalculoInformada = item.getImpostoSeletivo().getBaseCalculo();
        BigDecimal quantidade = item.getImpostoSeletivo().getQuantidade();
        BigDecimal impostoSeletivoInformado = item.getImpostoSeletivo().getValorImpostoSeletivoInformado();
        BigDecimal impostoSeletivoCalculado = ZERO;

        /////////////////////////////////
        BigDecimal valorAliquotaPadraoOuReferencia = null;
        BigDecimal valorAliquotaReferencia = null;
        BigDecimal valorAliquotaAdValorem = null;
        BigDecimal valorAliquotaAdRem = null;
        BigDecimal valorAliquota = null;
        /////////////////////////////////

        valorAliquotaAdValorem = aliquotaImpostoSeletivo.getAliquotaAdValorem();
        if (valorAliquotaAdValorem != null) {
            valorAliquotaAdValorem = valorAliquotaAdValorem.divide(CEM).setScale(8, HALF_UP);
        }
        valorAliquotaAdRem = aliquotaImpostoSeletivo.getAliquotaAdRem();

        /////////////////////////////
        BigDecimal resultadoAliquota = null;
        BigDecimal resultadoAliquotaEfetiva = null;
        BigDecimal resultadoBaseCalculo = null;
        BigDecimal resultadoTributoCalculado = null;
        // novos
        BigDecimal resultadoTributoDevido = null;
        /////////////////////////////

        // if (!isNullOrEmpty(expressaoAliquota)) {
        //     var variaveis0 = ofEntries(
        //             entry(ALIQUOTA, valorAliquota)),
        //             entry(QUANTIDADE, quantidade)),
        //             entry(PERCENTUAL_REDUCAO, valorPercentualReducao)),
        //             entry(ALIQUOTA_PADRAO_OU_REFERENCIA, valorAliquotaPadraoOuReferencia)),
        //             entry(ALIQUOTA_REFERENCIA, valorAliquotaReferencia)),
        //             entry(ALIQUOTA_AD_VALOREM, valorAliquotaAdValorem)),
        //             entry(ALIQUOTA_AD_REM, valorAliquotaAdRem)),
        //             entry(AJUSTE, valorAjuste)),
        //             entry(REDUTOR, valorRedutor)));
        //     resultadoAliquota = avaliador.evaluate(expressaoAliquota, variaveis0, 4);
        // }

        // if (!isNullOrEmpty(expressaoAliquotaEfetiva)) {
        //     var variaveis1 = ofEntries(
        //             entry(QUANTIDADE, quantidade),
        //             entry(ALIQUOTA, resultadoAliquota),
        //             entry(PERCENTUAL_REDUCAO, valorPercentualReducao),
        //             entry(ALIQUOTA_PADRAO_OU_REFERENCIA, valorAliquotaPadraoOuReferencia),
        //             entry(ALIQUOTA_REFERENCIA, valorAliquotaReferencia),
        //             entry(ALIQUOTA_AD_VALOREM, valorAliquotaAdValorem),
        //             entry(ALIQUOTA_AD_REM, valorAliquotaAdRem),
        //             entry(AJUSTE, valorAjuste),
        //             entry(REDUTOR, valorRedutor));
        //     resultadoAliquotaEfetiva = avaliador.evaluate(expressaoAliquotaEfetiva, variaveis1, 4);
        // }

        var variaveis2 = ofEntries(
                entry(QUANTIDADE, quantidade),
                entry(ALIQUOTA, resultadoAliquota),
                entry(ALIQUOTA_EFETIVA, resultadoAliquotaEfetiva),
                entry(BASE_CALCULO_INFORMADA, baseCalculoInformada),
                entry(IMPOSTO_SELETIVO_INFORMADO, impostoSeletivoInformado),
                entry(IMPOSTO_SELETIVO_CALCULADO, impostoSeletivoCalculado),
                entry(PERCENTUAL_REDUCAO, valorPercentualReducao),
                entry(ALIQUOTA_PADRAO_OU_REFERENCIA, valorAliquotaPadraoOuReferencia),
                entry(ALIQUOTA_REFERENCIA, valorAliquotaReferencia),
                entry(ALIQUOTA_AD_VALOREM, valorAliquotaAdValorem),
                entry(ALIQUOTA_AD_REM, valorAliquotaAdRem),
                entry(AJUSTE, valorAjuste),
                entry(REDUTOR, valorRedutor));

        resultadoBaseCalculo = avaliador.evaluate(expressaoBaseCalculo, variaveis2, 2);

        var variaveis3 = ofEntries(
                entry(QUANTIDADE, quantidade),
                entry(BASE_CALCULO, resultadoBaseCalculo),
                entry(ALIQUOTA, resultadoAliquota),
                entry(ALIQUOTA_EFETIVA, resultadoAliquotaEfetiva),
                entry(BASE_CALCULO_INFORMADA, baseCalculoInformada),
                entry(IMPOSTO_SELETIVO_INFORMADO, impostoSeletivoInformado),
                entry(IMPOSTO_SELETIVO_CALCULADO, impostoSeletivoCalculado),
                entry(PERCENTUAL_REDUCAO, valorPercentualReducao),
                entry(ALIQUOTA_PADRAO_OU_REFERENCIA, valorAliquotaPadraoOuReferencia),
                entry(ALIQUOTA_REFERENCIA, valorAliquotaReferencia),
                entry(ALIQUOTA_AD_VALOREM, valorAliquotaAdValorem),
                entry(ALIQUOTA_AD_REM, valorAliquotaAdRem),
                entry(AJUSTE, valorAjuste),
                entry(REDUTOR, valorRedutor));

        resultadoTributoCalculado = avaliador.evaluate(expressaoTributoCalculado, variaveis3, 2);

        if (isNotBlank(expressaoTributoDevido)) {

            var variaveis4 = ofEntries(
                    entry(TRIBUTO_CALCULADO, resultadoTributoCalculado),
                    entry(QUANTIDADE, quantidade),
                    entry(BASE_CALCULO, resultadoBaseCalculo),
                    entry(ALIQUOTA, resultadoAliquota),
                    entry(ALIQUOTA_EFETIVA, resultadoAliquotaEfetiva),
                    entry(BASE_CALCULO_INFORMADA, baseCalculoInformada),
                    entry(IMPOSTO_SELETIVO_INFORMADO, impostoSeletivoInformado),
                    entry(IMPOSTO_SELETIVO_CALCULADO, impostoSeletivoCalculado),
                    entry(PERCENTUAL_REDUCAO, valorPercentualReducao),
                    entry(ALIQUOTA_PADRAO_OU_REFERENCIA, valorAliquotaPadraoOuReferencia),
                    entry(ALIQUOTA_REFERENCIA, valorAliquotaReferencia),
                    entry(ALIQUOTA_AD_VALOREM, valorAliquotaAdValorem),
                    entry(ALIQUOTA_AD_REM, valorAliquotaAdRem),
                    entry(AJUSTE, valorAjuste),
                    entry(REDUTOR, valorRedutor));

            resultadoTributoDevido = avaliador.evaluate(expressaoTributoDevido, variaveis4, 2);
        }

        return ImpostoSeletivo
                .builder()
                .CSTIS(Integer.valueOf(cst))
                .cClassTribIS(cClassTrib)
                .qTrib(quantidade)
                .uTrib(unidade)
                .vIS(resultadoTributoDevido)
                .pIS(valorAliquotaAdValorem != null ? valorAliquotaAdValorem.movePointRight(2) : valorAliquotaAdValorem) // para nao enviar a aliquota ad valorem dividida por 100
                .pISEspec(valorAliquotaAdRem)
                .vBCIS(resultadoBaseCalculo)
                .memoriaCalculo(memoriaCalculo)
                .build();
    }

    private static Entry<String, BigDecimal> entry(VariavelExpressao c, BigDecimal v) {
        return Map.entry(c.getNome(), requireNonNullElse(v, ZERO));
    }

}
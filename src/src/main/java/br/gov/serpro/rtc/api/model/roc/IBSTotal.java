package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class IBSTotal {

    @Schema(description = "Grupo total do IBS da UF")
    @JsonProperty(value = "gIBSUF", index = 0)
    private IBSUFTotal gIBSUF;
    
    @Schema(description = "Grupo total do IBS do Município")
    @JsonProperty(value = "gIBSMun", index = 1)
    private IBSMunTotal gIBSMun;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do IBS")
    @JsonProperty(value = "vIBS", index = 2)
    private BigDecimal vIBS;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido")
    @JsonProperty(value = "vCredPres", index = 3)
    private BigDecimal vCredPres;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido em condição suspensiva")
    @JsonProperty(value = "vCredPresCondSus", index = 4)
    private BigDecimal vCredPresCondSus;
    
    // TODO Verificar se é possível criar um IBSCBSTotal
    public static IBSTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty()) {
            final var ibsTotal = new IBSTotal();
            final var gIBSUF = IBSUFTotal.create(detalhes);
            final var gIBSMun = IBSMunTotal.create(detalhes);
            ibsTotal.setGIBSUF(gIBSUF);
            ibsTotal.setGIBSMun(gIBSMun);
            ibsTotal.setVIBS(getValorIbsTotal(gIBSUF, gIBSMun));
            ibsTotal.setVCredPres(getValorCreditoPresumidoTotal(detalhes));
            ibsTotal.setVCredPresCondSus(getValorCreditoPresumidoCondSusTotal(detalhes));
            return ibsTotal;
        }
        return null;
    }

    private static BigDecimal getValorIbsTotal(IBSUFTotal ibsUFTotal, IBSMunTotal ibsMunTotal) {
        BigDecimal vIBSUF = ibsUFTotal != null ? ibsUFTotal.getVIBSUF() : ZERO;
        BigDecimal vIBSMun = ibsMunTotal != null ? ibsMunTotal.getVIBSMun() : ZERO;
        return vIBSUF.add(vIBSMun);
    }

    private static BigDecimal getValorCreditoPresumidoTotal(List<Objeto> detalhes) {
        final var condicao = condicaoPossuiCreditoPresumido();
        if (possuiCondicao(detalhes, condicao)) {
            return detalhes.parallelStream()
                    .filter(condicao)
                    .map(d -> d.getVCredPresIbs())
                    .reduce(ZERO, BigDecimal::add);
        }
        return ZERO;
    }

    private static BigDecimal getValorCreditoPresumidoCondSusTotal(List<Objeto> detalhes) {
        final var condicao = condicaoPossuiCreditoPresumidoCondSus();
        if (possuiCondicao(detalhes, condicao)) {
            return detalhes.parallelStream()
                .filter(condicao)
                .map(d -> d.getVCredPresCondSusIbs())
                .reduce(ZERO, BigDecimal::add);
        }
        return ZERO;
    }

    private static boolean possuiCondicao(List<Objeto> detalhes, Predicate<? super Objeto> condicao) {
        return detalhes.parallelStream().anyMatch(condicao);
    }

    private static Predicate<? super Objeto> condicaoPossuiCreditoPresumido() {
        return d -> d.possuiCredPresIbs();
    }
    
    private static Predicate<? super Objeto> condicaoPossuiCreditoPresumidoCondSus() {
        return d -> d.possuiCredPresCondSusIbs();
    }

}
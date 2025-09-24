package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.api.model.roc.reduce.CBSTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CBSTotal {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do diferimento")
    @JsonProperty(value = "vDif", index = 0)
    private BigDecimal vDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total de devolução de tributos")
    @JsonProperty(value = "vDevTrib", index = 1)
    private BigDecimal vDevTrib = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total da CBS")
    @JsonProperty(value = "vCBS", index = 2)
    private BigDecimal vCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido")
    @JsonProperty(value = "vCredPres", index = 3)
    private BigDecimal vCredPres = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido em condição suspensiva")
    @JsonProperty(value = "vCredPresCondSus", index = 4)
    private BigDecimal vCredPresCondSus = ZERO;
    
    public static CBSTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiCbs(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiCbs())
                    .map(d -> d.getGrupoIBSCBS())
                    .map(g -> CBSTotalAccumulator.from(g.getGCBS(), g.getGCBSCredPres()))
                    .reduce(new CBSTotalAccumulator(), CBSTotalAccumulator::add, CBSTotalAccumulator::add)
                    .toCBSTotal();
        }
        return null;
    }
    
    private static boolean possuiCbs(List<Objeto> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiCbs());
    }
    
    private static Predicate<? super Objeto> condicaoPossuiCbs() {
        return d -> d.possuiCbs() || d.possuiCreditoPresumidoCbs();
    }

}
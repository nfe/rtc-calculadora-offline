package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.api.model.roc.reduce.IBSMunTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class IBSMunTotal {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do diferimento")
    @JsonProperty(value = "vDif", index = 0)
    private BigDecimal vDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total de devolução de tributos")
    @JsonProperty(value = "vDevTrib", index = 1)
    private BigDecimal vDevTrib = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do IBS do Município")
    @JsonProperty(value = "vIBSMun", index = 2)
    private BigDecimal vIBSMun = ZERO;
    
    // TODO verificar se é possivel criar o IBSMunTotal a partir de detalhes
    public static IBSMunTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiIbsMunicipal(detalhes)) {
            return detalhes.parallelStream()
                .filter(condicaoPossuiIbsMunicipal())
                .map(d -> IBSMunTotalAccumulator.from(d.getGIBSMun()))
                .reduce(new IBSMunTotalAccumulator(), IBSMunTotalAccumulator::add, IBSMunTotalAccumulator::add)
                .toIBSMunTotal();
        }
        return null;
    }
    
    private static boolean possuiIbsMunicipal(List<Objeto> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiIbsMunicipal());
    }
    
    private static Predicate<? super Objeto> condicaoPossuiIbsMunicipal() {
        return d -> d.possuiIBSMun();
    }

}

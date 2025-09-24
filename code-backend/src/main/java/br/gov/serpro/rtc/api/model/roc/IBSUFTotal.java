package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.api.model.roc.reduce.IBSUFTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class IBSUFTotal {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do diferimento")
    @JsonProperty(value = "vDif", index = 0)
    private BigDecimal vDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total de devolução de tributos")
    @JsonProperty(value = "vDevTrib", index = 1)
    private BigDecimal vDevTrib = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do IBS da UF")
    @JsonProperty(value = "vIBSUF", index = 2)
    private BigDecimal vIBSUF = ZERO;
    
    // TODO verificar se é possivel criar o IBSUFTotal a partir de detalhes
    public static IBSUFTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiIbsUF(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiIbsUF())
                    .map(d -> IBSUFTotalAccumulator.from(d.getGIBSUF()))
                    .reduce(new IBSUFTotalAccumulator(), IBSUFTotalAccumulator::add, IBSUFTotalAccumulator::add)
                    .toIBSUFTotal();
        }
        return null;
    }
    
    private static boolean possuiIbsUF(List<Objeto> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiIbsUF());
    }
    
    private static Predicate<? super Objeto> condicaoPossuiIbsUF() {
        return d -> d.possuiIBSUF();
    }

}

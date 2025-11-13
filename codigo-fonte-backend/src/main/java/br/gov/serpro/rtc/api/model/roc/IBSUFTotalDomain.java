package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.api.model.roc.reduce.IBSUFTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vDif", "vDevTrib", "vIBSUF" })
public class IBSUFTotalDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do diferimento")
    private BigDecimal vDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total de devolução de tributos")
    private BigDecimal vDevTrib = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do IBS da UF")
    private BigDecimal vIBSUF = ZERO;
    
    // TODO verificar se é possivel criar o IBSUFTotal a partir de detalhes
    public static IBSUFTotalDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiIbsUF(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiIbsUF())
                    .map(d -> IBSUFTotalAccumulator.from(d.getGIBSUF()))
                    .reduce(new IBSUFTotalAccumulator(), IBSUFTotalAccumulator::add, IBSUFTotalAccumulator::add)
                    .toIBSUFTotal();
        }
        return null;
    }
    
    private static boolean possuiIbsUF(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiIbsUF());
    }
    
    private static Predicate<? super ObjetoDomain> condicaoPossuiIbsUF() {
        return ObjetoDomain::possuiIBSUF;
    }

}

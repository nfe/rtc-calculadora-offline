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
import br.gov.serpro.rtc.api.model.roc.reduce.IBSMunTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vDif", "vDevTrib", "vIBSMun" })
public class IBSMunTotalDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do diferimento")
    private BigDecimal vDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total de devolução de tributos")
    private BigDecimal vDevTrib = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do IBS do Município")
    private BigDecimal vIBSMun = ZERO;
    
    // TODO verificar se é possivel criar o IBSMunTotal a partir de detalhes
    public static IBSMunTotalDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiIbsMunicipal(detalhes)) {
            return detalhes.parallelStream()
                .filter(condicaoPossuiIbsMunicipal())
                .map(d -> IBSMunTotalAccumulator.from(d.getGIBSMun()))
                .reduce(new IBSMunTotalAccumulator(), IBSMunTotalAccumulator::add, IBSMunTotalAccumulator::add)
                .toIBSMunTotal();
        }
        return null;
    }
    
    private static boolean possuiIbsMunicipal(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiIbsMunicipal());
    }
    
    private static Predicate<? super ObjetoDomain> condicaoPossuiIbsMunicipal() {
        return ObjetoDomain::possuiIBSMun;
    }

}

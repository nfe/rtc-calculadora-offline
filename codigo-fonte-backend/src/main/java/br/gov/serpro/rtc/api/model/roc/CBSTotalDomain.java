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
import br.gov.serpro.rtc.api.model.roc.reduce.CBSTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vDif", "vDevTrib", "vCBS", "vCredPres", "vCredPresCondSus" })
public class CBSTotalDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do diferimento")
    private BigDecimal vDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total de devolução de tributos")
    private BigDecimal vDevTrib = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total da CBS")
    private BigDecimal vCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido")
    private BigDecimal vCredPres = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido em condição suspensiva")
    private BigDecimal vCredPresCondSus = ZERO;
    
    public static CBSTotalDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiCbs(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiCbs())
                    .map(t -> CBSTotalAccumulator.from(t.getGCBS(), t.getCredPresCbs()))
                    .reduce(new CBSTotalAccumulator(), CBSTotalAccumulator::add, CBSTotalAccumulator::add)
                    .toCBSTotal();
        }
        return null;
    }
    
    private static boolean possuiCbs(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiCbs());
    }
    
    private static Predicate<? super ObjetoDomain> condicaoPossuiCbs() {
        return d -> d.possuiCbs() || d.possuiCredPresCbs();
    }

}
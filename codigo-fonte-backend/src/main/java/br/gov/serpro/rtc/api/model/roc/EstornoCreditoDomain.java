package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.api.model.roc.reduce.EstornoCreditoAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vIBSEstCred", "vCBSEstCred" })
public class EstornoCreditoDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS a ser estornado")
    private BigDecimal vIBSEstCred;

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS a ser estornada")
    private BigDecimal vCBSEstCred;

    public static EstornoCreditoDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiEstornoCredito(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiEstornoCredito())
                    .map(d -> EstornoCreditoAccumulator.from(d.getGEstornoCred()))
                    .reduce(new EstornoCreditoAccumulator(), EstornoCreditoAccumulator::add, EstornoCreditoAccumulator::add)
                    .toEstornoCreditoDomainTotal();
        }
        return null;
    }

    private static boolean possuiEstornoCredito(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiEstornoCredito());
    }

    private static Predicate<? super ObjetoDomain> condicaoPossuiEstornoCredito() {    
        return ObjetoDomain::possuiEstornoCredito;
    }
}
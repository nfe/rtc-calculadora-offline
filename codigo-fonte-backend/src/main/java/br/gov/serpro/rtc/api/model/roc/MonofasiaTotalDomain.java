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
import br.gov.serpro.rtc.api.model.roc.reduce.MonofasiaTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vIBSMono", "vCBSMono", "vIBSMonoReten", "vCBSMonoReten", "vIBSMonoRet", "vCBSMonoRet" })
public class MonofasiaTotalDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do IBS monofásico")
    private BigDecimal vIBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total da CBS monofásica")
    private BigDecimal vCBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do IBS monofásico sujeito a retenção")
    private BigDecimal vIBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total da CBS monofásica sujeita a retenção")
    private BigDecimal vCBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do IBS monofásico retido anteriormente")
    private BigDecimal vIBSMonoRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total da CBS monofásica retida anteriormente")
    private BigDecimal vCBSMonoRet = ZERO;
    
    public static MonofasiaTotalDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiMonofasia(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiMonofasia())
                    .map(d -> MonofasiaTotalAccumulator.from(d.getGIBSCBSMono()))
                    .reduce(new MonofasiaTotalAccumulator(), MonofasiaTotalAccumulator::add, MonofasiaTotalAccumulator::add)
                    .toMonofasiaTotal();
        }
        return null;
    }

    private static boolean possuiMonofasia(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiMonofasia());
    }

    private static Predicate<? super ObjetoDomain> condicaoPossuiMonofasia() {    
        return ObjetoDomain::possuiMonofasia;
    }
}

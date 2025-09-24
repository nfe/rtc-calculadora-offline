package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.roc.reduce.MonofasiaTotalAccumulator;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class MonofasiaTotal {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do IBS monofásico")
    @JsonProperty(value = "vIBSMono", index = 0)
    private BigDecimal vIBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total da CBS monofásica")
    @JsonProperty(value = "vCBSMono", index = 1)
    private BigDecimal vCBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do IBS monofásico sujeito a retenção")
    @JsonProperty(value = "vIBSMonoReten", index = 2)
    private BigDecimal vIBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total da CBS monofásica sujeita a retenção")
    @JsonProperty(value = "vCBSMonoReten", index = 3)
    private BigDecimal vCBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do IBS monofásico retido anteriormente")
    @JsonProperty(value = "vIBSMonoRet", index = 4)
    private BigDecimal vIBSMonoRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total da CBS monofásica retida anteriormente")
    @JsonProperty(value = "vCBSMonoRet", index = 5)
    private BigDecimal vCBSMonoRet = ZERO;
    
    public static MonofasiaTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiMonofasia(detalhes)) {
            return detalhes.parallelStream()
                    .filter(condicaoPossuiMonofasia())
                    .map(d -> MonofasiaTotalAccumulator.from(d.getGIBSCBSMono()))
                    .reduce(new MonofasiaTotalAccumulator(), MonofasiaTotalAccumulator::add, MonofasiaTotalAccumulator::add)
                    .toMonofasiaTotal();
        }
        return null;
    }

    private static boolean possuiMonofasia(List<Objeto> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiMonofasia());
    }

    private static Predicate<? super Objeto> condicaoPossuiMonofasia() {    
        return Objeto::possuiMonofasia;
    }
}

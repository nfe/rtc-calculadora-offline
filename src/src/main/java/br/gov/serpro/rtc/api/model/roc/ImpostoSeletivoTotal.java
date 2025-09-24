package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ImpostoSeletivoTotal {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do imposto seletivo")
    @JsonProperty(value = "vIS", index = 0)
    private BigDecimal vIS = ZERO;
    
    public static ImpostoSeletivoTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiImpostoSeletivo(detalhes)) {
            ImpostoSeletivoTotal isTotal = new ImpostoSeletivoTotal();
            isTotal.setVIS(getValorTotalIS(detalhes));
            return isTotal;
        }
        return null;
    }
    
    private static boolean possuiImpostoSeletivo(List<Objeto> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiIS());
    }

    private static BigDecimal getValorTotalIS(List<Objeto> detalhes) {
        return detalhes.parallelStream()
                .filter(condicaoPossuiIS())
                .map(d -> d.getValorImpostoSeletivo())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Predicate<? super Objeto> condicaoPossuiIS() {
        return d -> d.possuiImpostoSeletivo();
    }

}

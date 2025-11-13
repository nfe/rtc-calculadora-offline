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
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vIS" })
public class ImpostoSeletivoTotalDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total do imposto seletivo")
    private BigDecimal vIS = ZERO;
    
    public static ImpostoSeletivoTotalDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty() && possuiImpostoSeletivo(detalhes)) {
            ImpostoSeletivoTotalDomain isTotal = new ImpostoSeletivoTotalDomain();
            isTotal.setVIS(getValorTotalIS(detalhes));
            return isTotal;
        }
        return null;
    }
    
    private static boolean possuiImpostoSeletivo(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream().anyMatch(condicaoPossuiIS());
    }

    private static BigDecimal getValorTotalIS(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream()
                .filter(condicaoPossuiIS())
                .map(ObjetoDomain::getValorImpostoSeletivo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Predicate<? super ObjetoDomain> condicaoPossuiIS() {
        return ObjetoDomain::possuiImpostoSeletivo;
    }

}
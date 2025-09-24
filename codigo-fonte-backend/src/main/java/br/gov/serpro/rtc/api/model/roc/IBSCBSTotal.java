package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class IBSCBSTotal {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total da BC do IBS e da CBS")
    @JsonProperty(value = "vBCIBSCBS", index = 0)
    private BigDecimal vBCIBSCBS;
    
    @Schema(description = "Grupo total do IBS")
    @JsonProperty(value = "gIBS", index = 1)
    private IBSTotal gIBS;
    
    @Schema(description = "Grupo total da CBS")
    @JsonProperty(value = "gCBS", index = 2)
    private CBSTotal gCBS;
    
    @Schema(description = "Grupo total da Monofasia")
    @JsonProperty(value = "gMono", index = 3)
    private MonofasiaTotal gMono;
    
    // TODO Verificar se é possivel criar o IBSCBSTotal
    public static IBSCBSTotal create(List<Objeto> detalhes) {
        if (detalhes != null && !detalhes.isEmpty()) {
            final var ibsCbsTotal = new IBSCBSTotal();
            ibsCbsTotal.setVBCIBSCBS(getBaseCalculoIbsCbsTotal(detalhes));
            ibsCbsTotal.setGIBS(IBSTotal.create(detalhes)); 
            ibsCbsTotal.setGCBS(CBSTotal.create(detalhes));
            ibsCbsTotal.setGMono(MonofasiaTotal.create(detalhes));
            return ibsCbsTotal;
        }
        return null;
    }

    private static BigDecimal getBaseCalculoIbsCbsTotal(List<Objeto> detalhes) {
        return detalhes.parallelStream()
                .filter(d -> d.possuiBaseCalculoIBSCBS())
                .map(d -> d.getValorBaseCalculoIBSCBS())
                .reduce(ZERO, BigDecimal::add);
    }

}

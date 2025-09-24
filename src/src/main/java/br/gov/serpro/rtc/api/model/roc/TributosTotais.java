package br.gov.serpro.rtc.api.model.roc;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class TributosTotais {

    @Schema(description = "Grupo total do imposto seletivo")
    @JsonProperty(value = "ISTot", index = 0)
    private ImpostoSeletivoTotal ISTot;
    
    @Schema(description = "Totais com IBS e CBS")    
    @JsonProperty(value = "IBSCBSTot", index = 1)
    private IBSCBSTotal IBSCBSTot;
    
    public static TributosTotais create(List<Objeto> detalhes) {
        final var tributosTotais = new TributosTotais();
        tributosTotais.setISTot(ImpostoSeletivoTotal.create(detalhes));
        tributosTotais.setIBSCBSTot(IBSCBSTotal.create(detalhes));
        return tributosTotais;
    }
    
}

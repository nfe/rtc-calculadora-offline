package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "ISTot", "IBSCBSTot" })
public class TributosTotaisDomain implements SerializationVisibility {

    @Schema(description = "Grupo total do imposto seletivo")
    private ImpostoSeletivoTotalDomain ISTot;
    
    @Schema(description = "Totais com IBS e CBS")    
    private IBSCBSTotalDomain IBSCBSTot;
    
    public static TributosTotaisDomain create(List<ObjetoDomain> detalhes) {
        final var tributosTotais = new TributosTotaisDomain();
        tributosTotais.setISTot(ImpostoSeletivoTotalDomain.create(detalhes));
        tributosTotais.setIBSCBSTot(IBSCBSTotalDomain.create(detalhes));
        return tributosTotais;
    }
    
}

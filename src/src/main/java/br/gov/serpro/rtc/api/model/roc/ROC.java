package br.gov.serpro.rtc.api.model.roc;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
@JacksonXmlRootElement(localName = "infNFe")
public final class ROC {
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "det")
    @JsonProperty(value = "objetos", index = 0)
    private List<Objeto> objetos;
    
    @JsonProperty(value = "total", index = 1)
    private ValoresTotais total;

}

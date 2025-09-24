package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.config.xml.IValoresTotais;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(NON_NULL)
public class ValoresTotais implements IValoresTotais {

    @JsonProperty(value = "tribCalc", index = 0)
    private TributosTotais tribCalc;

    public TributosTotais getTribCalc() {
        return this.tribCalc;
    }

    public void setTribCalc(TributosTotais tribCalc) {
        this.tribCalc = tribCalc;
    }

    public static ValoresTotais create(List<Objeto> detalhes) {
        ValoresTotais valoresTotais = new ValoresTotais();
        valoresTotais.setTribCalc(TributosTotais.create(detalhes));
        return valoresTotais;
    }
}

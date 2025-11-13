package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "tribCalc" })
public class ValoresTotaisDomain implements SerializationVisibility  {

    private TributosTotaisDomain tribCalc;

    public static ValoresTotaisDomain create(List<ObjetoDomain> detalhes) {
        ValoresTotaisDomain valoresTotais = new ValoresTotaisDomain();
        valoresTotais.setTribCalc(TributosTotaisDomain.create(detalhes));
        return valoresTotais;
    }
}

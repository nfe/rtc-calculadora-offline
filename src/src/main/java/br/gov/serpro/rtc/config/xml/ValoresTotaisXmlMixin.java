package br.gov.serpro.rtc.config.xml;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import br.gov.serpro.rtc.api.model.roc.TributosTotais;

/**
 * Necessario para que os atributos da classe TributosTotais sejam serializados
 * no XML como tags filhas da classe que a envolve. Usada na configuracao do
 * XMLMapper.
 */
public interface ValoresTotaisXmlMixin extends IValoresTotais {
    
    @JsonUnwrapped
    TributosTotais getTribCalc();
}
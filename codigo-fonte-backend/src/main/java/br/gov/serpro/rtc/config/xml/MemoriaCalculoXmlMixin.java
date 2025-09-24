package br.gov.serpro.rtc.config.xml;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Necessario para que a memoria de calculo nao seja serializada no XML. Usada
 * na configuracao do XMLMapper.
 */
public interface MemoriaCalculoXmlMixin extends IMemoriaCalculo {
    
    @JsonIgnore
    String getMemoriaCalculo();
}
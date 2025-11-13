package br.gov.serpro.rtc.api.model.xml.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoXml {
    GRUPO("grupo.xsd"),
    NOTA("nota.xsd");

    private final String mnemonico;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
    
    @JsonCreator
    public static TipoXml fromMnemonico(String mnemonico) {
        if (mnemonico == null || mnemonico.trim().isEmpty()) {
            throw new IllegalArgumentException("Mnemonico não pode ser nulo ou vazio");
        }
        for (TipoXml tipo : TipoXml.values()) {
            if (tipo.getMnemonico().equalsIgnoreCase(mnemonico)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de XML inválido: " + mnemonico);
    }
    
}

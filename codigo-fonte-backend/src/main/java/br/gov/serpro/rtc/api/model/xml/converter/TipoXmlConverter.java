package br.gov.serpro.rtc.api.model.xml.converter;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import br.gov.serpro.rtc.api.model.xml.enumeration.TipoXml;

@Component
public class TipoXmlConverter implements Converter<String, TipoXml> {
    @Override
    public TipoXml convert(@NonNull String source) {
        final TipoXml tipo = EnumUtils.getEnumIgnoreCase(TipoXml.class, source);
        if (tipo == null) {
            throw new IllegalArgumentException("TipoXml inválido: " + source);
        }
        return tipo;
    }

}

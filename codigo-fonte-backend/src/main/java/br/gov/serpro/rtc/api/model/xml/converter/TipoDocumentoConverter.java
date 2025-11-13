package br.gov.serpro.rtc.api.model.xml.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import br.gov.serpro.rtc.api.model.xml.enumeration.TipoDocumento;

@Component
public class TipoDocumentoConverter implements Converter<String, TipoDocumento> {
    @Override
    public TipoDocumento convert(@NonNull String source) {
        return TipoDocumento.fromMnemonico(source);
    }
}
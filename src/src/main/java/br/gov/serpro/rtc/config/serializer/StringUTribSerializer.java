package br.gov.serpro.rtc.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializador para o elemento uTrib (unidade de medida), baseado no tipo TStringRTC,
 * com tamanho mínimo 1 e máximo 6 caracteres.
 */
public class StringUTribSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value.isEmpty() || value.length() > 6) {
            throw new IOException("uTrib deve ter entre 1 e 6 caracteres");
        } else {
            gen.writeString(value);
        }
    }
}
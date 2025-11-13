package br.gov.serpro.rtc.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializador para o tipo TCST (Código Situação Tributária do IBS/CBS, exatamente 3 dígitos).
 */
public class StringTCSTSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (!value.matches("\\d{3}")) {
            throw new IOException("TCST deve ser uma string de exatamente 3 dígitos numéricos");
        } else {
            gen.writeString(value);
        }
    }
}
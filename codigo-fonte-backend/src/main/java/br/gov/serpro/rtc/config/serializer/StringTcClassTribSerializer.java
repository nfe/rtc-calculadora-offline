package br.gov.serpro.rtc.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializador para o tipo TcClassTrib (Código de Classificação Tributária do IBS/CBS, exatamente 6 dígitos).
 * Serializa String garantindo o padrão de 6 dígitos.
 */
public class StringTcClassTribSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (!value.matches("\\d{6}")) {
            throw new IOException("TcClassTrib deve ser uma string de exatamente 6 dígitos numéricos");
        } else {
            gen.writeString(value);
        }
    }
}
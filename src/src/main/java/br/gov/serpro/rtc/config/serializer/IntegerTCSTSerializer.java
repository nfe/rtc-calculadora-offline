package br.gov.serpro.rtc.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializador para o tipo TCST (Código Situação Tributária do IBS/CBS, exatamente 3 dígitos).
 * Serializa Integer como string de 3 dígitos com zeros à esquerda.
 */
public class IntegerTCSTSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value < 0 || value > 999) {
            throw new IOException("TCST deve ser um número inteiro entre 0 e 999");
        } else {
            // Serializa como string de 3 dígitos, com zeros à esquerda se necessário
            gen.writeString(String.format("%03d", value));
        }
    }
}
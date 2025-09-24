package br.gov.serpro.rtc.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Serializador para o tipo TDec_1104Op (até 11 inteiros e 4 decimais, sem zeros à direita desnecessários).
 * Exemplo válido: 0.0001, 0.0010, 0.0100, 0.1000, 1, 12345678901.1234
 */
public class BigDecimalTDec1104OpSerializer extends JsonSerializer<BigDecimal> {

    private static final DecimalFormat FORMAT;
    static {
        FORMAT = new DecimalFormat("0.####");
        FORMAT.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value.signum() < 0) {
            throw new IllegalArgumentException("Números negativos não são permitidos para TDec_1104Op");
        } else {
            // Remove zeros à direita, mas garante até 4 casas decimais se necessário
            gen.writeString(FORMAT.format(value.stripTrailingZeros()));
        }
    }
}
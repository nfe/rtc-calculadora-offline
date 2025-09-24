package br.gov.serpro.rtc.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Serializador para o tipo TDec_0302_04 (até 3 inteiros, de 2 a 4 decimais).
 * Exemplo válido: 0.01, 0.1234, 123.12, 999.9999
 */
public class BigDecimalTDec0302_04Serializer extends JsonSerializer<BigDecimal> {

    private static final DecimalFormat FORMAT;
    static {
        FORMAT = new DecimalFormat("0.00##");
        FORMAT.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value.signum() < 0) {
            throw new IllegalArgumentException("Números negativos não são permitidos para TDec_0302_04");
        } else {
            // Garante de 2 a 4 casas decimais, sem zeros desnecessários à direita
            BigDecimal scaled = value.setScale(Math.clamp(value.scale(), 2, 4), RoundingMode.HALF_UP);
            gen.writeString(FORMAT.format(scaled.stripTrailingZeros()));
        }
    }
}
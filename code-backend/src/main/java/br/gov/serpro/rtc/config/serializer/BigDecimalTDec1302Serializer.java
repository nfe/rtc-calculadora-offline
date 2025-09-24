package br.gov.serpro.rtc.config.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializador para o tipo TDec1302 (até 13 inteiros e 2 decimais, sempre com 2 casas decimais).
 * Regex: 0|0\.[0-9]{2}|[1-9]{1}[0-9]{0,12}(\.[0-9]{2})?
 * Exemplos válidos: 0, 0.01, 1234567890123.45
 */
public class BigDecimalTDec1302Serializer extends JsonSerializer<BigDecimal> {

    private static final DecimalFormat FORMAT;
    static {
        FORMAT = new DecimalFormat("0.00");
        FORMAT.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value.signum() < 0) {
            throw new IllegalArgumentException("Números negativos não são permitidos para TDec1302");
        } else {
            // Sempre serializa com 2 casas decimais, conforme o XSD
            gen.writeString(FORMAT.format(value.setScale(2, RoundingMode.HALF_UP)));
        }
    }
}
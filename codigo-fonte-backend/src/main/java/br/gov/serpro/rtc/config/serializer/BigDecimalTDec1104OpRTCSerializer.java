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
 * Serializador para o tipo TDec_1104OpRTC (quantidade na RTC).
 * 
 * Padrão: 0\.[1-9]{1}[0-9]{3}|0\.[0-9]{3}[1-9]{1}|0\.[0-9]{2}[1-9]{1}[0-9]{1}|0\.[0-9]{1}[1-9]{1}[0-9]{2}|[1-9]{1}[0-9]{0,10}(\.[0-9]{4})?
 * 
 * Regras:
 * - Números inteiros: 1 a 11 dígitos, sem zeros à esquerda (ex: 1, 1000, 99999999999)
 * - Números decimais: exatamente 4 casas decimais, pelo menos 1 dígito != 0 nas 4 casas (ex: 55.5555, 55.5500, 0.0001)
 * - Não aceita: 55.55 (falta zeros), 55.550 (falta zeros), 0 (deve ser inteiro sem ponto)
 * 
 * Exemplos válidos:
 * - 1000 → "1000"
 * - 55.5555 → "55.5555"
 * - 55.5500 → "55.5500"
 * - 0.0001 → "0.0001"
 * 
 * Exemplos inválidos:
 * - 55.55 (apenas 2 decimais)
 * - 55.550 (apenas 3 decimais)
 * - 0.0000 (todas as casas decimais são zero)
 */
public class BigDecimalTDec1104OpRTCSerializer extends JsonSerializer<BigDecimal> {

    private static final DecimalFormat FORMAT_4_DECIMALS;
    static {
        FORMAT_4_DECIMALS = new DecimalFormat("0.0000");
        FORMAT_4_DECIMALS.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value.signum() < 0) {
            throw new IllegalArgumentException("Números negativos não são permitidos para TDec_1104OpRTC");
        } else {
            // Verifica se é número inteiro (sem parte decimal)
            if (value.stripTrailingZeros().scale() <= 0) {
                // Número inteiro: escreve sem ponto decimal
                gen.writeString(value.toBigInteger().toString());
            } else {
                // Número decimal: escreve com exatamente 4 casas decimais
                gen.writeString(FORMAT_4_DECIMALS.format(value));
            }
        }
    }
}

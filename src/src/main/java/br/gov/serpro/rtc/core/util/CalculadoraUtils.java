/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.core.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.Stream;

import lombok.NonNull;

public final class CalculadoraUtils {
    public static final BigDecimal CEM = new BigDecimal("100");

    private static final int ESCALA = 2;
    private static final int PRECISAO = 34;
    private static final RoundingMode MODO_ARREDONDAMENTO = RoundingMode.DOWN;
    private static final MathContext CONTEXTO = new MathContext(PRECISAO, MODO_ARREDONDAMENTO);

    private CalculadoraUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final BigDecimal multiplicar(BigDecimal v1, BigDecimal v2) {
        return getValorTruncado(v1.multiply(v2));
    }
    
    // é assim mesmo?
    public static final BigDecimal dividir(BigDecimal v1, BigDecimal v2) {
        return getValorTruncado(v1.divide(v2, 2, MODO_ARREDONDAMENTO));
    }

    public static final BigDecimal totalizar(Stream<BigDecimal> valores) {
        return valores.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static final BigDecimal getValorTruncado(@NonNull BigDecimal valor) {
        return valor.setScale(ESCALA, MODO_ARREDONDAMENTO).round(CONTEXTO);
    }

    public static final BigDecimal dividePorCem(@NonNull BigDecimal valor) {
        return valor.divide(CEM);
    }

    public static final BigDecimal multiplicaPorCem(@NonNull BigDecimal valor) {
        return valor.multiply(CEM);
    }

}

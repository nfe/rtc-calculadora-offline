/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.core.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberFormatUtils {

    private NumberFormatUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String currencyWithChosenLocalisation(BigDecimal value) {
        return getCurrencyFormat().format(value);
    }

    private static NumberFormat getCurrencyFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(6);
        return nf;
    }

    private static NumberFormat getNumberFormat() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(6);
        return nf;
    }

    public static String forNumber(BigDecimal value) {
        return getNumberFormat().format(value);
    }

    public static String forPercentages(BigDecimal value) {
        return getPercentFormat().format(value);
    }

    private static NumberFormat getPercentFormat() {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(6);
        return nf;
    }

}

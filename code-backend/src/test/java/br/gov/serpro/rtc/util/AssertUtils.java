package br.gov.serpro.rtc.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class AssertUtils {
    
    public static void isEqualByComparingTo(BigDecimal actual, String expected) {
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualByComparingTo(expected);
    }

    public static void isEqualByComparingTo(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualByComparingTo(expected);
    }

}

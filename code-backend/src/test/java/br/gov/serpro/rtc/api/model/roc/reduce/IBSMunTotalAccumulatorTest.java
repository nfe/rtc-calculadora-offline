package br.gov.serpro.rtc.api.model.roc.reduce;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.api.model.roc.DevolucaoTributos;
import br.gov.serpro.rtc.api.model.roc.Diferimento;
import br.gov.serpro.rtc.api.model.roc.IBSMun;
import br.gov.serpro.rtc.api.model.roc.IBSMunTotal;
import br.gov.serpro.rtc.api.model.roc.reduce.IBSMunTotalAccumulator;

class IBSMunTotalAccumulatorTest {

    @Test
    void testDefaultConstructor() {
        IBSMunTotalAccumulator acc = new IBSMunTotalAccumulator();
        IBSMunTotal total = acc.toIBSMunTotal();
        isEqualByComparingTo(ZERO, total.getVDif());
        isEqualByComparingTo(ZERO, total.getVDevTrib());
        isEqualByComparingTo(ZERO, total.getVIBSMun());
    }

    @Test
    void testParameterizedConstructor() {
        BigDecimal v1 = ONE;
        IBSMunTotalAccumulator acc = new IBSMunTotalAccumulator(v1, v1, v1);
        IBSMunTotal total = acc.toIBSMunTotal();
        isEqualByComparingTo(v1, total.getVDif());
        isEqualByComparingTo(v1, total.getVDevTrib());
        isEqualByComparingTo(v1, total.getVIBSMun());
    }

    @Test
    void testFromMethodWithNull() {
        IBSMunTotalAccumulator acc = IBSMunTotalAccumulator.from(null);
        IBSMunTotal total = acc.toIBSMunTotal();
        isEqualByComparingTo(ZERO, total.getVDif());
        isEqualByComparingTo(ZERO, total.getVDevTrib());
        isEqualByComparingTo(ZERO, total.getVIBSMun());
    }

    @Test
    void testFromMethodWithValues() {
        Diferimento gDif = Diferimento.builder()
                .vDif(TEN)
                .build();
        DevolucaoTributos gDevTrib = DevolucaoTributos.builder()
                .vDevTrib(ONE)
                .build();
        IBSMun ibsMun = new IBSMun();
        ibsMun.setGDif(gDif);
        ibsMun.setGDevTrib(gDevTrib);
        
        final var valorImposto = BigDecimal.valueOf(5);
        ibsMun.setValorImposto(valorImposto);
        
        IBSMunTotalAccumulator acc = IBSMunTotalAccumulator.from(ibsMun);
        IBSMunTotal total = acc.toIBSMunTotal();
        isEqualByComparingTo(TEN, total.getVDif());
        isEqualByComparingTo(ONE, total.getVDevTrib());
        isEqualByComparingTo(valorImposto, total.getVIBSMun());
    }

    @Test
    void testAddMethod() {
        IBSMunTotalAccumulator acc1 = new IBSMunTotalAccumulator(ONE, ONE, ONE);
        IBSMunTotalAccumulator acc2 = new IBSMunTotalAccumulator(TEN, TEN, TEN);
        IBSMunTotalAccumulator result = acc1.add(acc2);
        IBSMunTotal total = result.toIBSMunTotal();
        
        final var onze = new BigDecimal("11.00");
        isEqualByComparingTo(onze, total.getVDif());
        isEqualByComparingTo(onze, total.getVDevTrib());
        isEqualByComparingTo(onze, total.getVIBSMun());
    }

    @Test
    void testAddWithNull() {
        IBSMunTotalAccumulator acc1 = new IBSMunTotalAccumulator(ONE, ONE, ONE);
        IBSMunTotalAccumulator result = acc1.add(null);
        IBSMunTotal total = result.toIBSMunTotal();
        isEqualByComparingTo(ONE, total.getVDif());
        isEqualByComparingTo(ONE, total.getVDevTrib());
        isEqualByComparingTo(ONE, total.getVIBSMun());
    }
    
    private static void isEqualByComparingTo(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(actual).isEqualByComparingTo(expected);
    }
}

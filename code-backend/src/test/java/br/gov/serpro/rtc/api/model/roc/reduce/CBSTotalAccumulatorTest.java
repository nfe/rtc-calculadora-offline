package br.gov.serpro.rtc.api.model.roc.reduce;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.TWO;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.api.model.roc.CBS;
import br.gov.serpro.rtc.api.model.roc.CBSTotal;
import br.gov.serpro.rtc.api.model.roc.CreditoPresumido;
import br.gov.serpro.rtc.api.model.roc.DevolucaoTributos;
import br.gov.serpro.rtc.api.model.roc.Diferimento;
import br.gov.serpro.rtc.api.model.roc.reduce.CBSTotalAccumulator;

class CBSTotalAccumulatorTest {

    @Test
    void testDefaultConstructor() {
        CBSTotalAccumulator acc = new CBSTotalAccumulator();
        CBSTotal total = acc.toCBSTotal();
        isEqualByComparingTo(ZERO, total.getVDif());
        isEqualByComparingTo(ZERO, total.getVDevTrib());
        isEqualByComparingTo(ZERO, total.getVCBS());
        isEqualByComparingTo(ZERO, total.getVCredPres());
        isEqualByComparingTo(ZERO, total.getVCredPresCondSus());
    }

    @Test
    void testParameterizedConstructor() {
        BigDecimal v1 = ONE;
        CBSTotalAccumulator acc = new CBSTotalAccumulator(v1, v1, v1, v1, v1);
        CBSTotal total = acc.toCBSTotal();
        isEqualByComparingTo(v1, total.getVDif());
        isEqualByComparingTo(v1, total.getVDevTrib());
        isEqualByComparingTo(v1, total.getVCBS());
        isEqualByComparingTo(v1, total.getVCredPres());
        isEqualByComparingTo(v1, total.getVCredPresCondSus());
    }

    @Test
    void testFromMethodWithNulls() {
        CBSTotalAccumulator acc = CBSTotalAccumulator.from(null, null);
        CBSTotal total = acc.toCBSTotal();
        isEqualByComparingTo(ZERO, total.getVDif());
        isEqualByComparingTo(ZERO, total.getVDevTrib());
        isEqualByComparingTo(ZERO, total.getVCBS());
        isEqualByComparingTo(ZERO, total.getVCredPres());
        isEqualByComparingTo(ZERO, total.getVCredPresCondSus());
    }

    @Test
    void testFromMethodWithValues() {
        Diferimento gDif = Diferimento.builder()
                .vDif(TEN)
                .build();
        DevolucaoTributos gDevTrib = DevolucaoTributos.builder()
                .vDevTrib(ONE)
                .build();
        CBS cbs = new CBS();
        cbs.setGDif(gDif);
        cbs.setGDevTrib(gDevTrib);
        final var valorImposto = new BigDecimal("5.00");
        cbs.setValorImposto(valorImposto);
        
        final var vCredPres = TWO;
        final var vCredPresCondSus = new BigDecimal("3.97");
        CreditoPresumido cred = CreditoPresumido.builder()
                .vCredPres(vCredPres)
                .vCredPresCondSus(vCredPresCondSus)
                .build();
        CBSTotalAccumulator acc = CBSTotalAccumulator.from(cbs, cred);
        CBSTotal total = acc.toCBSTotal();
        isEqualByComparingTo(TEN, total.getVDif());
        isEqualByComparingTo(ONE, total.getVDevTrib());
        isEqualByComparingTo(valorImposto, total.getVCBS());
        isEqualByComparingTo(vCredPres, total.getVCredPres());
        isEqualByComparingTo(vCredPresCondSus, total.getVCredPresCondSus());
    }

    @Test
    void testAddMethod() {
        CBSTotalAccumulator acc1 = new CBSTotalAccumulator(ONE, ONE, ONE, ONE, ONE);
        CBSTotalAccumulator acc2 = new CBSTotalAccumulator(TEN, TEN, TEN, TEN, TEN);
        CBSTotalAccumulator result = acc1.add(acc2);
        CBSTotal total = result.toCBSTotal();
        final var onze = new BigDecimal("11.00");
        isEqualByComparingTo(onze, total.getVDif());
        isEqualByComparingTo(onze, total.getVDevTrib());
        isEqualByComparingTo(onze, total.getVCBS());
        isEqualByComparingTo(onze, total.getVCredPres());
        isEqualByComparingTo(onze, total.getVCredPresCondSus());
    }

    @Test
    void testAddWithNull() {
        CBSTotalAccumulator acc1 = new CBSTotalAccumulator(ONE, ONE, ONE, ONE, ONE);
        CBSTotalAccumulator result = acc1.add(null);
        CBSTotal total = result.toCBSTotal();
        isEqualByComparingTo(ONE, total.getVDif());
        isEqualByComparingTo(ONE, total.getVDevTrib());
        isEqualByComparingTo(ONE, total.getVCBS());
        isEqualByComparingTo(ONE, total.getVCredPres());
        isEqualByComparingTo(ONE, total.getVCredPresCondSus());
    }
    
    private static void isEqualByComparingTo(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(actual).isEqualByComparingTo(expected);
    }
    
}

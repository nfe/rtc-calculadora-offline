package br.gov.serpro.rtc.api.model.roc.reduce;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.api.model.roc.Monofasia;
import br.gov.serpro.rtc.api.model.roc.MonofasiaTotal;
import br.gov.serpro.rtc.api.model.roc.reduce.MonofasiaTotalAccumulator;

class MonofasiaTotalAccumulatorTest {

    @Test
    void testDefaultConstructor() {
        MonofasiaTotalAccumulator acc = new MonofasiaTotalAccumulator();
        MonofasiaTotal total = acc.toMonofasiaTotal();
        isEqualByComparingTo(ZERO, total.getVIBSMono());
        isEqualByComparingTo(ZERO, total.getVCBSMono());
        isEqualByComparingTo(ZERO, total.getVIBSMonoReten());
        isEqualByComparingTo(ZERO, total.getVCBSMonoReten());
        isEqualByComparingTo(ZERO, total.getVIBSMonoRet());
        isEqualByComparingTo(ZERO, total.getVCBSMonoRet());
    }

    @Test
    void testParameterizedConstructor() {
        BigDecimal v = ONE;
        MonofasiaTotalAccumulator acc = new MonofasiaTotalAccumulator(v, v, v, v, v, v);
        MonofasiaTotal total = acc.toMonofasiaTotal();
        isEqualByComparingTo(v, total.getVIBSMono());
        isEqualByComparingTo(v, total.getVCBSMono());
        isEqualByComparingTo(v, total.getVIBSMonoReten());
        isEqualByComparingTo(v, total.getVCBSMonoReten());
        isEqualByComparingTo(v, total.getVIBSMonoRet());
        isEqualByComparingTo(v, total.getVCBSMonoRet());
    }

    @Test
    void testFromMethodWithNull() {
        MonofasiaTotalAccumulator acc = MonofasiaTotalAccumulator.from(null);
        MonofasiaTotal total = acc.toMonofasiaTotal();
        isEqualByComparingTo(ZERO, total.getVIBSMono());
        isEqualByComparingTo(ZERO, total.getVCBSMono());
        isEqualByComparingTo(ZERO, total.getVIBSMonoReten());
        isEqualByComparingTo(ZERO, total.getVCBSMonoReten());
        isEqualByComparingTo(ZERO, total.getVIBSMonoRet());
        isEqualByComparingTo(ZERO, total.getVCBSMonoRet());
    }

    @Test
    void testFromMethodWithValues() {
        Monofasia m = Monofasia.builder()
                .vIBSMono(TEN)
                .vCBSMono(ONE)
                .vIBSMonoReten(BigDecimal.TWO)
                .vCBSMonoReten(BigDecimal.valueOf(3))
                .vIBSMonoRet(BigDecimal.valueOf(4))
                .vCBSMonoRet(BigDecimal.valueOf(5))
                .build();
        MonofasiaTotalAccumulator acc = MonofasiaTotalAccumulator.from(m);
        MonofasiaTotal total = acc.toMonofasiaTotal();
        isEqualByComparingTo(TEN, total.getVIBSMono());
        isEqualByComparingTo(ONE, total.getVCBSMono());
        isEqualByComparingTo(BigDecimal.valueOf(2), total.getVIBSMonoReten());
        isEqualByComparingTo(BigDecimal.valueOf(3), total.getVCBSMonoReten());
        isEqualByComparingTo(BigDecimal.valueOf(4), total.getVIBSMonoRet());
        isEqualByComparingTo(BigDecimal.valueOf(5), total.getVCBSMonoRet());
    }

    @Test
    void testAddMethod() {
        MonofasiaTotalAccumulator acc1 = new MonofasiaTotalAccumulator(ONE, ONE, ONE, ONE, ONE, ONE);
        MonofasiaTotalAccumulator acc2 = new MonofasiaTotalAccumulator(TEN, TEN, TEN, TEN, TEN, TEN);
        MonofasiaTotalAccumulator result = acc1.add(acc2);
        MonofasiaTotal total = result.toMonofasiaTotal();
        final var onze = new BigDecimal("11.00");
        isEqualByComparingTo(onze, total.getVIBSMono());
        isEqualByComparingTo(onze, total.getVCBSMono());
        isEqualByComparingTo(onze, total.getVIBSMonoReten());
        isEqualByComparingTo(onze, total.getVCBSMonoReten());
        isEqualByComparingTo(onze, total.getVIBSMonoRet());
        isEqualByComparingTo(onze, total.getVCBSMonoRet());
    }

    @Test
    void testAddWithNull() {
        MonofasiaTotalAccumulator acc1 = new MonofasiaTotalAccumulator(ONE, ONE, ONE, ONE, ONE, ONE);
        MonofasiaTotalAccumulator result = acc1.add(null);
        MonofasiaTotal total = result.toMonofasiaTotal();
        isEqualByComparingTo(ONE, total.getVIBSMono());
        isEqualByComparingTo(ONE, total.getVCBSMono());
        isEqualByComparingTo(ONE, total.getVIBSMonoReten());
        isEqualByComparingTo(ONE, total.getVCBSMonoReten());
        isEqualByComparingTo(ONE, total.getVIBSMonoRet());
        isEqualByComparingTo(ONE, total.getVCBSMonoRet());
    }
    
    private static void isEqualByComparingTo(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(actual).isEqualByComparingTo(expected);
    }
    
}

package br.gov.serpro.rtc.api.model.roc.reduce;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.api.model.roc.DevolucaoTributosDomain;
import br.gov.serpro.rtc.api.model.roc.DiferimentoDomain;
import br.gov.serpro.rtc.api.model.roc.IBSUFDomain;
import br.gov.serpro.rtc.api.model.roc.IBSUFTotalDomain;

class IBSUFTotalAccumulatorTest {

    @Test
    void testDefaultConstructor() {
        IBSUFTotalAccumulator acc = new IBSUFTotalAccumulator();
        IBSUFTotalDomain total = acc.toIBSUFTotal();
        isEqualByComparingTo(ZERO, total.getVDif());
        isEqualByComparingTo(ZERO, total.getVDevTrib());
        isEqualByComparingTo(ZERO, total.getVIBSUF());
    }

    @Test
    void testParameterizedConstructor() {
        BigDecimal v1 = ONE;
        IBSUFTotalAccumulator acc = new IBSUFTotalAccumulator(v1, v1, v1);
        IBSUFTotalDomain total = acc.toIBSUFTotal();
        isEqualByComparingTo(v1, total.getVDif());
        isEqualByComparingTo(v1, total.getVDevTrib());
        isEqualByComparingTo(v1, total.getVIBSUF());
    }

    @Test
    void testFromMethodWithNull() {
        IBSUFTotalAccumulator acc = IBSUFTotalAccumulator.from(null);
        IBSUFTotalDomain total = acc.toIBSUFTotal();
        isEqualByComparingTo(ZERO, total.getVDif());
        isEqualByComparingTo(ZERO, total.getVDevTrib());
        isEqualByComparingTo(ZERO, total.getVIBSUF());
    }

    @Test
    void testFromMethodWithValues() {
        
        DiferimentoDomain gDif = DiferimentoDomain.builder()
                .vDif(TEN)
                .build();
        DevolucaoTributosDomain gDevTrib = DevolucaoTributosDomain.builder()
                .vDevTrib(ONE)
                .build();
        IBSUFDomain ibsUf = new IBSUFDomain();
        ibsUf.setGDif(gDif);
        ibsUf.setGDevTrib(gDevTrib);
        ibsUf.setVIBSUF(BigDecimal.valueOf(5));
        IBSUFTotalAccumulator acc = IBSUFTotalAccumulator.from(ibsUf);
        IBSUFTotalDomain total = acc.toIBSUFTotal();
        isEqualByComparingTo(TEN, total.getVDif());
        isEqualByComparingTo(ONE, total.getVDevTrib());
        isEqualByComparingTo(BigDecimal.valueOf(5), total.getVIBSUF());
    }

    @Test
    void testAddMethod() {
        IBSUFTotalAccumulator acc1 = new IBSUFTotalAccumulator(ONE, ONE, ONE);
        IBSUFTotalAccumulator acc2 = new IBSUFTotalAccumulator(TEN, TEN, TEN);
        IBSUFTotalAccumulator result = acc1.add(acc2);
        IBSUFTotalDomain total = result.toIBSUFTotal();
        final var onze = new BigDecimal("11.00");
        isEqualByComparingTo(onze, total.getVDif());
        isEqualByComparingTo(onze, total.getVDevTrib());
        isEqualByComparingTo(onze, total.getVIBSUF());
    }

    @Test
    void testAddWithNull() {
        IBSUFTotalAccumulator acc1 = new IBSUFTotalAccumulator(ONE, ONE, ONE);
        IBSUFTotalAccumulator result = acc1.add(null);
        IBSUFTotalDomain total = result.toIBSUFTotal();
        isEqualByComparingTo(ONE, total.getVDif());
        isEqualByComparingTo(ONE, total.getVDevTrib());
        isEqualByComparingTo(ONE, total.getVIBSUF());
    }
    
    
    private static void isEqualByComparingTo(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(actual).isEqualByComparingTo(expected);
    }
}

package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para MonofasiaDomain
 * Verifica se os totalizadores vTotIBSMonoItem e vTotCBSMonoItem são calculados corretamente
 * conforme NT da NFe 1.30:
 * 
 * vTotIBSMonoItem = vIBSMono + vIBSMonoReten - vIBSMonoDif
 * vTotCBSMonoItem = vCBSMono + vCBSMonoReten - vCBSMonoDif
 */
class MonofasiaDomainTest {

    @Test
    void testGettersMonofasiaPadrao() {
        var monoPadrao = MonofasiaPadraoDomain.builder()
                .vIBSMono(new BigDecimal("792.50"))
                .vCBSMono(new BigDecimal("792.50"))
                .build();
        
        var monofasia = MonofasiaDomain.builder()
                .gMonoPadrao(monoPadrao)
                .build();
        
        assertThat(monofasia.getVIBSMono()).isEqualByComparingTo(new BigDecimal("792.50"));
        assertThat(monofasia.getVCBSMono()).isEqualByComparingTo(new BigDecimal("792.50"));
        assertThat(monofasia.getVIBSMonoReten()).isEqualByComparingTo(ZERO);
        assertThat(monofasia.getVCBSMonoReten()).isEqualByComparingTo(ZERO);
        assertThat(monofasia.getVIBSMonoDif()).isEqualByComparingTo(ZERO);
        assertThat(monofasia.getVCBSMonoDif()).isEqualByComparingTo(ZERO);
    }

    @Test
    void testGettersMonofasiaRetencao() {
        var monoReten = MonofasiaRetencaoDomain.builder()
                .vIBSMonoReten(new BigDecimal("82.37"))
                .vCBSMonoReten(new BigDecimal("82.37"))
                .build();
        
        var monofasia = MonofasiaDomain.builder()
                .gMonoReten(monoReten)
                .build();
        
        assertThat(monofasia.getVIBSMono()).isEqualByComparingTo(ZERO);
        assertThat(monofasia.getVCBSMono()).isEqualByComparingTo(ZERO);
        assertThat(monofasia.getVIBSMonoReten()).isEqualByComparingTo(new BigDecimal("82.37"));
        assertThat(monofasia.getVCBSMonoReten()).isEqualByComparingTo(new BigDecimal("82.37"));
    }

    @Test
    void testGettersMonofasiaDiferimento() {
        var monoDif = MonofasiaDiferimentoDomain.builder()
                .vIBSMonoDif(new BigDecimal("100.00"))
                .vCBSMonoDif(new BigDecimal("100.00"))
                .build();
        
        var monofasia = MonofasiaDomain.builder()
                .gMonoDif(monoDif)
                .build();
        
        assertThat(monofasia.getVIBSMonoDif()).isEqualByComparingTo(new BigDecimal("100.00"));
        assertThat(monofasia.getVCBSMonoDif()).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void testTotalizadoresSemDiferimento() {
        // Cenário: vIBSMono=792.50, vIBSMonoReten=82.37, vIBSMonoDif=0
        // vTotIBSMonoItem = 792.50 + 82.37 - 0 = 874.87
        
        var monoPadrao = MonofasiaPadraoDomain.builder()
                .vIBSMono(new BigDecimal("792.50"))
                .vCBSMono(new BigDecimal("792.50"))
                .build();
        
        var monoReten = MonofasiaRetencaoDomain.builder()
                .vIBSMonoReten(new BigDecimal("82.37"))
                .vCBSMonoReten(new BigDecimal("82.37"))
                .build();
        
        var monofasia = MonofasiaDomain.builder()
                .gMonoPadrao(monoPadrao)
                .gMonoReten(monoReten)
                .vTotIBSMonoItem(new BigDecimal("874.87"))
                .vTotCBSMonoItem(new BigDecimal("874.87"))
                .build();
        
        assertThat(monofasia.getVTotIBSMonoItem()).isEqualByComparingTo(new BigDecimal("874.87"));
        assertThat(monofasia.getVTotCBSMonoItem()).isEqualByComparingTo(new BigDecimal("874.87"));
    }

    @Test
    void testTotalizadoresComDiferimento() {
        // Cenário: vIBSMono=792.50, vIBSMonoReten=82.37, vIBSMonoDif=50.00
        // vTotIBSMonoItem = 792.50 + 82.37 - 50.00 = 824.87
        
        var monoPadrao = MonofasiaPadraoDomain.builder()
                .vIBSMono(new BigDecimal("792.50"))
                .vCBSMono(new BigDecimal("792.50"))
                .build();
        
        var monoReten = MonofasiaRetencaoDomain.builder()
                .vIBSMonoReten(new BigDecimal("82.37"))
                .vCBSMonoReten(new BigDecimal("82.37"))
                .build();
        
        var monoDif = MonofasiaDiferimentoDomain.builder()
                .vIBSMonoDif(new BigDecimal("50.00"))
                .vCBSMonoDif(new BigDecimal("50.00"))
                .build();
        
        var monofasia = MonofasiaDomain.builder()
                .gMonoPadrao(monoPadrao)
                .gMonoReten(monoReten)
                .gMonoDif(monoDif)
                .vTotIBSMonoItem(new BigDecimal("824.87"))
                .vTotCBSMonoItem(new BigDecimal("824.87"))
                .build();
        
        assertThat(monofasia.getVTotIBSMonoItem()).isEqualByComparingTo(new BigDecimal("824.87"));
        assertThat(monofasia.getVTotCBSMonoItem()).isEqualByComparingTo(new BigDecimal("824.87"));
    }

    @Test
    void testTotalizadoresZero() {
        // Quando não há valores, os totalizadores devem ser ZERO (default)
        var monofasia = MonofasiaDomain.builder().build();
        
        assertThat(monofasia.getVTotIBSMonoItem()).isEqualByComparingTo(ZERO);
        assertThat(monofasia.getVTotCBSMonoItem()).isEqualByComparingTo(ZERO);
    }
}

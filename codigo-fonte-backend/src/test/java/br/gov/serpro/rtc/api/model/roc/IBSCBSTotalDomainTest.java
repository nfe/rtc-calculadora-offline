package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Testes para garantir que os campos obrigatórios dos totais 
 * sejam sempre preenchidos, mesmo quando zerados.
 * 
 * Isso é necessário para validação do XML gerado.
 */
class IBSCBSTotalDomainTest {

    @Test
    void testMonofasiaSemTributacaoRegular_DeveConterCamposObrigatoriosZerados() {
        // Criar objeto domain com monofasia
        var objetoDomain = ObjetoDomain.builder()
                .nObj(1)
                .tribCalc(TributosDomain.builder()
                        .IBSCBS(IBSCBSDomain.builder()
                                .CST("620")
                                .cClassTrib("620001")
                                .gIBSCBSMono(MonofasiaDomain.builder()
                                        .gMonoPadrao(MonofasiaPadraoDomain.builder()
                                                .qBCMono(new BigDecimal("1000"))
                                                .adRemIBS(new BigDecimal("0.7925"))
                                                .adRemCBS(new BigDecimal("0.7925"))
                                                .vIBSMono(new BigDecimal("792.50"))
                                                .vCBSMono(new BigDecimal("792.50"))
                                                .build())
                                        .vTotIBSMonoItem(new BigDecimal("792.50"))
                                        .vTotCBSMonoItem(new BigDecimal("792.50"))
                                        .build())
                                .build())
                        .build())
                .build();
        
        List<ObjetoDomain> detalhes = new ArrayList<>();
        detalhes.add(objetoDomain);
        
        // Criar totalizador
        IBSCBSTotalDomain total = IBSCBSTotalDomain.create(detalhes);
        
        // Validações
        assertThat(total).isNotNull();
        assertThat(total.getVBCIBSCBS()).isEqualByComparingTo(ZERO);
        
        // gIBS deve existir sempre
        assertThat(total.getGIBS()).isNotNull();
        assertThat(total.getGIBS().getVIBS()).isEqualByComparingTo(ZERO);
        assertThat(total.getGIBS().getVCredPres()).isEqualByComparingTo(ZERO);
        assertThat(total.getGIBS().getVCredPresCondSus()).isEqualByComparingTo(ZERO);
        
        // gIBSUF deve existir sempre, mesmo que zerado
        assertThat(total.getGIBS().getGIBSUF()).isNotNull();
        assertThat(total.getGIBS().getGIBSUF().getVDif()).isEqualByComparingTo(ZERO);
        assertThat(total.getGIBS().getGIBSUF().getVDevTrib()).isEqualByComparingTo(ZERO);
        assertThat(total.getGIBS().getGIBSUF().getVIBSUF()).isEqualByComparingTo(ZERO);
        
        // gIBSMun deve existir sempre, mesmo que zerado
        assertThat(total.getGIBS().getGIBSMun()).isNotNull();
        assertThat(total.getGIBS().getGIBSMun().getVDif()).isEqualByComparingTo(ZERO);
        assertThat(total.getGIBS().getGIBSMun().getVDevTrib()).isEqualByComparingTo(ZERO);
        assertThat(total.getGIBS().getGIBSMun().getVIBSMun()).isEqualByComparingTo(ZERO);
        
        // gCBS deve existir sempre, mesmo que zerado
        assertThat(total.getGCBS()).isNotNull();
        assertThat(total.getGCBS().getVDif()).isEqualByComparingTo(ZERO);
        assertThat(total.getGCBS().getVDevTrib()).isEqualByComparingTo(ZERO);
        assertThat(total.getGCBS().getVCBS()).isEqualByComparingTo(ZERO);
        assertThat(total.getGCBS().getVCredPres()).isEqualByComparingTo(ZERO);
        assertThat(total.getGCBS().getVCredPresCondSus()).isEqualByComparingTo(ZERO);
        
        // gMono deve existir com valores da monofasia
        assertThat(total.getGMono()).isNotNull();
        assertThat(total.getGMono().getVIBSMono()).isEqualByComparingTo(new BigDecimal("792.50"));
        assertThat(total.getGMono().getVCBSMono()).isEqualByComparingTo(new BigDecimal("792.50"));
    }
}

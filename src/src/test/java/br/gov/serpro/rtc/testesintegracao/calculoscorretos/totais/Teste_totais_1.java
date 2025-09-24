/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.calculoscorretos.totais;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.roc.IBSCBSTotal;
import br.gov.serpro.rtc.api.model.roc.TributosTotais;
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.util.JsonResourceObjectMapper;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_totais_1 {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testCalcularTributos(
            final @Value("classpath:entradas/calculoscorretos/totais/Teste_totais_1.json") Resource resourceFile)
            throws Exception {
        final var operacao = mapper.loadTestJson(resourceFile);
        final var resultado = calculadoraService.calcularTributos(operacao);

        assertThat(resultado).isNotNull();
        final var total = resultado.getTotal();
        assertThat(total).isNotNull();
        
        TributosTotais tribCalc = total.getTribCalc();
        assertThat(tribCalc).isNotNull();

        final var isTot = tribCalc.getISTot();
        assertThat(isTot).isNotNull();
        assertThat(isTot.getVIS()).isEqualByComparingTo("47.30");
        
        var ibscbsTot = tribCalc.getIBSCBSTot();
        assertThat(ibscbsTot).isNotNull();
        assertThat(ibscbsTot.getVBCIBSCBS()).isEqualByComparingTo("647.30");
        
        assertCbsTotal(ibscbsTot);
        assertIbsTotal(ibscbsTot);
        assertMonofasiaTotal(tribCalc);
    }

    private void assertCbsTotal(IBSCBSTotal ibscbsTot) {
        final var gcbs = ibscbsTot.getGCBS();
        assertThat(gcbs).isNotNull();
        
        assertThat(gcbs.getVDevTrib()).isEqualByComparingTo("0.00");
        assertThat(gcbs.getVDif()).isEqualByComparingTo("6.72");
        assertThat(gcbs.getVCBS()).isEqualByComparingTo("37.57");
        
        assertThat(gcbs.getVCredPres()).isEqualByComparingTo("0.00");
        assertThat(gcbs.getVCredPresCondSus()).isEqualByComparingTo("0.00");
    }

    private void assertIbsTotal(IBSCBSTotal ibscbsTot) {
        final var gibs = ibscbsTot.getGIBS();
        assertThat(gibs).isNotNull();
        
        final var gibsuf = gibs.getGIBSUF();
        assertThat(gibsuf).isNotNull();
        assertThat(gibsuf.getVDif()).isEqualByComparingTo("0.04");
        assertThat(gibsuf.getVDevTrib()).isEqualByComparingTo("0.00");
        assertThat(gibsuf.getVIBSUF()).isEqualByComparingTo("0.22");
        
        final var gibsMun = gibs.getGIBSMun();
        assertThat(gibsMun).isNotNull();
        assertThat(gibsMun.getVDif()).isEqualByComparingTo("0.04");
        assertThat(gibsMun.getVDevTrib()).isEqualByComparingTo("0.00");
        assertThat(gibsMun.getVIBSMun()).isEqualByComparingTo("0.22");
        
        assertThat(gibs.getVIBS()).isEqualByComparingTo("0.44");
        assertThat(gibs.getVCredPres()).isEqualByComparingTo("0.00");
        assertThat(gibs.getVCredPresCondSus()).isEqualByComparingTo("0.00");
    }

    private void assertMonofasiaTotal(final TributosTotais total) {
        final var tributoMonofasicoTotal = total.getIBSCBSTot().getGMono();
        assertThat(tributoMonofasicoTotal).isNotNull();
        
        assertThat(tributoMonofasicoTotal.getVCBSMono()).isEqualByComparingTo("14.70");
        assertThat(tributoMonofasicoTotal.getVIBSMono()).isEqualByComparingTo("14.71");
        
        assertThat(tributoMonofasicoTotal.getVCBSMonoRet()).isEqualByComparingTo("0.00");
        assertThat(tributoMonofasicoTotal.getVIBSMonoRet()).isEqualByComparingTo("0.00");
        
        assertThat(tributoMonofasicoTotal.getVCBSMonoReten()).isEqualByComparingTo("0.00");
        assertThat(tributoMonofasicoTotal.getVIBSMonoReten()).isEqualByComparingTo("0.00");
    }
}
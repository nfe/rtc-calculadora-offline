/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.calculoscorretos.cbsibs.cclasstrib_200002;

import static br.gov.serpro.rtc.util.AssertUtils.isEqualByComparingTo;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.roc.CBS;
import br.gov.serpro.rtc.api.model.roc.IBSMun;
import br.gov.serpro.rtc.api.model.roc.IBSUF;
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.util.JsonResourceObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_200002_1 {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testCalcularTributos(
            final @Value("classpath:entradas/calculoscorretos/Teste_200002_1.json") Resource resourceFile)
            throws Exception {
        final var operacao = mapper.loadTestJson(resourceFile);
        final var resultado = calculadoraService.calcularTributos(operacao);

        assertThat(resultado).isNotNull();
        final var objetos = resultado.getObjetos();
        assertThat(objetos).isNotNull().isNotEmpty();

        var item = objetos.get(0);
        assertThat(item).isNotNull();

        isEqualByComparingTo(item.getValorBaseCalculoIBSCBS(), "200.00");
        
        assertCbs(item.getGCBS());
        assertIbsEstadual(item.getGIBSUF());
        assertIbsMunicipal(item.getGIBSMun());
        assertThat(item.getImpostoSeletivo()).isNull();
        
        assertThat(item.getTributacaoRegular()).isNull();
    }

    private void assertCbs(final CBS cbs) {
        assertThat(cbs).isNotNull();
        isEqualByComparingTo(cbs.getAliquota(), ZERO);
        isEqualByComparingTo(cbs.getValorImposto(), ZERO);
    }

    private void assertIbsEstadual(final IBSUF ibsEstadual) {
        assertThat(ibsEstadual).isNotNull();
        isEqualByComparingTo(ibsEstadual.getAliquota(), ZERO);
        isEqualByComparingTo(ibsEstadual.getValorImposto(), ZERO);
    }

    private void assertIbsMunicipal(final IBSMun ibsMunicipal) {
        assertThat(ibsMunicipal).isNotNull();
        isEqualByComparingTo(ibsMunicipal.getAliquota(), ZERO);
        isEqualByComparingTo(ibsMunicipal.getValorImposto(), ZERO);
    }
}
/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.calculoscorretos.cbsibs.cclasstrib_000001;

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
import br.gov.serpro.rtc.api.model.roc.CBSDomain;
import br.gov.serpro.rtc.api.model.roc.ObjetoDomain;
import br.gov.serpro.rtc.api.model.roc.IBSMunDomain;
import br.gov.serpro.rtc.api.model.roc.IBSUFDomain;
import br.gov.serpro.rtc.api.model.roc.TributacaoRegularDomain;
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.util.JsonResourceObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_000001_3 {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testCalcularTributos(
            final @Value("classpath:entradas/calculoscorretos/Teste_000001_3.json") Resource resourceFile)
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
        assertTributacaoRegular(item, "0.90", "1.80", "0.10", "0.20", "0.00", "0.00");
    }

    private void assertCbs(final CBSDomain cbs) {
        assertThat(cbs).isNotNull();
        isEqualByComparingTo(cbs.getPCBS(), "0.90");
        isEqualByComparingTo(cbs.getVCBS(), ZERO);
    }

    private void assertIbsEstadual(final IBSUFDomain ibsEstadual) {
        assertThat(ibsEstadual).isNotNull();
        isEqualByComparingTo(ibsEstadual.getPIBSUF(), "0.10");
        isEqualByComparingTo(ibsEstadual.getVIBSUF(), ZERO);
    }

    private void assertIbsMunicipal(final IBSMunDomain ibsMunicipal) {
        assertThat(ibsMunicipal).isNotNull();
        isEqualByComparingTo(ibsMunicipal.getPIBSMun(), ZERO);
        isEqualByComparingTo(ibsMunicipal.getVIBSMun(), ZERO);
    }

    private void assertTributacaoRegular(final ObjetoDomain item, final String aliquotaRegularCBS,
            final String valorRegularCBS, final String aliquotaRegularIBSUF, final String valorRegularIBSUF,
            final String aliquotaRegularIBSMun, final String valorRegularIBSMun) {
        assertThat(item.possuiTributacaoRegular()).isTrue();
        TributacaoRegularDomain tr = item.getTributacaoRegular();
        isEqualByComparingTo(tr.getPAliqEfetRegCBS(), aliquotaRegularCBS);
        isEqualByComparingTo(tr.getVTribRegCBS(), valorRegularCBS);
        isEqualByComparingTo(tr.getPAliqEfetRegIBSUF(), aliquotaRegularIBSUF);
        isEqualByComparingTo(tr.getVTribRegIBSUF(), valorRegularIBSUF);
        isEqualByComparingTo(tr.getPAliqEfetRegIBSMun(), aliquotaRegularIBSMun);
        isEqualByComparingTo(tr.getVTribRegIBSMun(), valorRegularIBSMun);
    }

}
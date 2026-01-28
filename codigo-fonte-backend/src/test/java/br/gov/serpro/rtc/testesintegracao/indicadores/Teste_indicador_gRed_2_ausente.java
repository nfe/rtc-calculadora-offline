/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.indicadores;

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
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.util.JsonResourceObjectMapper;

/**
 * Teste do indicador de grupo de redução (gRed).
 * 
 * CST 000 - "Tributação integral" possui SITR_IND_GRED = 0.
 * 
 * Quando a situação tributária NÃO possui o indicador de grupo de redução ativado,
 * o grupo gRed NÃO deve ser retornado na resposta da calculadora.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_indicador_gRed_2_ausente {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testIndicadorGRedAusente(
            final @Value("classpath:entradas/indicadores/Teste_indicador_gRed_2_ausente.json") Resource resourceFile)
            throws Exception {
        final var operacao = mapper.loadTestJson(resourceFile);
        final var resultado = calculadoraService.calcularTributos(operacao);

        assertThat(resultado).isNotNull();
        final var objetos = resultado.getObjetos();
        assertThat(objetos).isNotNull().isNotEmpty();

        var item = objetos.get(0);
        assertThat(item).isNotNull();

        // Verifica CBS - NÃO deve ter gRed
        var cbs = item.getGCBS();
        assertThat(cbs).isNotNull();
        assertThat(cbs.getGRed())
            .as("CBS NÃO deve conter grupo gRed pois CST 000 possui SITR_IND_GRED=0")
            .isNull();

        // Verifica IBS UF - NÃO deve ter gRed
        var ibsUf = item.getGIBSUF();
        assertThat(ibsUf).isNotNull();
        assertThat(ibsUf.getGRed())
            .as("IBS UF NÃO deve conter grupo gRed pois CST 000 possui SITR_IND_GRED=0")
            .isNull();

        // Verifica IBS Mun - NÃO deve ter gRed
        var ibsMun = item.getGIBSMun();
        assertThat(ibsMun).isNotNull();
        assertThat(ibsMun.getGRed())
            .as("IBS Mun NÃO deve conter grupo gRed pois CST 000 possui SITR_IND_GRED=0")
            .isNull();
    }
}

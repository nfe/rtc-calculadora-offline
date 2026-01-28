/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.indicadores;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

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
 * CST 200 - "Alíquota reduzida" possui SITR_IND_GRED = 1.
 * 
 * Quando a situação tributária possui o indicador de grupo de redução ativado,
 * o grupo gRed DEVE ser retornado na resposta da calculadora contendo:
 * - pRedAliq: percentual de redução da alíquota
 * - pAliqEfet: alíquota efetiva aplicada
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_indicador_gRed_1_presente {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testIndicadorGRedPresente(
            final @Value("classpath:entradas/indicadores/Teste_indicador_gRed_1_presente.json") Resource resourceFile)
            throws Exception {
        final var operacao = mapper.loadTestJson(resourceFile);
        final var resultado = calculadoraService.calcularTributos(operacao);

        assertThat(resultado).isNotNull();
        final var objetos = resultado.getObjetos();
        assertThat(objetos).isNotNull().isNotEmpty();

        var item = objetos.get(0);
        assertThat(item).isNotNull();

        // Verifica CBS - deve ter gRed
        var cbs = item.getGCBS();
        assertThat(cbs).isNotNull();
        assertThat(cbs.getGRed())
            .as("CBS deve conter grupo gRed pois CST 200 possui SITR_IND_GRED=1")
            .isNotNull();
        assertThat(cbs.getGRed().getPRedAliq())
            .as("Percentual de redução deve estar presente")
            .isNotNull()
            .isGreaterThan(BigDecimal.ZERO);
        assertThat(cbs.getGRed().getPAliqEfet())
            .as("Alíquota efetiva deve estar presente")
            .isNotNull();

        // Verifica IBS UF - deve ter gRed
        var ibsUf = item.getGIBSUF();
        assertThat(ibsUf).isNotNull();
        assertThat(ibsUf.getGRed())
            .as("IBS UF deve conter grupo gRed pois CST 200 possui SITR_IND_GRED=1")
            .isNotNull();
        assertThat(ibsUf.getGRed().getPRedAliq())
            .as("Percentual de redução deve estar presente")
            .isNotNull()
            .isGreaterThan(BigDecimal.ZERO);
        assertThat(ibsUf.getGRed().getPAliqEfet())
            .as("Alíquota efetiva deve estar presente")
            .isNotNull();

        // Verifica IBS Mun - deve ter gRed
        var ibsMun = item.getGIBSMun();
        assertThat(ibsMun).isNotNull();
        assertThat(ibsMun.getGRed())
            .as("IBS Mun deve conter grupo gRed pois CST 200 possui SITR_IND_GRED=1")
            .isNotNull();
        assertThat(ibsMun.getGRed().getPRedAliq())
            .as("Percentual de redução deve estar presente")
            .isNotNull()
            .isGreaterThan(BigDecimal.ZERO);
        assertThat(ibsMun.getGRed().getPAliqEfet())
            .as("Alíquota efetiva deve estar presente")
            .isNotNull();
    }
}

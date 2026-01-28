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
 * Teste do indicador de grupo de redução (gRed) com tributação regular.
 * 
 * Conforme a lógica em CalculoCbsIbsService linha 383:
 * .grupoReducao(!classificacaoTributaria.getSituacaoTributaria().getInGrupoReducao() || temTributacaoRegular ? null : ...)
 * 
 * Quando existe tributação regular (temTributacaoRegular=true), o grupo gRed NÃO deve
 * ser retornado, MESMO QUE a situação tributária possua SITR_IND_GRED=1.
 * 
 * Neste teste, usamos CST 550 (Suspensão) no item principal e tributacaoRegular com
 * CST 200 (que possui SITR_IND_GRED=1), mas o gRed NÃO deve aparecer no resultado
 * principal devido à presença da tributação regular.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_indicador_gRed_3_tributacaoRegular {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testIndicadorGRedComTributacaoRegular(
            final @Value("classpath:entradas/indicadores/Teste_indicador_gRed_3_tributacaoRegular.json") Resource resourceFile)
            throws Exception {
        final var operacao = mapper.loadTestJson(resourceFile);
        final var resultado = calculadoraService.calcularTributos(operacao);

        assertThat(resultado).isNotNull();
        final var objetos = resultado.getObjetos();
        assertThat(objetos).isNotNull().isNotEmpty();

        var item = objetos.get(0);
        assertThat(item).isNotNull();

        // Verifica que existe tributação regular
        assertThat(item.getTributacaoRegular())
            .as("Deve conter grupo de tributação regular")
            .isNotNull();

        // Verifica CBS - NÃO deve ter gRed devido à tributação regular
        var cbs = item.getGCBS();
        assertThat(cbs).isNotNull();
        assertThat(cbs.getGRed())
            .as("CBS NÃO deve conter grupo gRed quando há tributação regular")
            .isNull();

        // Verifica IBS UF - NÃO deve ter gRed devido à tributação regular
        var ibsUf = item.getGIBSUF();
        assertThat(ibsUf).isNotNull();
        assertThat(ibsUf.getGRed())
            .as("IBS UF NÃO deve conter grupo gRed quando há tributação regular")
            .isNull();

        // Verifica IBS Mun - NÃO deve ter gRed devido à tributação regular
        var ibsMun = item.getGIBSMun();
        assertThat(ibsMun).isNotNull();
        assertThat(ibsMun.getGRed())
            .as("IBS Mun NÃO deve conter grupo gRed quando há tributação regular")
            .isNull();
    }
}

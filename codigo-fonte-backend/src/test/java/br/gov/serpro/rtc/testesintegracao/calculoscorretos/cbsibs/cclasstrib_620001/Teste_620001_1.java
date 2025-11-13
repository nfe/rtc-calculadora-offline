/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.calculoscorretos.cbsibs.cclasstrib_620001;


import static java.math.BigDecimal.TEN;
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
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.util.JsonResourceObjectMapper;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_620001_1 {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private CalculadoraService calculadoraService;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }

    @Test
    void testCalcularTributos(
            final @Value("classpath:entradas/calculoscorretos/Teste_620001_1.json") Resource resourceFile)
            throws Exception {
        final var operacao = mapper.loadTestJson(resourceFile);
        final var resultado = calculadoraService.calcularTributos(operacao);
        assertThat(resultado).isNotNull();

        final var objetos = resultado.getObjetos();
        assertThat(objetos).isNotNull().isNotEmpty();
        
        final var item = objetos.get(0);
        assertThat(item).isNotNull();
        
        assertThat(item.possuiBaseCalculoIBSCBS()).isFalse();
        assertThat(item.possuiImpostoSeletivo()).isFalse();
        assertThat(item.possuiCbs()).isFalse();
        assertThat(item.possuiIBSUF()).isFalse();
        assertThat(item.possuiIBSMun()).isFalse();
        assertThat(item.possuiTributacaoRegular()).isFalse();
        
        assertThat(item.possuiMonofasia()).isTrue();
        
        var mono = item.getGIBSCBSMono();
        assertThat(mono).isNotNull();
        
        final var gMonoPadrao = mono.getGMonoPadrao();
        assertThat(gMonoPadrao).isNotNull();
        assertThat(mono.getGMonoPadrao().getQBCMono()).isEqualByComparingTo(TEN);
        assertThat(mono.getGMonoPadrao().getAdRemCBS()).isEqualByComparingTo("1.47");
        assertThat(mono.getGMonoPadrao().getVCBSMono()).isEqualByComparingTo("14.70");
        //assertThat(mono.getAdRemIBS()).isEqualByComparingTo("1.47"); // FIXME problema de aliquotas diferentes para UF e Municipio
        assertThat(mono.getVIBSMono()).isEqualByComparingTo("14.7");

        assertThat(mono.getGMonoReten()).isNull();
        assertThat(mono.getGMonoRet()).isNull();
        assertThat(mono.getGMonoDif()).isNull();
        
        // TODO testar aqui totais gerais - aguardando definição de como serão consolidado os totais
        // assertThat(mono.getVTotCBSMonoItem()).isEqualByComparingTo(ZERO);
        // assertThat(mono.getVTotIBSMonoItem()).isEqualByComparingTo(ZERO);        
    }

}
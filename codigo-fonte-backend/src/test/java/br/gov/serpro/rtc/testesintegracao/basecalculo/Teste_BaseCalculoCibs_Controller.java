/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesintegracao.basecalculo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoCibsInput;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_BaseCalculoCibs_Controller {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void teste_calculoBasicoComSucesso() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("100.00"));
        input.setAjusteAcrescimos(new BigDecimal("10.00"));
        input.setJuros(new BigDecimal("5.00"));
        input.setIcms(new BigDecimal("15.00"));

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(100.00));
    }

    @Test
    void teste_todosCamposQueIntegram() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setAjusteAcrescimos(new BigDecimal("50.00"));
        input.setJuros(new BigDecimal("20.00"));
        input.setMultas(new BigDecimal("10.00"));
        input.setEncargos(new BigDecimal("30.00"));
        input.setFrete(new BigDecimal("40.00"));
        input.setImpostoSeletivo(new BigDecimal("100.00"));
        input.setOutrosTributos(new BigDecimal("25.00"));
        input.setDemaisImportancias(new BigDecimal("15.00"));

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(1290.00));
    }

    @Test
    void teste_todosCamposQueNaoIntegram() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2026);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));
        input.setPis(new BigDecimal("16.50"));
        input.setPisImportacao(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("76.00"));
        input.setCofinsImportacao(new BigDecimal("20.00"));
        input.setCosip(new BigDecimal("5.00"));
        input.setIpi(new BigDecimal("150.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(522.50));
    }

    @Test
    void teste_baseNegativaRetornaErro() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("100.00"));
        input.setIcms(new BigDecimal("200.00"));

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void teste_valoresZeroRetornaZero() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        // Todos os campos já inicializam com BigDecimal.ZERO

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(0.00));
    }

    @Test
    void teste_impostoSeletivoIntegraBase() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("500.00"));
        input.setImpostoSeletivo(new BigDecimal("100.00"));
        input.setIcms(new BigDecimal("50.00"));

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(550.00));
    }

    @Test
    void teste_novosCamposLC214() throws Exception {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("500.00"));;
        input.setCosip(new BigDecimal("5.00"));
        input.setIpi(new BigDecimal("50.00"));
        input.setDescontoIncondicional(new BigDecimal("15.00"));

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(430.00));
    }

    @Test
    void teste_camposRemovidosNaoExistemMais() throws Exception {
        // Este teste verifica que o JSON sem os campos antigos é aceito normalmente
        String jsonSemCamposAntigos = """
            {
                "valorBem": 100.00,
                "anoFatoGerador": 2027,
                "ajusteAcrescimos": 10.00,
                "icms": 10.00
            }
            """;

        mockMvc.perform(post("/calculadora/base-calculo/cbs-ibs-mercadorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSemCamposAntigos))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(100.00));
    }
}

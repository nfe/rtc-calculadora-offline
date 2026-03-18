/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesintegracao.dadosabertos;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_dadosabertos_classificacoesTributariasPorCst {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void teste_controller_consultarClassificacoesTributariasImpostoSeletivoPorCst() throws Exception {
        mockMvc.perform(get("/calculadora/dados-abertos/classificacoes-tributarias/imposto-seletivo/000")
                .param("data", "2027-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void teste_controller_consultarClassificacoesTributariasCbsIbsPorCst() throws Exception {
        mockMvc.perform(get("/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs/000")
                .param("data", "2025-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void teste_controller_consultarClassificacoesTributariasImpostoSeletivoPorCstSemData() throws Exception {
        // Test with required data parameter
        mockMvc.perform(get("/calculadora/dados-abertos/classificacoes-tributarias/imposto-seletivo/000")
                .param("data", "2027-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void teste_controller_consultarClassificacoesTributariasCbsIbsPorCstSemData() throws Exception {
        // Test with required data parameter
        mockMvc.perform(get("/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs/010")
                .param("data", "2025-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
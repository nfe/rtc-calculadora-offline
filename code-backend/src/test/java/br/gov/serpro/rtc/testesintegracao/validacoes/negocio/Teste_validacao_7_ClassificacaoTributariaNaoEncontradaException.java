/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesintegracao.validacoes.negocio;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoEncontradaException;
import br.gov.serpro.rtc.util.JsonResourceObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_validacao_7_ClassificacaoTributariaNaoEncontradaException {

    private static JsonResourceObjectMapper<OperacaoInput> mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CalculadoraService calculadoraService;
    
    private OperacaoInput operacao;

    @BeforeAll
    static void setup() {
        mapper = new JsonResourceObjectMapper<>(OperacaoInput.class);
    }
    
    @BeforeEach
    void beforeEach(
            final @Value("classpath:entradas/validacoes/Teste_validacao_7_ClassificacaoTributariaNaoEncontradaException.json") Resource resourceFile)
            throws IOException {
        operacao = mapper.loadTestJson(resourceFile);
    }

    @Test
    void teste_service_CalcularTributos() {
        assertThatThrownBy(() -> calculadoraService.calcularTributos(operacao))
                .isExactlyInstanceOf(ClassificacaoTributariaNaoEncontradaException.class);
    }

    @Test
    void teste_controller_CalcularTributos() throws Exception {
        final String jsonContent = objectMapper.writeValueAsString(operacao);
        mockMvc.perform(post("/calculadora/regime-geral")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.title").value("Classificação tributária não encontrada"));
    }

}
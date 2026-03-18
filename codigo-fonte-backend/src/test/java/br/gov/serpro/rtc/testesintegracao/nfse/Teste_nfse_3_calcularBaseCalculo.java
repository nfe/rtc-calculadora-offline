/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesintegracao.nfse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serpro.rtc.api.model.input.nfse.NfseBaseCalculoInput;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-testes.yml")
@ActiveProfiles("testes")
class Teste_nfse_3_calcularBaseCalculo {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void teste_controller_calcularBaseCalculoComSucesso() throws Exception {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new java.math.BigDecimal("100.00"));
        input.setDescontoIncondicional(new java.math.BigDecimal("5.00"));
        input.setVCalcReeRepRes(new java.math.BigDecimal("6.00"));
        input.setVCalcDedRedIBSCBS(new java.math.BigDecimal("4.00"));
        input.setIss(new java.math.BigDecimal("5.00"));
        input.setPis(new java.math.BigDecimal("1.65"));
        input.setCofins(new java.math.BigDecimal("7.60"));
        input.setAnoFatoGerador(2026);

        mockMvc.perform(post("/calculadora/nfse/base-calculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(70.75));
    }

    @Test
    void teste_controller_calcularBaseCalculoDataApartir2033() throws Exception {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new java.math.BigDecimal("100.00"));
        input.setDescontoIncondicional(new java.math.BigDecimal("5.00"));
        input.setVCalcReeRepRes(new java.math.BigDecimal("3.00"));
        input.setVCalcDedRedIBSCBS(new java.math.BigDecimal("2.00"));
        input.setAnoFatoGerador(2033);

        mockMvc.perform(post("/calculadora/nfse/base-calculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseCalculo").value(90.00));
    }

    @Test
    void teste_controller_calcularBaseCalculoBaseNegativa() throws Exception {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new java.math.BigDecimal("10.00"));
        input.setDescontoIncondicional(new java.math.BigDecimal("50.00"));
        input.setVCalcReeRepRes(new java.math.BigDecimal("0.00"));
        input.setVCalcDedRedIBSCBS(new java.math.BigDecimal("0.00"));
        input.setIss(new java.math.BigDecimal("0.00"));
        input.setPis(new java.math.BigDecimal("0.00"));
        input.setCofins(new java.math.BigDecimal("0.00"));
        input.setAnoFatoGerador(2026);

        mockMvc.perform(post("/calculadora/nfse/base-calculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest()); // Business logic exception returns 400
    }

    @Test
    void teste_controller_calcularBaseCalculoValoresNulos() throws Exception {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        // Não configurar valor do serviço (obrigatório)
        input.setAnoFatoGerador(2026);

        mockMvc.perform(post("/calculadora/nfse/base-calculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }
}
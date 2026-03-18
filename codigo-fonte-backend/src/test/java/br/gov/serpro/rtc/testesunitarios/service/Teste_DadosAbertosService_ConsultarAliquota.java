/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesunitarios.service;

import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.ACRESCIMO;
import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.DECRESCIMO;
import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.SUBSTITUICAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gov.serpro.rtc.api.model.output.dadosabertos.AliquotaDadosAbertosOutput;
import br.gov.serpro.rtc.domain.model.dto.AliquotaResultadoDTO;
import br.gov.serpro.rtc.domain.service.AliquotaPadraoService;
import br.gov.serpro.rtc.domain.service.dadosabertos.DadosAbertosService;

/**
 * Testes unitários para o método consultarAliquota de DadosAbertosService
 */
@ExtendWith(MockitoExtension.class)
public class Teste_DadosAbertosService_ConsultarAliquota {

    @Mock
    private AliquotaPadraoService aliquotaPadraoService;

    @InjectMocks
    private DadosAbertosService dadosAbertosService;

    private Long idTributo;
    private Long codigoUf;
    private Long codigoMunicipio;
    private LocalDate data;

    @BeforeEach
    public void setup() {
        idTributo = 2L; // CBS
        codigoUf = 35L; // SP
        codigoMunicipio = 3550308L; // São Paulo
        data = LocalDate.of(2026, 1, 15);
    }

    @Test
    public void teste_ConsultarAliquota_SemValorPadrao_DeveRetornarApenasReferencia() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("12.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada");
        assertNull(resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser nula quando não há valor padrão");
        assertNull(resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser nula quando não há valor padrão");
        
        verify(aliquotaPadraoService, times(1))
            .buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data));
    }

    @Test
    public void teste_ConsultarAliquota_ComAcrescimo_DeveRetornarAliquotaPropria() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.00");
        BigDecimal valorPadrao = new BigDecimal("2.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, ACRESCIMO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada");
        assertEquals(new BigDecimal("12.00"), resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser valorAplicavel (10 + 2 = 12)");
        assertEquals(ACRESCIMO, resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser ACRESCIMO");
        
        verify(aliquotaPadraoService, times(1))
            .buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data));
    }

    @Test
    public void teste_ConsultarAliquota_ComDecrescimo_DeveRetornarAliquotaPropria() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("15.00");
        BigDecimal valorPadrao = new BigDecimal("3.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, DECRESCIMO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada");
        assertEquals(new BigDecimal("12.00"), resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser valorAplicavel (15 - 3 = 12)");
        assertEquals(DECRESCIMO, resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser DECRESCIMO");
        
        verify(aliquotaPadraoService, times(1))
            .buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data));
    }

    @Test
    public void teste_ConsultarAliquota_ComSubstituicao_DeveRetornarAliquotaPropria() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.00");
        BigDecimal valorPadrao = new BigDecimal("8.50");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, SUBSTITUICAO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada");
        assertEquals(valorPadrao, resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser o valorPadrao (8.50)");
        assertEquals(SUBSTITUICAO, resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser SUBSTITUICAO");
        
        verify(aliquotaPadraoService, times(1))
            .buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data));
    }

    @Test
    public void teste_ConsultarAliquota_ComValorPadraoZero_ComAcrescimo_DeveRetornarZero() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("12.00");
        BigDecimal valorPadrao = new BigDecimal("0.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, ACRESCIMO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada");
        assertEquals(new BigDecimal("12.00"), resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser valorAplicavel (12 + 0 = 12)");
        assertEquals(ACRESCIMO, resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser ACRESCIMO");
    }

    @Test
    public void teste_ConsultarAliquota_ComValoresDecimais_DevePreservarPrecisao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("12.345");
        BigDecimal valorPadrao = new BigDecimal("1.155");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, ACRESCIMO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve preservar precisão");
        assertEquals(new BigDecimal("13.500"), resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser valorAplicavel (12.345 + 1.155 = 13.500)");
    }

    @Test
    public void teste_ConsultarAliquota_ParaDiferentesTributos_DeveRetornarCorreto() {
        // Arrange - IBS (tributo 3)
        Long idTributoIbs = 3L;
        BigDecimal valorReferencia = new BigDecimal("17.50");
        BigDecimal valorPadrao = new BigDecimal("0.50");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, DECRESCIMO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributoIbs), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributoIbs, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada para IBS");
        assertEquals(new BigDecimal("17.00"), resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser valorAplicavel (17.50 - 0.50 = 17.00)");
        assertEquals(DECRESCIMO, resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser DECRESCIMO");
    }

    @Test
    public void teste_ConsultarAliquota_ParaDiferentesUfs_DeveRetornarCorreto() {
        // Arrange - Rio de Janeiro (código 33)
        Long codigoUfRj = 33L;
        Long codigoMunicipioRj = 3304557L; // Rio de Janeiro
        BigDecimal valorReferencia = new BigDecimal("11.00");
        BigDecimal valorPadrao = new BigDecimal("9.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, SUBSTITUICAO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUfRj), eq(codigoMunicipioRj), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUfRj, codigoMunicipioRj, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada para RJ");
        assertEquals(valorPadrao, resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser o valorPadrao (9.00)");
    }

    @Test
    public void teste_ConsultarAliquota_ParaDiferentesDatas_DeveRetornarCorreto() {
        // Arrange - Data futura
        LocalDate dataFutura = LocalDate.of(2027, 6, 15);
        BigDecimal valorReferencia = new BigDecimal("13.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(dataFutura)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, dataFutura);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada para data futura");
        assertNull(resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser nula quando não há valor padrão");
    }

    @Test
    public void teste_ConsultarAliquota_ComSubstituicaoZero_DeveRetornarZero() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.00");
        BigDecimal valorPadrao = BigDecimal.ZERO;
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
            valorReferencia, valorPadrao, SUBSTITUICAO);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        AliquotaDadosAbertosOutput resultado = dadosAbertosService.consultarAliquota(
            idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert
        assertNotNull(resultado, "Resultado não deve ser nulo");
        assertEquals(valorReferencia, resultado.getAliquotaReferencia(), 
            "Alíquota de referência deve ser retornada");
        assertEquals(BigDecimal.ZERO, resultado.getAliquotaPropria(), 
            "Alíquota própria deve ser zero quando substituída por zero");
        assertEquals(SUBSTITUICAO, resultado.getFormaAplicacao(), 
            "Forma de aplicação deve ser SUBSTITUICAO");
    }

    @Test
    public void teste_ConsultarAliquota_VerificaChamadaCorretaAoService() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("12.00");
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        when(aliquotaPadraoService.buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data)))
            .thenReturn(aliquotaDTO);
        
        // Act
        dadosAbertosService.consultarAliquota(idTributo, codigoUf, codigoMunicipio, data);
        
        // Assert - Verifica se o service foi chamado com os parâmetros corretos
        verify(aliquotaPadraoService, times(1))
            .buscarAliquota(eq(idTributo), eq(codigoUf), eq(codigoMunicipio), eq(data));
    }
}

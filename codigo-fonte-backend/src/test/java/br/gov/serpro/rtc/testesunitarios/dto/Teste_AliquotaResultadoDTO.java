/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesunitarios.dto;

import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.ACRESCIMO;
import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.DECRESCIMO;
import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.SUBSTITUICAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.domain.model.dto.AliquotaResultadoDTO;
import br.gov.serpro.rtc.domain.service.exception.AliquotaNegativaException;
import br.gov.serpro.rtc.domain.service.exception.AliquotaPadraoNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.AliquotaReferenciaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoDefinidaException;

/**
 * Testes unitários para AliquotaResultadoDTO
 */
public class Teste_AliquotaResultadoDTO {

    @Test
    public void teste_CriacaoComApenasValorReferencia_DeveFuncionar() {
        // Arrange & Act
        BigDecimal valorReferencia = new BigDecimal("10.50");
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        // Assert
        assertNotNull(dto, "DTO não deve ser nulo");
        assertEquals(valorReferencia, dto.valorReferencia(), "Valor de referência deve ser preservado");
        assertEquals(null, dto.valorPadrao(), "Valor padrão deve ser nulo");
        assertEquals(null, dto.formaAplicacao(), "Forma de aplicação deve ser nula");
    }

    @Test
    public void teste_ValorAplicavel_SemValorPadrao_DeveRetornarValorReferencia() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("15.75");
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        // Act
        BigDecimal valorAplicavel = dto.valorAplicavel();
        
        // Assert
        assertEquals(valorReferencia, valorAplicavel, 
            "Quando não há valor padrão, valorAplicavel deve retornar valor de referência");
    }

    @Test
    public void teste_CriacaoComAcrescimo_DeveAplicarCorretamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("5.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("105.00"), dto.valorPadrao(), 
            "Valor padrão deve ser soma de referência + padrão quando forma é ACRESCIMO");
        assertEquals(valorReferencia, dto.valorReferencia(), 
            "Valor de referência deve permanecer inalterado");
    }

    @Test
    public void teste_ValorAplicavel_ComAcrescimo_DeveRetornarValorPadraoCalculado() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("50.00");
        BigDecimal valorPadrao = new BigDecimal("10.00");
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Act
        BigDecimal valorAplicavel = dto.valorAplicavel();
        
        // Assert
        assertEquals(new BigDecimal("60.00"), valorAplicavel, 
            "Valor aplicável deve ser o valor padrão calculado (50 + 10)");
    }

    @Test
    public void teste_CriacaoComDecrescimo_DeveAplicarCorretamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("15.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("85.00"), dto.valorPadrao(), 
            "Valor padrão deve ser diferença de referência - padrão quando forma é DECRESCIMO");
        assertEquals(valorReferencia, dto.valorReferencia(), 
            "Valor de referência deve permanecer inalterado");
    }

    @Test
    public void teste_ValorAplicavel_ComDecrescimo_DeveRetornarValorPadraoCalculado() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("80.00");
        BigDecimal valorPadrao = new BigDecimal("20.00");
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Act
        BigDecimal valorAplicavel = dto.valorAplicavel();
        
        // Assert
        assertEquals(new BigDecimal("60.00"), valorAplicavel, 
            "Valor aplicável deve ser o valor padrão calculado (80 - 20)");
    }

    @Test
    public void teste_CriacaoComValorReferenciaNull_DeveLancarExcecao() {
        // Arrange
        BigDecimal valorReferencia = null;
        BigDecimal valorPadrao = new BigDecimal("10.00");
        
        // Act & Assert
        assertThrows(AliquotaReferenciaNaoEncontradaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO),
            "Deve lançar AliquotaReferenciaNaoEncontradaException quando valorReferencia é nulo");
    }

    @Test
    public void teste_CriacaoComValorPadraoSemFormaAplicacao_DeveLancarExcecao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("10.00");
        
        // Act & Assert
        assertThrows(FormaAplicacaoNaoDefinidaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, valorPadrao, null),
            "Deve lançar FormaAplicacaoNaoDefinidaException quando valorPadrao está definido mas formaAplicacao é nula");
    }

    @Test
    public void teste_CriacaoComFormaAplicacaoSemValorPadrao_DeveLancarExcecao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = null;
        
        // Act & Assert
        assertThrows(AliquotaPadraoNaoEncontradaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO),
            "Deve lançar AliquotaPadraoNaoEncontradaException quando formaAplicacao está definida mas valorPadrao é nulo");
    }

    @Test
    public void teste_AcrescimoComValoresDecimais_DeveCalcularPrecisamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("12.345");
        BigDecimal valorPadrao = new BigDecimal("2.655");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("15.000"), dto.valorPadrao(), 
            "Cálculo com decimais deve ser preciso");
    }

    @Test
    public void teste_DecrescimoComValoresDecimais_DeveCalcularPrecisamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("25.750");
        BigDecimal valorPadrao = new BigDecimal("5.250");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("20.500"), dto.valorPadrao(), 
            "Cálculo com decimais deve ser preciso");
    }

    @Test
    public void teste_AcrescimoComValorZero_DeveManterValorReferencia() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("50.00");
        BigDecimal valorPadrao = BigDecimal.ZERO;
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("50.00"), dto.valorPadrao(), 
            "Acréscimo de zero deve resultar no valor de referência");
        assertEquals(new BigDecimal("50.00"), dto.valorAplicavel(), 
            "Valor aplicável deve ser igual ao valor de referência");
    }

    @Test
    public void teste_DecrescimoComValorZero_DeveManterValorReferencia() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("50.00");
        BigDecimal valorPadrao = BigDecimal.ZERO;
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("50.00"), dto.valorPadrao(), 
            "Decréscimo de zero deve resultar no valor de referência");
        assertEquals(new BigDecimal("50.00"), dto.valorAplicavel(), 
            "Valor aplicável deve ser igual ao valor de referência");
    }

    @Test
    public void teste_ValorReferenciaNegativo_DeveLancarExcecao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("-10.00");
        
        // Act & Assert
        assertThrows(AliquotaNegativaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, null, null),
            "Deve lançar AliquotaNegativaException quando valorReferencia é negativo");
    }

    @Test
    public void teste_ValorPadraoNegativo_DeveLancarExcecao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("-10.00");
        
        // Act & Assert
        assertThrows(AliquotaNegativaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO),
            "Deve lançar AliquotaNegativaException quando valorPadrao é negativo");
    }

    @Test
    public void teste_DecrescimoResultadoNegativo_DeveLancarExcecao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.00");
        BigDecimal valorPadrao = new BigDecimal("15.00");
        
        // Act & Assert
        assertThrows(AliquotaNegativaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO),
            "Deve lançar AliquotaNegativaException quando resultado do decréscimo é negativo");
    }

    @Test
    public void teste_ValoresComMuitasCasasDecimais_DevePreservarPrecisao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("123.456789");
        BigDecimal valorPadrao = new BigDecimal("0.000001");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("123.456790"), dto.valorPadrao(), 
            "Deve preservar precisão com muitas casas decimais");
    }

    @Test
    public void teste_RecordEquality_MesmosValores_DeveSerIgual() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("10.00");
        
        AliquotaResultadoDTO dto1 = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        AliquotaResultadoDTO dto2 = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Act & Assert
        assertEquals(dto1, dto2, "DTOs com mesmos valores devem ser iguais");
        assertEquals(dto1.hashCode(), dto2.hashCode(), "HashCodes devem ser iguais");
    }

    @Test
    public void teste_RecordToString_DeveConterInformacoes() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("50.00");
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        // Act
        String toString = dto.toString();
        
        // Assert
        assertNotNull(toString, "toString não deve ser nulo");
        assertEquals(true, toString.contains("50.00"), 
            "toString deve conter o valor de referência");
    }

    @Test
    public void teste_CriacaoComSubstituicao_DeveManterValorPadrao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("25.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("25.00"), dto.valorPadrao(), 
            "Valor padrão deve permanecer inalterado quando forma é SUBSTITUICAO");
        assertEquals(valorReferencia, dto.valorReferencia(), 
            "Valor de referência deve permanecer inalterado");
    }

    @Test
    public void teste_ValorAplicavel_ComSubstituicao_DeveRetornarValorPadrao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("50.00");
        BigDecimal valorPadrao = new BigDecimal("30.00");
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Act
        BigDecimal valorAplicavel = dto.valorAplicavel();
        
        // Assert
        assertEquals(new BigDecimal("30.00"), valorAplicavel, 
            "Valor aplicável deve ser o valor padrão (substituição) e não a referência");
    }

    @Test
    public void teste_SubstituicaoComValorMaiorQueReferencia_DevePermitir() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.00");
        BigDecimal valorPadrao = new BigDecimal("50.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("50.00"), dto.valorPadrao(), 
            "Valor padrão pode ser maior que referência em SUBSTITUICAO");
        assertEquals(new BigDecimal("50.00"), dto.valorAplicavel(), 
            "Valor aplicável deve ser o valor padrão substituto");
    }

    @Test
    public void teste_SubstituicaoComValorMenorQueReferencia_DevePermitir() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("80.00");
        BigDecimal valorPadrao = new BigDecimal("15.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("15.00"), dto.valorPadrao(), 
            "Valor padrão pode ser menor que referência em SUBSTITUICAO");
        assertEquals(new BigDecimal("15.00"), dto.valorAplicavel(), 
            "Valor aplicável deve ser o valor padrão substituto");
    }

    @Test
    public void teste_SubstituicaoComValoresDecimais_DevePreservarPrecisao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("45.678");
        BigDecimal valorPadrao = new BigDecimal("12.345");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("12.345"), dto.valorPadrao(), 
            "Valor padrão deve preservar precisão decimal em SUBSTITUICAO");
        assertEquals(new BigDecimal("12.345"), dto.valorAplicavel(), 
            "Valor aplicável deve preservar precisão decimal");
    }

    @Test
    public void teste_SubstituicaoComValorZero_DevePermitir() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("50.00");
        BigDecimal valorPadrao = BigDecimal.ZERO;
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(BigDecimal.ZERO, dto.valorPadrao(), 
            "Valor padrão pode ser zero em SUBSTITUICAO");
        assertEquals(BigDecimal.ZERO, dto.valorAplicavel(), 
            "Valor aplicável deve ser zero quando substituído por zero");
    }

    @Test
    public void teste_AcrescimoComValoresGrandes_DeveCalcularCorretamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("9999999.99");
        BigDecimal valorPadrao = new BigDecimal("0.01");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("10000000.00"), dto.valorPadrao(), 
            "Deve calcular corretamente com valores grandes");
        assertEquals(new BigDecimal("10000000.00"), dto.valorAplicavel(), 
            "Valor aplicável deve refletir o cálculo com valores grandes");
    }

    @Test
    public void teste_DecrescimoComValoresIguais_DeveResultarZero() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("100.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("0.00"), dto.valorPadrao(), 
            "Decréscimo de valor igual deve resultar em zero");
        assertEquals(new BigDecimal("0.00"), dto.valorAplicavel(), 
            "Valor aplicável deve ser zero");
    }

    @Test
    public void teste_AcrescimoComValorReferenciaZero_DeveResultarValorPadrao() {
        // Arrange
        BigDecimal valorReferencia = BigDecimal.ZERO;
        BigDecimal valorPadrao = new BigDecimal("25.00");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("25.00"), dto.valorPadrao(), 
            "Acréscimo a zero deve resultar no próprio valor padrão");
        assertEquals(new BigDecimal("25.00"), dto.valorAplicavel(), 
            "Valor aplicável deve ser o valor padrão");
    }

    @Test
    public void teste_DecrescimoComValorReferenciaZero_DeveResultarNegativo() {
        // Arrange
        BigDecimal valorReferencia = BigDecimal.ZERO;
        BigDecimal valorPadrao = new BigDecimal("10.00");
        
        // Act & Assert
        assertThrows(AliquotaNegativaException.class, 
            () -> new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO),
            "Decréscimo de valor maior que zero deve lançar exceção de valor negativo");
    }

    @Test
    public void teste_SubstituicaoComValorIgualReferencia_DevePermitir() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("75.50");
        BigDecimal valorPadrao = new BigDecimal("75.50");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("75.50"), dto.valorPadrao(), 
            "Substituição por valor igual à referência deve funcionar");
        assertEquals(new BigDecimal("75.50"), dto.valorAplicavel(), 
            "Valor aplicável deve ser igual ao substituído");
    }

    @Test
    public void teste_AcrescimoComMuitasCasasDecimais_DevePreservarTodasCasas() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("1.123456789");
        BigDecimal valorPadrao = new BigDecimal("2.876543211");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("4.000000000"), dto.valorPadrao(), 
            "Deve preservar todas as casas decimais no cálculo");
    }

    @Test
    public void teste_DecrescimoComResultadoMuitoPequeno_DevePreservarPrecisao() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.000001");
        BigDecimal valorPadrao = new BigDecimal("10.000000");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("0.000001"), dto.valorPadrao(), 
            "Deve preservar precisão com resultado muito pequeno");
        assertEquals(new BigDecimal("0.000001"), dto.valorAplicavel(), 
            "Valor aplicável deve preservar a precisão");
    }

    @Test
    public void teste_ValorReferenciaZeroSemValorPadrao_DevePermitir() {
        // Arrange
        BigDecimal valorReferencia = BigDecimal.ZERO;
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, null, null);
        
        // Assert
        assertEquals(BigDecimal.ZERO, dto.valorReferencia(), 
            "Valor de referência zero deve ser permitido");
        assertEquals(BigDecimal.ZERO, dto.valorAplicavel(), 
            "Valor aplicável deve ser zero quando não há valor padrão");
    }

    @Test
    public void teste_AcrescimoComValoresFracionarios_DeveCalcularCorretamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("0.5");
        BigDecimal valorPadrao = new BigDecimal("0.3");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("0.8"), dto.valorPadrao(), 
            "Deve calcular corretamente com valores fracionários");
        assertEquals(new BigDecimal("0.8"), dto.valorAplicavel(), 
            "Valor aplicável deve refletir o cálculo com fracionários");
    }

    @Test
    public void teste_DecrescimoComValoresFracionarios_DeveCalcularCorretamente() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("0.9");
        BigDecimal valorPadrao = new BigDecimal("0.4");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("0.5"), dto.valorPadrao(), 
            "Deve calcular corretamente com valores fracionários");
        assertEquals(new BigDecimal("0.5"), dto.valorAplicavel(), 
            "Valor aplicável deve refletir o cálculo com fracionários");
    }

    @Test
    public void teste_SubstituicaoComValorFracionario_DevePreservar() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("10.0");
        BigDecimal valorPadrao = new BigDecimal("0.25");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("0.25"), dto.valorPadrao(), 
            "Substituição deve preservar valor fracionário");
        assertEquals(new BigDecimal("0.25"), dto.valorAplicavel(), 
            "Valor aplicável deve ser o fracionário substituído");
    }

    @Test
    public void teste_ValorReferenciaUmCentavo_ComAcrescimo_DeveCalcular() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("0.01");
        BigDecimal valorPadrao = new BigDecimal("0.01");
        
        // Act
        AliquotaResultadoDTO dto = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        
        // Assert
        assertEquals(new BigDecimal("0.02"), dto.valorPadrao(), 
            "Deve calcular corretamente com valores mínimos (1 centavo)");
    }

    @Test
    public void teste_MultiplasCriacoesComMesmosValores_DevemSerIndependentes() {
        // Arrange
        BigDecimal valorReferencia = new BigDecimal("100.00");
        BigDecimal valorPadrao = new BigDecimal("10.00");
        
        // Act
        AliquotaResultadoDTO dto1 = new AliquotaResultadoDTO(valorReferencia, valorPadrao, ACRESCIMO);
        AliquotaResultadoDTO dto2 = new AliquotaResultadoDTO(valorReferencia, valorPadrao, DECRESCIMO);
        AliquotaResultadoDTO dto3 = new AliquotaResultadoDTO(valorReferencia, valorPadrao, SUBSTITUICAO);
        
        // Assert
        assertEquals(new BigDecimal("110.00"), dto1.valorPadrao(), 
            "DTO1 com ACRESCIMO deve ter valor correto");
        assertEquals(new BigDecimal("90.00"), dto2.valorPadrao(), 
            "DTO2 com DECRESCIMO deve ter valor correto");
        assertEquals(new BigDecimal("10.00"), dto3.valorPadrao(), 
            "DTO3 com SUBSTITUICAO deve ter valor correto");
    }
}

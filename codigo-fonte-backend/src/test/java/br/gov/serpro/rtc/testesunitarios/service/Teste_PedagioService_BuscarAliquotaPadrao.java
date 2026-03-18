package br.gov.serpro.rtc.testesunitarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gov.serpro.rtc.domain.model.dto.AliquotaResultadoDTO;
import br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum;
import br.gov.serpro.rtc.domain.model.enumeration.TributoEnum;
import br.gov.serpro.rtc.domain.service.AliquotaPadraoService;
import br.gov.serpro.rtc.domain.service.UfService;
import br.gov.serpro.rtc.domain.service.pedagio.PedagioService;

/**
 * Testes unitários para o método buscarAliquotaPadrao da classe PedagioService.
 * 
 * O método recebe alíquotas em formato percentual direto (ex: 12 = 12%) e retorna dividido por 100 (ex: 0.12).
 * 
 * Comportamento:
 * 1. Busca AliquotaResultadoDTO via AliquotaPadraoService
 * 2. Obtém valorAplicavel() que já considera a formaAplicacao (SUBSTITUICAO, ACRESCIMO ou DECRESCIMO)
 * 3. Divide por 100 para converter de percentual para decimal (12% → 0.12)
 * 4. Aplica escala de 8 casas decimais com HALF_UP
 */
@ExtendWith(MockitoExtension.class)
class Teste_PedagioService_BuscarAliquotaPadrao {

    @Mock
    private AliquotaPadraoService aliquotaPadraoService;

    @Mock
    private UfService ufService;

    @InjectMocks
    private PedagioService pedagioService;

    private LocalDate dataOperacao;

    @BeforeEach
    void setUp() {
        dataOperacao = LocalDate.of(2026, 1, 15);
    }

    @Test
    void teste_BuscarAliquotaPadrao_CBS_AliquotaNormal() {
        // Cenário: CBS com alíquota de 12% (valor informado já é 12)
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("12"), // 12%
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS,
                dataOperacao,
                null,
                null
        );

        // Assert
        assertNotNull(resultado);
        // 12 / 100 = 0.12
        assertEquals(new BigDecimal("0.12000000"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_IBSEstadual_ComCodigoUF() {
        // Cenário: IBS Estadual para São Paulo (UF 35) com alíquota de 17.5%
        Long codigoUfSP = 35L;
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("17.5"), // 17.5%
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.IBS_ESTADUAL.getCodigo()),
                eq(codigoUfSP),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.IBS_ESTADUAL,
                dataOperacao,
                codigoUfSP,
                null
        );

        // Assert
        assertNotNull(resultado);
        // 17.5 / 100 = 0.175
        assertEquals(new BigDecimal("0.17500000"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_IBSMunicipal_ComCodigoMunicipio() {
        // Cenário: IBS Municipal para São Paulo capital com alíquota de 5%
        Long codigoMunicipioSP = 3550308L;
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("5"), // 5%
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.IBS_MUNICIPAL.getCodigo()),
                isNull(),
                eq(codigoMunicipioSP),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.IBS_MUNICIPAL,
                dataOperacao,
                null,
                codigoMunicipioSP
        );

        // Assert
        assertNotNull(resultado);
        // 5 / 100 = 0.05
        assertEquals(new BigDecimal("0.05000000"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_ComAcrescimo() {
        // Cenário: Alíquota referência de 10% + acréscimo de 2% = 12%
        // valorReferencia: 10, valorPadrao: 2
        // valorAplicavel() retorna 10 + 2 = 12
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                new BigDecimal("10"), // 10% referência
                new BigDecimal("2"),  // +2% acréscimo
                FormaAplicacaoEnum.ACRESCIMO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS,
                dataOperacao,
                null,
                null
        );

        // Assert
        assertNotNull(resultado);
        // 12 / 100 = 0.12
        assertEquals(new BigDecimal("0.12000000"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_ComDecrescimo() {
        // Cenário: Alíquota referência de 15% - decréscimo de 3% = 12%
        // valorReferencia: 15, valorPadrao: 3
        // valorAplicavel() retorna 15 - 3 = 12
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                new BigDecimal("15"), // 15% referência
                new BigDecimal("3"),  // -3% decréscimo
                FormaAplicacaoEnum.DECRESCIMO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS,
                dataOperacao,
                null,
                null
        );

        // Assert
        assertNotNull(resultado);
        // 12 / 100 = 0.12
        assertEquals(new BigDecimal("0.12000000"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_AliquotaZero() {
        // Cenário: Alíquota zero (caso raro mas possível)
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS,
                dataOperacao,
                null,
                null
        );

        // Assert
        assertNotNull(resultado);
        // 0 / 100 = 0.00
        assertEquals(new BigDecimal("0.00000000"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_ValorComMuitasDecimais() {
        // Cenário: Valor com muitas casas decimais para testar arredondamento
        // 12.34567890123 / 100 = 0.1234567890123 → arredonda para 0.12345679
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("12.34567890123"),
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS,
                dataOperacao,
                null,
                null
        );

        // Assert
        assertNotNull(resultado);
        assertEquals(new BigDecimal("0.12345679"), resultado);
        assertEquals(8, resultado.scale()); // Verifica que tem exatamente 8 casas decimais
    }

    @Test
    void teste_BuscarAliquotaPadrao_ArredondamentoHalfUp() {
        // Cenário: Testa arredondamento HALF_UP na 8ª casa decimal
        // 12.34123456785 / 100 = 0.1234123456785 → arredonda para 0.12341235
        AliquotaResultadoDTO aliquotaDTO = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("12.34123456785"),
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaDTO);

        // Act
        BigDecimal resultado = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS,
                dataOperacao,
                null,
                null
        );

        // Assert
        assertNotNull(resultado);
        // Última casa arredonda de 6785 para 35 (HALF_UP)
        assertEquals(new BigDecimal("0.12341235"), resultado);
    }

    @Test
    void teste_BuscarAliquotaPadrao_DatasDiferentes() {
        // Cenário: Alíquotas diferentes para datas diferentes
        LocalDate data2025 = LocalDate.of(2025, 12, 31);
        LocalDate data2026 = LocalDate.of(2026, 1, 1);

        AliquotaResultadoDTO aliquota2025 = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("10"), // 10% em 2025
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        AliquotaResultadoDTO aliquota2026 = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("12"), // 12% em 2026
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(data2025)
        )).thenReturn(aliquota2025);

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(data2026)
        )).thenReturn(aliquota2026);

        // Act
        BigDecimal resultado2025 = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS, data2025, null, null
        );

        BigDecimal resultado2026 = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS, data2026, null, null
        );

        // Assert
        // 10 / 100 = 0.10
        assertEquals(new BigDecimal("0.10000000"), resultado2025);
        // 12 / 100 = 0.12
        assertEquals(new BigDecimal("0.12000000"), resultado2026);
    }

    @Test
    void teste_BuscarAliquotaPadrao_TresTributosDiferentes() {
        // Cenário real: Buscar alíquotas para CBS, IBS Estadual e IBS Municipal
        // Simula o fluxo em getTrechosPedagio()
        Long codigoUf = 35L; // SP
        Long codigoMunicipio = 3550308L; // SP Capital

        AliquotaResultadoDTO aliquotaCBS = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("12"), // CBS: 12%
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        AliquotaResultadoDTO aliquotaIBSEstadual = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("17.5"), // IBS Estadual: 17.5%
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        AliquotaResultadoDTO aliquotaIBSMunicipal = new AliquotaResultadoDTO(
                BigDecimal.ZERO,
                new BigDecimal("5"), // IBS Municipal: 5%
                FormaAplicacaoEnum.SUBSTITUICAO
        );

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.CBS.getCodigo()),
                isNull(),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaCBS);

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.IBS_ESTADUAL.getCodigo()),
                eq(codigoUf),
                isNull(),
                eq(dataOperacao)
        )).thenReturn(aliquotaIBSEstadual);

        when(aliquotaPadraoService.buscarAliquota(
                eq(TributoEnum.IBS_MUNICIPAL.getCodigo()),
                isNull(),
                eq(codigoMunicipio),
                eq(dataOperacao)
        )).thenReturn(aliquotaIBSMunicipal);

        // Act
        BigDecimal valorCBS = pedagioService.buscarAliquotaPadrao(
                TributoEnum.CBS, dataOperacao, null, null
        );

        BigDecimal valorIBSEstadual = pedagioService.buscarAliquotaPadrao(
                TributoEnum.IBS_ESTADUAL, dataOperacao, codigoUf, null
        );

        BigDecimal valorIBSMunicipal = pedagioService.buscarAliquotaPadrao(
                TributoEnum.IBS_MUNICIPAL, dataOperacao, null, codigoMunicipio
        );

        // Assert
        // 12 / 100 = 0.12
        assertEquals(new BigDecimal("0.12000000"), valorCBS);
        // 17.5 / 100 = 0.175
        assertEquals(new BigDecimal("0.17500000"), valorIBSEstadual);
        // 5 / 100 = 0.05
        assertEquals(new BigDecimal("0.05000000"), valorIBSMunicipal);
    }
}

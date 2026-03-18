/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesunitarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.api.model.input.nfse.NfseBaseCalculoInput;
import br.gov.serpro.rtc.api.model.output.nfse.NfseBaseCalculoOutput;
import br.gov.serpro.rtc.domain.service.nfse.NfseService;

class Teste_NfseService_CalcularBaseCalculo {

    private NfseService service;

    @BeforeEach
    void setup() {
        service = new NfseService();
    }

    @Test
    void teste_calculoCorretoEm2026_PisCofinsnaoIntegram() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        input.setVCalcReeRepRes(new BigDecimal("20.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("10.00"));
        input.setIss(new BigDecimal("80.00"));
        input.setPis(new BigDecimal("16.50"));
        input.setCofins(new BigDecimal("76.00"));
        input.setAnoFatoGerador(2026);

        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);

        assertNotNull(resultado);
        // integra = 1000
        // naoIntegra = 50 + 20 + 10 + 80 + 16.50 + 76 = 252.50
        // base = 1000 - 252.50 = 747.50
        assertEquals(0, new BigDecimal("747.50").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_calculoCorretoEm2027_SemPisCofinsSemIss() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        input.setVCalcReeRepRes(new BigDecimal("20.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("10.00"));
        input.setIss(new BigDecimal("80.00"));
        input.setAnoFatoGerador(2027);

        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);

        assertNotNull(resultado);
        // integra = 1000
        // naoIntegra = 50 + 20 + 10 + 80 = 160 (ISS deduzido em 2027, mas sem PIS e COFINS)
        // base = 1000 - 160 = 840.00
        assertEquals(0, new BigDecimal("840.00").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_dataAPartirDe2033SemIss() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        input.setVCalcReeRepRes(new BigDecimal("20.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("10.00"));
        input.setAnoFatoGerador(2033);

        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);

        assertNotNull(resultado);
        // integra = 1000
        // naoIntegra = 50 + 20 + 10 = 80 (SEM ISS, SEM PIS, SEM COFINS)
        // base = 1000 - 80 = 920.00
        assertEquals(0, new BigDecimal("920.00").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_baseNegativaLancaExcecao() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("100.00"));
        input.setDescontoIncondicional(new BigDecimal("200.00"));
        input.setAnoFatoGerador(2026);

        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );

        assertEquals("Base de cálculo não pode ser negativa. Valores que não integram a base são maiores que o valor do serviço.", 
                     exception.getMessage());
    }

    @Test
    void teste_todosCamposZeroRetornaZero() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(BigDecimal.ZERO);
        input.setAnoFatoGerador(2026);

        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);

        assertNotNull(resultado);
        assertEquals(0, BigDecimal.ZERO.compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_cadaCampoNaoIntegraCorretamente() {
        // Testa descontoIncondicional
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("100.00"));
        input.setAnoFatoGerador(2026);
        assertEquals(0, new BigDecimal("900.00").compareTo(service.calcularBaseCalculo(input).getBaseCalculo()));

        // Testa vCalcReeRepRes
        input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setVCalcReeRepRes(new BigDecimal("50.00"));
        input.setAnoFatoGerador(2026);
        assertEquals(0, new BigDecimal("950.00").compareTo(service.calcularBaseCalculo(input).getBaseCalculo()));

        // Testa vCalcDedRedIBSCBS
        input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("75.00"));
        input.setAnoFatoGerador(2026);
        assertEquals(0, new BigDecimal("925.00").compareTo(service.calcularBaseCalculo(input).getBaseCalculo()));

        // Testa iss
        input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("80.00"));
        input.setAnoFatoGerador(2026);
        assertEquals(0, new BigDecimal("920.00").compareTo(service.calcularBaseCalculo(input).getBaseCalculo()));
    }

    @Test
    void teste_transicao2026Para2027_PisCofinsLancaExcecaoEm2027() {
        BigDecimal valorServico = new BigDecimal("1000.00");
        BigDecimal pis = new BigDecimal("16.50");
        BigDecimal cofins = new BigDecimal("76.00");

        // Último dia de 2026 - PIS e COFINS não integram
        NfseBaseCalculoInput input2026 = new NfseBaseCalculoInput();
        input2026.setValorServico(valorServico);
        input2026.setPis(pis);
        input2026.setCofins(cofins);
        input2026.setAnoFatoGerador(2026);

        NfseBaseCalculoOutput resultado2026 = service.calcularBaseCalculo(input2026);
        // base = 1000 - 16.50 - 76 = 907.50
        assertEquals(0, new BigDecimal("907.50").compareTo(resultado2026.getBaseCalculo()));

        // Primeiro dia de 2027 - PIS e COFINS lançam exceção
        NfseBaseCalculoInput input2027 = new NfseBaseCalculoInput();
        input2027.setValorServico(valorServico);
        input2027.setPis(pis);
        input2027.setCofins(cofins);
        input2027.setAnoFatoGerador(2027);

        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input2027));
    }

    @Test
    void teste_ano2027_PisLancaExcecao() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("500.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setAnoFatoGerador(2027);

        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input));
    }
    
    @Test
    void teste_ano2027_CofinsLancaExcecao() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("500.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setAnoFatoGerador(2027);

        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input));
    }
    
    @Test
    void teste_ano2030_PisCofinsLancaExcecao() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("500.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setAnoFatoGerador(2030);

        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input));
    }
    
    @Test
    void teste_ano2033_IssLancaExcecao() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("80.00"));
        input.setAnoFatoGerador(2033);

        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input));
    }
    
    // ===== TESTES PARA NOVA VALIDAÇÃO: ANO MÍNIMO =====
    
    @Test
    void teste_anoAnterior2026_LancaExcecao() {
        // Nova validação: ano mínimo para CBS/IBS é 2026
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setAnoFatoGerador(2025);
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        assertEquals("O ano do fato gerador deve ser 2026 ou superior", exception.getMessage());
    }
    
    @Test
    void teste_ano2020_LancaExcecao() {
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setAnoFatoGerador(2020);
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        assertEquals("O ano do fato gerador deve ser 2026 ou superior", exception.getMessage());
    }
    
    @Test
    void teste_ano2026_PrimeiroAnoValido() {
        // 2026 é o primeiro ano válido, deve funcionar normalmente
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        input.setAnoFatoGerador(2026);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("950.00").compareTo(resultado.getBaseCalculo()));
    }
    
    // ===== TESTES COMPLEMENTARES: VALIDAÇÕES E CENÁRIOS =====
    
    @Test
    void teste_issComValorZeroNaoLancaExcecaoEm2033() {
        // ISS com valor zero não deve gerar exceção
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setIss(BigDecimal.ZERO);
        input.setAnoFatoGerador(2033);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_pisComValorZeroNaoLancaExcecaoEm2027() {
        // PIS com valor zero não deve gerar exceção
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setPis(BigDecimal.ZERO);
        input.setCofins(BigDecimal.ZERO);
        input.setAnoFatoGerador(2027);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2032_IssDeveSerDeduzido() {
        // ISS deve ser deduzido até 2032 (último ano)
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00"));
        input.setAnoFatoGerador(2032);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        // base = 1000 - 50 = 950.00
        assertEquals(0, new BigDecimal("950.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_mensagemErroMultiplosCampos() {
        // Valida mensagem quando múltiplos campos são informados
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setAnoFatoGerador(2027);
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        
        String mensagem = exception.getMessage();
        assertEquals(true, mensagem.contains("Os seguintes campos não podem ser informados a partir de 2027:"));
        assertEquals(true, mensagem.contains("PIS"));
        assertEquals(true, mensagem.contains("COFINS"));
    }
    
    @Test
    void teste_todosOsCamposQueNaoIntegram() {
        // Valida que todos os campos são deduzidos corretamente
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("2000.00"));
        input.setDescontoIncondicional(new BigDecimal("100.00"));
        input.setVCalcReeRepRes(new BigDecimal("50.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("30.00"));
        input.setIss(new BigDecimal("80.00"));
        input.setPis(new BigDecimal("16.50"));
        input.setCofins(new BigDecimal("76.00"));
        input.setAnoFatoGerador(2026);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        
        // naoIntegra = 100 + 50 + 30 + 80 + 16.50 + 76 = 352.50
        // base = 2000 - 352.50 = 1647.50
        assertEquals(0, new BigDecimal("1647.50").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_validacaoOcorreAntesDoCalculo() {
        // Validação de ano deve ocorrer antes do cálculo
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        input.setAnoFatoGerador(2025);
        
        // Deve lançar exceção de ano inválido, não de cálculo
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        assertEquals(true, exception.getMessage().contains("ano do fato gerador"));
    }
    
    @Test
    void teste_ano2034_IssNaoDeveSerDeduzido() {
        // ISS não deve ser deduzido em anos após 2032
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        input.setIss(new BigDecimal("80.00"));
        input.setAnoFatoGerador(2034);
        
        // Deve lançar exceção pois ISS foi informado após 2032
        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input));
    }
    
    // ===== TESTES DA REFATORAÇÃO: LÓGICA SIMPLIFICADA =====
    
    @Test
    void teste_camposSempreSomados_ValidacaoAntecipada() {
        // Nova lógica: campos sempre somados, validação antecipada
        // PIS/COFINS sempre somados em 2026
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setIss(new BigDecimal("50.00"));
        input.setAnoFatoGerador(2026);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        // Todos deduzidos: 10 + 20 + 50 = 80
        assertEquals(0, new BigDecimal("920.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_validacaoMetodoCentralizado() {
        // Valida que o método centralizado funciona corretamente
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setAnoFatoGerador(2027);
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        
        // Mensagem deve conter ambos os campos e ano de extinção
        assertEquals(true, exception.getMessage().contains("2027"));
        assertEquals(true, exception.getMessage().contains("PIS"));
        assertEquals(true, exception.getMessage().contains("COFINS"));
    }
    
    @Test
    void teste_mensagemOrdenadaAlfabeticamente() {
        // Valida ordenação alfabética (COFINS antes de PIS)
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setAnoFatoGerador(2027);
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        
        String mensagem = exception.getMessage();
        int posCofins = mensagem.indexOf("COFINS");
        int posPis = mensagem.indexOf("PIS");
        assertEquals(true, posCofins < posPis, "COFINS deve aparecer antes de PIS");
    }
    
    @Test
    void teste_duasValidacoesSeparadas() {
        // Valida que PIS/COFINS e ISS têm validações separadas
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setAnoFatoGerador(2033);
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularBaseCalculo(input)
        );
        
        // Deve mencionar apenas PIS e ano 2027 (não ISS e 2033)
        assertEquals(true, exception.getMessage().contains("PIS"));
        assertEquals(true, exception.getMessage().contains("2027"));
        assertEquals(false, exception.getMessage().contains("ISS"));
        assertEquals(false, exception.getMessage().contains("2033"));
    }
    
    @Test
    void teste_constantes_AnoExtincao() {
        // Valida os novos nomes das constantes (ANO_EXTINCAO ao invés de ANO_LIMITE)
        // PIS/COFINS: extinção em 2027
        NfseBaseCalculoInput input1 = new NfseBaseCalculoInput();
        input1.setValorServico(new BigDecimal("1000.00"));
        input1.setPis(new BigDecimal("10.00"));
        input1.setAnoFatoGerador(2027);
        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input1));
        
        // ISS: extinção em 2033
        NfseBaseCalculoInput input2 = new NfseBaseCalculoInput();
        input2.setValorServico(new BigDecimal("1000.00"));
        input2.setIss(new BigDecimal("50.00"));
        input2.setAnoFatoGerador(2033);
        assertThrows(CampoInvalidoException.class, () -> service.calcularBaseCalculo(input2));
    }
    
    @Test
    void teste_todosOsCamposSempreDeduzidos2026() {
        // Valida que em 2026 todos os campos são deduzidos
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("2000.00"));
        input.setDescontoIncondicional(new BigDecimal("100.00"));
        input.setVCalcReeRepRes(new BigDecimal("50.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("30.00"));
        input.setPis(new BigDecimal("20.00"));
        input.setCofins(new BigDecimal("80.00"));
        input.setIss(new BigDecimal("60.00"));
        input.setAnoFatoGerador(2026);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        
        // Todos deduzidos: 100 + 50 + 30 + 20 + 80 + 60 = 340
        // base = 2000 - 340 = 1660.00
        assertEquals(0, new BigDecimal("1660.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_todosOsCamposSempreDeduzidos2032() {
        // Valida que em 2032 ISS ainda é deduzido, mas PIS/COFINS não podem ser informados
        NfseBaseCalculoInput input = new NfseBaseCalculoInput();
        input.setValorServico(new BigDecimal("1500.00"));
        input.setDescontoIncondicional(new BigDecimal("100.00"));
        input.setVCalcReeRepRes(new BigDecimal("50.00"));
        input.setVCalcDedRedIBSCBS(new BigDecimal("30.00"));
        input.setIss(new BigDecimal("60.00"));
        // PIS/COFINS não informados (gerariam exceção)
        input.setAnoFatoGerador(2032);
        
        NfseBaseCalculoOutput resultado = service.calcularBaseCalculo(input);
        
        // Todos deduzidos: 100 + 50 + 30 + 60 = 240
        // base = 1500 - 240 = 1260.00
        assertEquals(0, new BigDecimal("1260.00").compareTo(resultado.getBaseCalculo()));
    }
    
}

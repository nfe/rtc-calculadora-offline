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

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoCibsInput;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoCibsModel;
import br.gov.serpro.rtc.domain.service.basecalculo.BaseCalculoService;

class Teste_BaseCalculoService_CalcularCibs {

    private BaseCalculoService service;

    @BeforeEach
    void setup() {
        service = new BaseCalculoService();
    }

    @Test
    void teste_logicaCalculoCorreta() {
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
        
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));

        BaseCalculoCibsModel resultado = service.calcularCibs(input);

        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1140.00").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_baseNegativaLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("100.00"));
        input.setIcms(new BigDecimal("200.00"));

        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularCibs(input)
        );

        assertEquals("Base de cálculo não pode ser negativa. A soma dos valores que não integram a base é maior que a soma dos valores que integram a base.", 
                     exception.getMessage());
    }

    @Test
    void teste_todosCamposZeroRetornaZero() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        // Todos os campos já são BigDecimal.ZERO por padrão

        BaseCalculoCibsModel resultado = service.calcularCibs(input);

        assertNotNull(resultado);
        assertEquals(0, BigDecimal.ZERO.compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_camposIndividuaisIntegram() {
        // Testa se cada campo que deve integrar realmente integra
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        
        // Testa valorBem
        input.setValorBem(new BigDecimal("100.00"));
        assertEquals(0, new BigDecimal("100.00").compareTo(service.calcularCibs(input).getBaseCalculo()));
        
        // Testa impostoSeletivo integra
        input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("100.00"));
        input.setImpostoSeletivo(new BigDecimal("50.00"));
        assertEquals(0, new BigDecimal("150.00").compareTo(service.calcularCibs(input).getBaseCalculo()));
    }

    @Test
    void teste_camposIndividuaisNaoIntegram() {
        // Testa se cada campo que não deve integrar realmente não integra (em 2027)
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        
        // Testa cosip
        input.setCosip(new BigDecimal("5.00"));
        assertEquals(0, new BigDecimal("995.00").compareTo(service.calcularCibs(input).getBaseCalculo()));
        
        // Testa ipi
        input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIpi(new BigDecimal("150.00"));
        assertEquals(0, new BigDecimal("850.00").compareTo(service.calcularCibs(input).getBaseCalculo()));
        
        // Testa descontoIncondicional
        input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        assertEquals(0, new BigDecimal("950.00").compareTo(service.calcularCibs(input).getBaseCalculo()));
    }
    
    @Test
    void teste_ano2026_PisCofinsDeduzidos() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2026);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("16.50"));
        input.setCofins(new BigDecimal("76.00"));
        input.setPisImportacao(new BigDecimal("5.00"));
        input.setCofinsImportacao(new BigDecimal("10.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // Em 2026, PIS/COFINS são deduzidos
        // naoIntegra = 16.50 + 76 + 5 + 10 = 107.50
        // base = 1000 - 107.50 = 892.50
        assertEquals(0, new BigDecimal("892.50").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_anoAnterior2026_LancaExcecao() {
        // Testa a nova validação de ano mínimo para CBS/IBS
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2025);
        input.setValorBem(new BigDecimal("1000.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularCibs(input)
        );
        assertEquals("Para CBS/IBS, o ano do fato gerador deve ser 2026 ou superior", 
                     exception.getMessage());
    }
    
    @Test
    void teste_ano2027_PisLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularCibs(input));
    }
    
    @Test
    void teste_ano2027_CofinsLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setCofins(new BigDecimal("20.00"));
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularCibs(input));
    }
    
    @Test
    void teste_ano2027_PisImportacaoLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPisImportacao(new BigDecimal("5.00"));
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularCibs(input));
    }
    
    @Test
    void teste_ano2027_CofinsImportacaoLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setCofinsImportacao(new BigDecimal("10.00"));
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularCibs(input));
    }
    
    @Test
    void teste_ano2027_PisCofinsMultiplosLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("20.00"));
        input.setPisImportacao(new BigDecimal("5.00"));
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularCibs(input));
    }
    
    @Test
    void teste_ano2032_IssDeduzido() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2032);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // ISS é deduzido até 2032
        // base = 1000 - 50 = 950.00
        assertEquals(0, new BigDecimal("950.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2033_IssLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        assertEquals("Os seguintes campos não podem ser informados a partir de 2033: ISS", exception.getMessage());
    }
    
    @Test
    void teste_ano2033_IssZeroNaoLancaExcecao() {
        // Testa que ISS com valor zero não gera exceção
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(BigDecimal.ZERO);
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2027_TodosCamposPisCofinsLancaExcecao() {
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("16.50"));
        input.setCofins(new BigDecimal("76.00"));
        input.setPisImportacao(new BigDecimal("5.00"));
        input.setCofinsImportacao(new BigDecimal("10.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        // Verifica que a mensagem contém todos os campos
        String mensagem = exception.getMessage();
        assertEquals(true, mensagem.contains("Os seguintes campos não podem ser informados a partir de 2027:"));
        assertEquals(true, mensagem.contains("PIS"));
        assertEquals(true, mensagem.contains("COFINS"));
    }
    
    @Test
    void teste_ano2027_CamposPisZeroNaoLancaExcecao() {
        // Testa que campos PIS/COFINS com valor zero não geram exceção
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(BigDecimal.ZERO);
        input.setCofins(BigDecimal.ZERO);
        input.setPisImportacao(BigDecimal.ZERO);
        input.setCofinsImportacao(BigDecimal.ZERO);
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    // ===== TESTES ESPECÍFICOS PARA ICMS =====
    
    @Test
    void teste_icmsNaoIntegraBase_2026() {
        // Valida que ICMS NÃO integra a base de cálculo (é deduzido) em 2026
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2026);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("150.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // ICMS não integra a base (é deduzido)
        // base = 1000 - 150 = 850.00
        assertEquals(0, new BigDecimal("850.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_icmsNaoIntegraBase_2027() {
        // Valida que ICMS NÃO integra a base de cálculo (é deduzido) em 2027
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("180.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // ICMS não integra a base (é deduzido)
        // base = 1000 - 180 = 820.00
        assertEquals(0, new BigDecimal("820.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_icmsNaoIntegraBase_2032() {
        // Valida que ICMS NÃO integra a base de cálculo (é deduzido) até 2032
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2032);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("120.00"));
        input.setIss(new BigDecimal("50.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // ICMS e ISS não integram a base até 2032
        // base = 1000 - 120 - 50 = 830.00
        assertEquals(0, new BigDecimal("830.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2033_IcmsLancaExcecao() {
        // Valida que ICMS não pode ser informado (> 0) a partir de 2033
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        assertEquals("Os seguintes campos não podem ser informados a partir de 2033: ICMS", exception.getMessage());
    }
    
    @Test
    void teste_ano2033_IcmsZeroNaoLancaExcecao() {
        // Valida que ICMS com valor zero não gera exceção em 2033
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(BigDecimal.ZERO);
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2033_IcmsIssAmbosLancamExcecao() {
        // Valida que ICMS e ISS juntos geram exceção com ambos na mensagem
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        
        String mensagem = exception.getMessage();
        assertEquals(true, mensagem.contains("Os seguintes campos não podem ser informados a partir de 2033:"));
        assertEquals(true, mensagem.contains("ICMS"));
        assertEquals(true, mensagem.contains("ISS"));
    }
    
    @Test
    void teste_icmsComOutrosCamposQueNaoIntegram() {
        // Valida ICMS junto com outros campos que não integram a base
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIpi(new BigDecimal("50.00"));
        input.setCosip(new BigDecimal("5.00"));
        input.setDescontoIncondicional(new BigDecimal("20.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // naoIntegra = 100 + 50 + 5 + 20 = 175
        // base = 1000 - 175 = 825.00
        assertEquals(0, new BigDecimal("825.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2034_IcmsLancaExcecao() {
        // Valida que ICMS continua gerando exceção em anos posteriores a 2033
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2034);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("50.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        assertEquals(true, exception.getMessage().contains("ICMS"));
    }
    
    @Test
    void teste_icmsComPisCofinsSomenteIcmsDeveSerDeduzido2027() {
        // Em 2027: PIS/COFINS NÃO são deduzidos, mas ICMS SIM
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        // Não informando PIS/COFINS para não gerar exceção
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // Apenas ICMS é deduzido
        // base = 1000 - 100 = 900.00
        assertEquals(0, new BigDecimal("900.00").compareTo(resultado.getBaseCalculo()));
    }
    
    // ===== TESTES COMPLEMENTARES - NOVA LÓGICA SIMPLIFICADA =====
    
    @Test
    void teste_mensagemErroOrdenadaAlfabeticamente_PisCofins() {
        // Valida que os campos PIS/COFINS são ordenados alfabeticamente
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPisImportacao(new BigDecimal("5.00")); // PIS Importação
        input.setPis(new BigDecimal("10.00")); // PIS
        input.setCofinsImportacao(new BigDecimal("8.00")); // COFINS Importação
        input.setCofins(new BigDecimal("15.00")); // COFINS
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        
        String mensagem = exception.getMessage();
        // Ordem alfabética: COFINS, COFINS Importação, PIS, PIS Importação
        int posCofins = mensagem.indexOf("COFINS,");
        int posCofinsImp = mensagem.indexOf("COFINS Importação");
        int posPis = mensagem.indexOf("PIS,");
        int posPisImp = mensagem.indexOf("PIS Importação");
        
        assertEquals(true, posCofins > 0 && posCofinsImp > posCofins);
        assertEquals(true, posPis > posCofinsImp && posPisImp > posPis);
    }
    
    @Test
    void teste_mensagemErroOrdenadaAlfabeticamente_IcmsIss() {
        // Valida que ICMS aparece antes de ISS na mensagem
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00")); // ISS informado primeiro
        input.setIcms(new BigDecimal("100.00")); // ICMS informado depois
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        
        String mensagem = exception.getMessage();
        int posicaoIcms = mensagem.indexOf("ICMS");
        int posicaoIss = mensagem.indexOf("ISS");
        assertEquals(true, posicaoIcms < posicaoIss, "ICMS deve aparecer antes de ISS");
    }
    
    @Test
    void teste_pisCofinsDeducaoSimultanea2026() {
        // Em 2026: PIS/COFINS são deduzidos simultaneamente
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2026);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("16.50"));
        input.setCofins(new BigDecimal("76.00"));
        input.setPisImportacao(new BigDecimal("5.00"));
        input.setCofinsImportacao(new BigDecimal("10.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // Todos deduzidos: 16.50 + 76 + 5 + 10 + 100 + 50 = 257.50
        // base = 1000 - 257.50 = 742.50
        assertEquals(0, new BigDecimal("742.50").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_todosOsCamposDeducaoSimultanea() {
        // Testa que todos os campos que não integram são deduzidos simultaneamente
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2026);
        input.setValorBem(new BigDecimal("3000.00"));
        input.setPis(new BigDecimal("20.00"));
        input.setPisImportacao(new BigDecimal("10.00"));
        input.setCofins(new BigDecimal("80.00"));
        input.setCofinsImportacao(new BigDecimal("15.00"));
        input.setIcms(new BigDecimal("150.00"));
        input.setIss(new BigDecimal("60.00"));
        input.setCosip(new BigDecimal("5.00"));
        input.setIpi(new BigDecimal("200.00"));
        input.setDescontoIncondicional(new BigDecimal("30.00"));
        
        BaseCalculoCibsModel resultado = service.calcularCibs(input);
        
        // Todos não integram: 20 + 10 + 80 + 15 + 150 + 60 + 5 + 200 + 30 = 570
        // base = 3000 - 570 = 2430.00
        assertEquals(0, new BigDecimal("2430.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_validacaoAntesDaSoma() {
        // Valida que a validação ocorre ANTES da soma
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularCibs(input));
    }
    
    @Test
    void teste_duasValidacoesSeparadas() {
        // Valida que há duas validações separadas: PIS/COFINS e ICMS/ISS
        // Se informar apenas PIS em 2033, deve dar erro de PIS (não de ICMS/ISS)
        BaseCalculoCibsInput input = new BaseCalculoCibsInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setPis(new BigDecimal("10.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularCibs(input)
        );
        
        // Deve mencionar PIS e ano 2027 (não 2033)
        assertEquals(true, exception.getMessage().contains("PIS"));
        assertEquals(true, exception.getMessage().contains("2027"));
    }
}

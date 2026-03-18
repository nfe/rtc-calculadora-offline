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

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoISMercadoriasInput;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoISMercadoriasModel;
import br.gov.serpro.rtc.domain.service.basecalculo.BaseCalculoService;

class Teste_BaseCalculoService_CalcularISMercadorias {

    private BaseCalculoService service;

    @BeforeEach
    void setup() {
        service = new BaseCalculoService();
    }

    @Test
    void teste_logicaCalculoCorreta() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setAjusteAcrescimos(new BigDecimal("50.00"));
        input.setJuros(new BigDecimal("20.00"));
        input.setMultas(new BigDecimal("10.00"));
        input.setEncargos(new BigDecimal("30.00"));
        input.setFreteCobrado(new BigDecimal("40.00"));
        input.setOutrosTributos(new BigDecimal("25.00"));
        input.setDemaisImportancias(new BigDecimal("15.00"));
        
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));
        input.setBonificacao(new BigDecimal("20.00"));

        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);

        assertNotNull(resultado);
        // integra = 1000 + 50 + 20 + 10 + 30 + 40 + 25 + 15 = 1190
        // naoIntegra = 100 + 50 + 20 = 170 (em 2027, PIS/COFINS não são deduzidos, ISS é deduzido)
        // base = 1190 - 170 = 1020.00
        assertEquals(0, new BigDecimal("1020.00").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_baseNegativaLancaExcecao() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("100.00"));
        input.setBonificacao(new BigDecimal("50.00"));
        input.setDevolucaoVendas(new BigDecimal("60.00"));

        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularISMercadorias(input)
        );

        assertEquals("Base de cálculo não pode ser negativa. A soma dos valores que não integram a base é maior que a soma dos valores que integram a base.", 
                     exception.getMessage());
    }

    @Test
    void teste_todosCamposZeroRetornaZero() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        // Todos os campos já são BigDecimal.ZERO por padrão

        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);

        assertNotNull(resultado);
        assertEquals(0, BigDecimal.ZERO.compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_camposIndividuaisIntegram() {
        // Testa se cada campo que deve integrar realmente integra
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        
        // Testa valorBem
        input.setValorBem(new BigDecimal("100.00"));
        assertEquals(0, new BigDecimal("100.00").compareTo(service.calcularISMercadorias(input).getBaseCalculo()));
        
        // Testa que freteCobrado integra
        input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("100.00"));
        input.setFreteCobrado(new BigDecimal("25.00"));
        assertEquals(0, new BigDecimal("125.00").compareTo(service.calcularISMercadorias(input).getBaseCalculo()));
    }

    @Test
    void teste_bonificacaoReduzBase_Art417() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setBonificacao(new BigDecimal("150.00"));

        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);

        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("850.00").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_devolucaoVendasReduzBase_Art418() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setDevolucaoVendas(new BigDecimal("200.00"));

        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);

        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("800.00").compareTo(resultado.getBaseCalculo()));
    }

    @Test
    void teste_camposIndividuaisNaoIntegram() {
        // Testa campos que não devem integrar (em 2027)
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        
        // Testa cosip
        input.setCosip(new BigDecimal("5.00"));
        assertEquals(0, new BigDecimal("995.00").compareTo(service.calcularISMercadorias(input).getBaseCalculo()));
        
        // Testa ipi
        input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIpi(new BigDecimal("150.00"));
        assertEquals(0, new BigDecimal("850.00").compareTo(service.calcularISMercadorias(input).getBaseCalculo()));
        
        // Testa descontoIncondicional
        input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setDescontoIncondicional(new BigDecimal("50.00"));
        assertEquals(0, new BigDecimal("950.00").compareTo(service.calcularISMercadorias(input).getBaseCalculo()));
        
        // Testa bonificacao e devolucaoVendas juntos
        input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setBonificacao(new BigDecimal("100.00"));
        input.setDevolucaoVendas(new BigDecimal("50.00"));
        assertEquals(0, new BigDecimal("850.00").compareTo(service.calcularISMercadorias(input).getBaseCalculo()));
    }
    
    @Test
    void teste_ano2026_PisCofinsDeduzidos() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);  // Mudado de 2026 para 2027 devido à nova validação ANO_INICIO_IS
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("50.00"));
        input.setIss(new BigDecimal("30.00"));
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        
        // Em 2027, PIS/COFINS não são mais deduzidos, apenas ICMS e ISS
        // base = 1000 - 50 - 30 = 920.00
        assertEquals(0, new BigDecimal("920.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_anoAnterior2027_LancaExcecao() {
        // Testa a nova validação de ano mínimo para Imposto Seletivo
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2026);
        input.setValorBem(new BigDecimal("1000.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class,
            () -> service.calcularISMercadorias(input)
        );
        assertEquals("Para IS, o ano do fato gerador deve ser 2027 ou superior", 
                     exception.getMessage());
    }
    
    @Test
    void teste_ano2032_IssDeduzido() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2032);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00"));
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        
        // ISS é deduzido até 2032
        // base = 1000 - 50 = 950.00
        assertEquals(0, new BigDecimal("950.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2033_IssLancaExcecao() {
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularISMercadorias(input)
        );
        assertEquals("Os seguintes campos não podem ser informados a partir de 2033: ISS", exception.getMessage());
    }
    
    @Test
    void teste_ano2033_IssZeroNaoLancaExcecao() {
        // Testa que ISS com valor zero não gera exceção
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(BigDecimal.ZERO);
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    // ===== TESTES ESPECÍFICOS PARA ICMS =====
    
    @Test
    void teste_icmsNaoIntegraBase_2027() {
        // Valida que ICMS NÃO integra a base de cálculo (é deduzido) em 2027
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("180.00"));
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        
        // ICMS não integra a base (é deduzido)
        // base = 1000 - 180 = 820.00
        assertEquals(0, new BigDecimal("820.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_icmsNaoIntegraBase_2032() {
        // Valida que ICMS NÃO integra a base de cálculo (é deduzido) até 2032
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2032);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("120.00"));
        input.setIss(new BigDecimal("50.00"));
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        
        // ICMS e ISS não integram a base até 2032
        // base = 1000 - 120 - 50 = 830.00
        assertEquals(0, new BigDecimal("830.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2033_IcmsLancaExcecao() {
        // Valida que ICMS não pode ser informado (> 0) a partir de 2033
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularISMercadorias(input)
        );
        assertEquals("Os seguintes campos não podem ser informados a partir de 2033: ICMS", exception.getMessage());
    }
    
    @Test
    void teste_ano2033_IcmsZeroNaoLancaExcecao() {
        // Valida que ICMS com valor zero não gera exceção em 2033
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(BigDecimal.ZERO);
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        assertNotNull(resultado);
        assertEquals(0, new BigDecimal("1000.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2033_IcmsIssAmbosLancamExcecao() {
        // Valida que ICMS e ISS juntos geram exceção com ambos na mensagem
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularISMercadorias(input)
        );
        
        String mensagem = exception.getMessage();
        assertEquals(true, mensagem.contains("Os seguintes campos não podem ser informados a partir de 2033:"));
        assertEquals(true, mensagem.contains("ICMS"));
        assertEquals(true, mensagem.contains("ISS"));
    }
    
    @Test
    void teste_icmsComOutrosCamposQueNaoIntegram() {
        // Valida ICMS junto com outros campos que não integram a base
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIpi(new BigDecimal("50.00"));
        input.setCosip(new BigDecimal("5.00"));
        input.setDescontoIncondicional(new BigDecimal("20.00"));
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        
        // naoIntegra = 100 + 50 + 5 + 20 = 175
        // base = 1000 - 175 = 825.00
        assertEquals(0, new BigDecimal("825.00").compareTo(resultado.getBaseCalculo()));
    }
    
    @Test
    void teste_ano2034_IcmsLancaExcecao() {
        // Valida que ICMS continua gerando exceção em anos posteriores a 2033
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2034);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("50.00"));
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularISMercadorias(input)
        );
        assertEquals(true, exception.getMessage().contains("ICMS"));
    }
    
    // ===== TESTES COMPLEMENTARES - NOVA LÓGICA SIMPLIFICADA =====
    
    @Test
    void teste_mensagemErroOrdenadaAlfabeticamente() {
        // Valida que os campos na mensagem de erro são ordenados alfabeticamente
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIss(new BigDecimal("50.00")); // ISS vem depois de ICMS alfabeticamente
        input.setIcms(new BigDecimal("100.00")); // ICMS vem primeiro
        
        CampoInvalidoException exception = assertThrows(
            CampoInvalidoException.class, 
            () -> service.calcularISMercadorias(input)
        );
        
        String mensagem = exception.getMessage();
        // Deve aparecer ICMS antes de ISS na mensagem
        int posicaoIcms = mensagem.indexOf("ICMS");
        int posicaoIss = mensagem.indexOf("ISS");
        assertEquals(true, posicaoIcms < posicaoIss, "ICMS deve aparecer antes de ISS na mensagem");
    }
    
    @Test
    void teste_validacaoAntesDaSoma() {
        // Valida que a validação ocorre ANTES da soma (exceção antes de calcular)
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2033);
        input.setValorBem(new BigDecimal("1000.00"));
        input.setIcms(new BigDecimal("100.00"));
        // Mesmo que o cálculo resultasse em valor válido, deve lançar exceção
        
        assertThrows(CampoInvalidoException.class, () -> service.calcularISMercadorias(input));
    }
    
    @Test
    void teste_todosOsCamposDeducaoSimultanea() {
        // Testa que todos os campos que não integram são deduzidos simultaneamente
        BaseCalculoISMercadoriasInput input = new BaseCalculoISMercadoriasInput();
        input.setAnoFatoGerador(2027);
        input.setValorBem(new BigDecimal("2000.00"));
        input.setIcms(new BigDecimal("100.00"));
        input.setIss(new BigDecimal("50.00"));
        input.setCosip(new BigDecimal("10.00"));
        input.setIpi(new BigDecimal("150.00"));
        input.setDescontoIncondicional(new BigDecimal("30.00"));
        input.setBonificacao(new BigDecimal("20.00"));
        input.setDevolucaoVendas(new BigDecimal("40.00"));
        
        BaseCalculoISMercadoriasModel resultado = service.calcularISMercadorias(input);
        
        // Todos os campos não integram: 100 + 50 + 10 + 150 + 30 + 20 + 40 = 400
        // base = 2000 - 400 = 1600.00
        assertEquals(0, new BigDecimal("1600.00").compareTo(resultado.getBaseCalculo()));
    }

}

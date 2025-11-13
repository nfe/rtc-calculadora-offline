/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.testesunitarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.domain.service.calculotributo.AvaliadorExpressaoAritmetica;

/**
 * Testes unitários para o AvaliadorExpressaoAritmetica
 */
public class Teste_AvaliadorExpressaoAritmetica {

    private AvaliadorExpressaoAritmetica avaliador;

    @BeforeEach
    public void setup() {
        avaliador = new AvaliadorExpressaoAritmetica();
        // Chamar o método @PostConstruct manualmente para testes
        try {
            var method = AvaliadorExpressaoAritmetica.class.getDeclaredMethod("postConstruct");
            method.setAccessible(true);
            method.invoke(avaliador);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar avaliador", e);
        }
    }

    @Test
    public void teste_DivisaoInteiros_DevolveFracao() {
        // Testa que 5/100 resulta em 0.05 e não em 0
        Map<String, BigDecimal> variables = new HashMap<>();
        
        String expressao = "5/100";
        BigDecimal resultado = avaliador.evaluate(expressao, variables, 4);
        
        assertEquals(new BigDecimal("0.0500"), resultado, 
            "5/100 deve resultar em 0.05, não em 0 (divisão inteira)");
    }

    @Test
    public void teste_ExpressaoComVariaveis() {
        // Testa a expressão completa do problema relatado
        Map<String, BigDecimal> variables = new HashMap<>();
        variables.put("quantidade", new BigDecimal("7720"));
        variables.put("aliquotaAdRemSecundaria", new BigDecimal("0.1922"));
        
        String expressao = "(5/100)*quantidade*0.4285714*aliquotaAdRemSecundaria";
        BigDecimal resultado = avaliador.evaluate(expressao, variables, 2);
        
        // Cálculo esperado: 0.05 * 7720 * 0.4285714 * 0.1922 = 31.81
        BigDecimal esperado = new BigDecimal("0.05")
            .multiply(new BigDecimal("7720"))
            .multiply(new BigDecimal("0.4285714"))
            .multiply(new BigDecimal("0.1922"))
            .setScale(2, java.math.RoundingMode.HALF_UP);
        
        assertEquals(esperado, resultado, 
            "A expressão com 5/100 deve calcular corretamente");
    }

    @Test
    public void teste_DivisaoDecimaisExplicitos() {
        // Testa que decimais explícitos sempre funcionaram
        Map<String, BigDecimal> variables = new HashMap<>();
        variables.put("quantidade", new BigDecimal("7720"));
        variables.put("aliquotaAdRemSecundaria", new BigDecimal("0.1922"));
        
        String expressao = "0.05*quantidade*0.4285714*aliquotaAdRemSecundaria";
        BigDecimal resultado = avaliador.evaluate(expressao, variables, 2);
        
        BigDecimal esperado = new BigDecimal("0.05")
            .multiply(new BigDecimal("7720"))
            .multiply(new BigDecimal("0.4285714"))
            .multiply(new BigDecimal("0.1922"))
            .setScale(2, java.math.RoundingMode.HALF_UP);
        
        assertEquals(esperado, resultado, 
            "A expressão com 0.05 deve calcular corretamente");
    }

    @Test
    public void teste_DivisaoSimples() {
        Map<String, BigDecimal> variables = new HashMap<>();
        
        String expressao = "1/2";
        BigDecimal resultado = avaliador.evaluate(expressao, variables, 2);
        
        assertEquals(new BigDecimal("0.50"), resultado, "1/2 deve resultar em 0.50");
    }

    @Test
    public void teste_DivisaoComplexaComVariaveis() {
        Map<String, BigDecimal> variables = new HashMap<>();
        variables.put("valor", new BigDecimal("100"));
        
        String expressao = "valor * (3/4)";
        BigDecimal resultado = avaliador.evaluate(expressao, variables, 2);
        
        assertEquals(new BigDecimal("75.00"), resultado, "100 * 3/4 deve resultar em 75.00");
    }
}

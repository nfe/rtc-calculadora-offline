/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl3.JexlArithmetic;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.introspection.JexlSandbox;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import jakarta.annotation.PostConstruct;

@Service
public class AvaliadorExpressaoAritmetica {
    
    private JexlEngine expressionEngine;
    
    @PostConstruct
    private void postConstruct() {
        // Create a JEXL engine with custom arithmetic that uses BigDecimal
        expressionEngine = new JexlBuilder()
                .sandbox(new JexlSandbox()) /* Empty sandbox (no external access) */
                .strict(true)
                .cache(512)
                .cacheThreshold(1024)
                .arithmetic(new BigDecimalArithmetic(true)) /* Use BigDecimal arithmetic */
                .create();
    }
    
    /**
     * Custom JEXL Arithmetic that converts all numbers to BigDecimal
     * to avoid integer division issues (e.g., 5/100 = 0 in integer division)
     */
    private static class BigDecimalArithmetic extends JexlArithmetic {
        
        public BigDecimalArithmetic(boolean strict) {
            super(strict);
        }
        
        @Override
        public Object divide(Object left, Object right) {
            // Convert both operands to BigDecimal to ensure decimal division
            BigDecimal leftBD = toBigDecimal(left);
            BigDecimal rightBD = toBigDecimal(right);
            
            if (rightBD.compareTo(ZERO) == 0) {
                throw new ArithmeticException("Division by zero");
            }
            
            // Use a high precision for intermediate calculations
            return leftBD.divide(rightBD, MathContext.DECIMAL128);
        }
        
        @Override
        public BigDecimal toBigDecimal(Object value) {
            if (value instanceof BigDecimal bd) {
                return bd;
            } else if (value instanceof Number n) {
                return new BigDecimal(n.toString());
            } else {
                throw new ArithmeticException("Cannot convert to BigDecimal: " + value);
            }
        }
    }
    
    public BigDecimal evaluate(String expression, Map<String, BigDecimal> variables, int scale) {
        final var exprTrim = expression.trim();
        // Caso 1: expressão é só o nome da variável
        if (variables.containsKey(exprTrim)) {
            // deve estar mapeado
            final var valor = variables.get(exprTrim);
            if (valor != null) {
                return valor;
            }
            throw new RuntimeException("Valor não mapeado corretamente: " + exprTrim);
        }

        /*
        // Caso 2: expressão é só um número
        try {
            if (exprTrim.matches("^\\d+(\\.\\d+)?$")) {
                // É possivelmente um número positivo, pode criar BigDecimal
                final var valor = new BigDecimal(exprTrim);
                return valor.setScale(scale, HALF_UP);
            }
        } catch (NumberFormatException ignore) {
            // Não é número, segue para JEXL
        }
        */
    
        // Caso geral: usa JEXL
        final JexlExpression jexp = expressionEngine.createExpression(expression);
        final Object evaluate = jexp.evaluate(new MapContextBigDecimal(variables));
        if (evaluate instanceof Number n) {
            BigDecimal result = NumberUtils.convertNumberToTargetClass(n, BigDecimal.class);
            return result.setScale(scale, HALF_UP);
        } else {
            throw new NumberFormatException("Erro ao avaliar expressão: " + evaluate);
        }
    }

    /**
     * Wraps a map in a context.
     * <p>
     * Each entry in the map is considered a variable name, value pair.
     * </p>
     */
    private class MapContextBigDecimal implements JexlContext {

        /**
         * The wrapped variable map.
         */
        private final Map<String, BigDecimal> map;

        /**
         * Creates a MapContext wrapping an existing user provided map.
         *
         * @param vars the variable map
         */
        public MapContextBigDecimal(final Map<String, BigDecimal> vars) {
            map = vars == null ? new HashMap<>() : vars;
        }

        @Override
        public Object get(final String name) {
            return map.get(name);
        }

        @Override
        public boolean has(final String name) {
            return map.containsKey(name);
        }

        @Override
        public void set(final String name, final Object value) {
            if (value instanceof BigDecimal bd) {
                map.put(name, bd);
            }
        }
    }
    
}
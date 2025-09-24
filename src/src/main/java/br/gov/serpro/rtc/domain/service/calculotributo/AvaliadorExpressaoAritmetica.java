/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

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
        // Create a JEXL engine
        expressionEngine = new JexlBuilder()
                .sandbox(new JexlSandbox()) /* Empty sandbox (no external access) */
                .strict(true)
                .cache(512)
                .cacheThreshold(1024)
                .create();
    }
    
    public BigDecimal evaluate(String expression, Map<String, BigDecimal> variables, int scale) {
        final JexlExpression jexp = expressionEngine.createExpression(expression);
        final Object evaluate = jexp.evaluate(new MapContextBigDecimal(variables));
        if (evaluate instanceof Number n) {
            BigDecimal result = NumberUtils.convertNumberToTargetClass(n, BigDecimal.class);
            return result.setScale(scale, RoundingMode.HALF_UP);
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
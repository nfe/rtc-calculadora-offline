package br.gov.serpro.rtc.config.serializer;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Testes para o serializador de quantidade TDec_1104OpRTC.
 * 
 * Validações do padrão:
 * - Inteiros: 1 a 11 dígitos sem zeros à esquerda
 * - Decimais: exatamente 4 casas decimais, pelo menos 1 != 0
 */
class BigDecimalTDec1104OpRTCSerializerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    
    static class Wrapper {
        @JsonSerialize(using = BigDecimalTDec1104OpRTCSerializer.class)
        public BigDecimal value;
        
        public Wrapper(BigDecimal value) {
            this.value = value;
        }
    }
    
    @Test
    void testQuantidadeInteira_1000() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("1000"));
        String json = mapper.writeValueAsString(wrapper);
        
        assertThat(json).isEqualTo("{\"value\":\"1000\"}");
    }
    
    @Test
    void testQuantidadeInteira_1() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("1"));
        String json = mapper.writeValueAsString(wrapper);
        
        assertThat(json).isEqualTo("{\"value\":\"1\"}");
    }
    
    @Test
    void testQuantidadeDecimal_55_55_DeveSerializar4Casas() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("55.55"));
        String json = mapper.writeValueAsString(wrapper);
        
        // 55.55 deve virar 55.5500 (4 casas decimais)
        assertThat(json).isEqualTo("{\"value\":\"55.5500\"}");
    }
    
    @Test
    void testQuantidadeDecimal_55_5555() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("55.5555"));
        String json = mapper.writeValueAsString(wrapper);
        
        assertThat(json).isEqualTo("{\"value\":\"55.5555\"}");
    }
    
    @Test
    void testQuantidadeDecimal_55_550_DeveSerializar4Casas() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("55.550"));
        String json = mapper.writeValueAsString(wrapper);
        
        // 55.550 deve virar 55.5500 (4 casas decimais)
        assertThat(json).isEqualTo("{\"value\":\"55.5500\"}");
    }
    
    @Test
    void testQuantidadeDecimal_55_5500() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("55.5500"));
        String json = mapper.writeValueAsString(wrapper);
        
        assertThat(json).isEqualTo("{\"value\":\"55.5500\"}");
    }
    
    @Test
    void testQuantidadeDecimal_0_0001() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("0.0001"));
        String json = mapper.writeValueAsString(wrapper);
        
        assertThat(json).isEqualTo("{\"value\":\"0.0001\"}");
    }
    
    @Test
    void testQuantidadeDecimal_0_1() throws JsonProcessingException {
        var wrapper = new Wrapper(new BigDecimal("0.1"));
        String json = mapper.writeValueAsString(wrapper);
        
        // 0.1 deve virar 0.1000 (4 casas decimais)
        assertThat(json).isEqualTo("{\"value\":\"0.1000\"}");
    }
    
    @Test
    void testQuantidadeZero_DeveFicarInteiro() throws JsonProcessingException {
        var wrapper = new Wrapper(BigDecimal.ZERO);
        String json = mapper.writeValueAsString(wrapper);
        
        // 0 deve ficar como inteiro, não 0.0000
        assertThat(json).isEqualTo("{\"value\":\"0\"}");
    }
}

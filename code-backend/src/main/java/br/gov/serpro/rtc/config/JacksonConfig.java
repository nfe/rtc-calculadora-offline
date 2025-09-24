/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serpro.rtc.config.serializer.CustomOffsetDateTimeSerializer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class JacksonConfig {

    private final CustomOffsetDateTimeSerializer offsetDateTimeSerializer;
    
    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {        
        return new Jackson2ObjectMapperBuilder().serializers(offsetDateTimeSerializer);
    }
    
    @Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build();
    }

}
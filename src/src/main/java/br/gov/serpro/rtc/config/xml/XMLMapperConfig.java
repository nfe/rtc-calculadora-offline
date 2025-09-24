package br.gov.serpro.rtc.config.xml;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class XMLMapperConfig {

    @Bean
    XmlMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(NON_NULL);
        xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.addMixIn(IMemoriaCalculo.class, MemoriaCalculoXmlMixin.class);
        xmlMapper.addMixIn(IValoresTotais.class, ValoresTotaisXmlMixin.class);
        return xmlMapper;
    }
    
    @Bean
    MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter(XmlMapper xmlMapper) {
        return new MappingJackson2XmlHttpMessageConverter(xmlMapper);
    }
     
}

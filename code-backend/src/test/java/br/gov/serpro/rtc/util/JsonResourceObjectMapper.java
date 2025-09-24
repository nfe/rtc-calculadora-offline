/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.util;

import java.io.IOException;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonResourceObjectMapper<T> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.registerModule(new JavaTimeModule());
    }

    private Class<T> model;

    public JsonResourceObjectMapper(Class<T> model) {
        this.model = model;
    }

    public T loadTestJson(Resource resourceFile) throws IOException {
        return MAPPER.readValue(resourceFile.getInputStream(), this.model);
    }
}

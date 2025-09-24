/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.config.serializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.serpro.rtc.config.ApplicationConfig;

@Component
public final class CustomOffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
    private final DateTimeFormatter fmt;

    public CustomOffsetDateTimeSerializer(ApplicationConfig config) {
        this.fmt = DateTimeFormatter.ofPattern(config.getDateTimeFormat())
                .withZone(TimeZone.getTimeZone(config.getTimezone()).toZoneId());
    }

    @Override
    public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(fmt.format(offsetDateTime));
    }

    @Override
    public Class<OffsetDateTime> handledType() {
        return OffsetDateTime.class;
    }
}
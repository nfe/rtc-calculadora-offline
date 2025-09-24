/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.config;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TimezoneConfig {

    public TimezoneConfig(ApplicationConfig properties) {
        TimeZone.setDefault(TimeZone.getTimeZone(properties.getTimezone()));
        log.info(String.format("Registering default timezone as '%s'", TimeZone.getDefault().getID()));
        
        Locale.setDefault(Locale.forLanguageTag(properties.getLocale()));
        log.info(String.format("Registering default locale as '%s'", Locale.getDefault()));
    }
}
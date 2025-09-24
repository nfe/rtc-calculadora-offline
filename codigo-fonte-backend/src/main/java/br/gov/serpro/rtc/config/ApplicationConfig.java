/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "application")
public class ApplicationConfig {
    private String timezone;
    private String locale;
    private String dateTimeFormat;
}

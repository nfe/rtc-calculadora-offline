/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.config.cache;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cache")
public class CacheSpecs {

	private List<CacheSpec> specs;

	@Getter
	@Setter
	public static class CacheSpec {
	    private String name;
	    private Duration expireAfterWrite;
	    private int initialCapacity;
	}

}
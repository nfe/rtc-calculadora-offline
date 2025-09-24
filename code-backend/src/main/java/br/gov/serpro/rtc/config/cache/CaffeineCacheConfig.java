/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class CaffeineCacheConfig {
    
	@Bean
	CacheManager cacheManager(CacheSpecs cacheSpec) {
	    final CaffeineCacheManager manager = new CaffeineCacheManager();
        cacheSpec
                .getSpecs()
                .parallelStream()
                .forEach(t -> buildCache(manager, t));
        return manager;
    }

	private static void buildCache(CaffeineCacheManager manager, CacheSpecs.CacheSpec cacheConfig) {
		final Cache<Object, Object> cache = Caffeine.newBuilder()
				.expireAfterWrite(cacheConfig.getExpireAfterWrite())
				.initialCapacity(cacheConfig.getInitialCapacity())
				.recordStats()
				.build();
		manager.registerCustomCache(cacheConfig.getName(), cache);
	}
}
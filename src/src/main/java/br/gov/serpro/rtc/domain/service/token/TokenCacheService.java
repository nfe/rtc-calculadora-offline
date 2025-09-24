/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.token;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenCacheService {
    
    @Cacheable("TokenCacheService.get")
    public TextToken get(String text, String key) {
        return new TextToken(text, key);
    }
}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.token;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TemplateTokenizerService {
    private final TokenCacheService tokenCacheService;

    @Cacheable(cacheNames = "TemplateTokenizerService.tokenize")
    public List<TextToken> tokenize(String template) {
        List<TextToken> tokens = new ArrayList<>();
        int last = 0;
        int len = template.length();
        while (last < len) {
            int open = template.indexOf('[', last);
            if (open < 0) {
                tokens.add(tokenCacheService.get(template.substring(last), null));
                break;
            }
            int close = template.indexOf(']', open + 1);
            if (close < 0) {
                tokens.add(tokenCacheService.get(template.substring(last), null));
                break;
            }
            if (open > last) {
                tokens.add(tokenCacheService.get(template.substring(last, open), null));
            }
            String key = template.substring(open + 1, close);
            tokens.add(tokenCacheService.get(null, key));
            last = close + 1;
        }
        return tokens;
    }

}
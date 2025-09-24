/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.token;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenizerService {

    private final TemplateTokenizerService templateTokenizerService;

    public String substituirPlaceholders(String template, Map<String, String> values) {
        if (!StringUtils.contains(template, '[')) {
            return template;
        }
        StringBuilder result = new StringBuilder(template.length());
        for (TextToken token : templateTokenizerService.tokenize(template)) {
            if (token.key() == null) {
                result.append(token.text());
            } else {
                result.append(values.getOrDefault(token.key(), ""));
            }
        }
        return result.toString();
    }
}

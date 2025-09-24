/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

public final class HttpUtils {

    private HttpUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String getBaseURL() {
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequestUri().build();
        String scheme = uriComponents.getScheme();
        String host = uriComponents.getHost();
        int port = uriComponents.getPort();

        return String.format("%s://%s:%d", scheme, host, port);
    }

}

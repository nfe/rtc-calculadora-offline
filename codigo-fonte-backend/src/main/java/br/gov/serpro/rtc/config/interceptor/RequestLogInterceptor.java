package br.gov.serpro.rtc.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor responsável por capturar e logar informações das requisições
 * aos endpoints da calculadora, incluindo IP de origem.
 * Ativo apenas quando application.module=api-regime-geral-publico.
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "application.module", havingValue = "api-regime-geral-publico")
public class RequestLogInterceptor implements HandlerInterceptor {

    private static final String MDC_CLIENT_IP = "clientIp";
    private static final String MDC_REQUEST_URI = "requestUri";
    private static final String MDC_HTTP_METHOD = "httpMethod";
    private static final String MDC_USER_AGENT = "userAgent";

    private static final String HEADER_X_FORWARDED_FOR = "X-ServAuth-Forwarded-For";
    private static final String HEADER_USER_AGENT = "User-Agent";

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                           @NonNull HttpServletResponse response,
                           @NonNull Object handler) {

        // Capturar IP de origem do header X-ServAuth-Forwarded-For
        String clientIp = extractClientIp(request);
        String userAgent = sanitizeUserAgent(request.getHeader(HEADER_USER_AGENT));

        // Configurar MDC para logs estruturados
        MDC.put(MDC_CLIENT_IP, clientIp);
        MDC.put(MDC_REQUEST_URI, request.getRequestURI());
        MDC.put(MDC_HTTP_METHOD, request.getMethod());
        MDC.put(MDC_USER_AGENT, userAgent);

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                              @NonNull HttpServletResponse response,
                              @NonNull Object handler,
                              Exception ex) {

        try {
            // Log de finalização da requisição
            log.debug("Requisição finalizada - Status: {}", response.getStatus());
        } finally {
            // Limpar MDC para evitar vazamentos de memória
            MDC.clear();
        }
    }

    /**
     * Extrai o IP do cliente do header X-ServAuth-Forwarded-For.
     */
    private String extractClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader(HEADER_X_FORWARDED_FOR);

        if (!StringUtils.hasText(forwardedFor)) {
            return "unknown";
        }

        return forwardedFor.trim();
    }

    /**
     * Sanitiza o User-Agent para evitar problemas nos logs
     */
    private String sanitizeUserAgent(String userAgent) {
        if (!StringUtils.hasText(userAgent)) {
            return "unknown";
        }

        // Remove caracteres que podem causar problemas nos logs
        return userAgent.replaceAll("[\r\n\t]", "_")
                        .substring(0, Math.min(userAgent.length(), 200)); // Limita tamanho
    }
}

package br.gov.serpro.rtc.config.interceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

/**
 * Configuração para interceptors de logging.
 * Esta configuração é aplicada em todos os endpoints abaixo de /calculadora.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(name = "application.module", havingValue = "api-regime-geral-publico")
public class LoggingInterceptorConfig implements WebMvcConfigurer {

    private final RequestLogInterceptor requestLogInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(requestLogInterceptor)
                .addPathPatterns("/calculadora/**")
                .order(0); // Prioridade alta para ser executado primeiro
    }
}

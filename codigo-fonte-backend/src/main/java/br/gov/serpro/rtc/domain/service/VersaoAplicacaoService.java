/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VersaoAplicacaoService {

    @Value("${info.app.version:unknown}")
    private String versaoAplicacao;
    private final VersaoBaseDadosService versaoBaseDadosService;
    
    public HttpHeaders getHeaders() {
        final var headers = new HttpHeaders();
        headers.add("X-CALC-APP-VERSION", versaoAplicacao);
        headers.add("X-CALC-DB-VERSION", versaoBaseDadosService.getUltimaVersao().getNumeroVersao());
        return headers;
    }

}
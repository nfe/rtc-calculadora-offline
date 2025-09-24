/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.roc.ROC;
import br.gov.serpro.rtc.api.openapi.controller.CalculadoraTributoControllerOpenApi;
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.domain.service.VersaoBaseDadosService;
import br.gov.serpro.rtc.domain.service.xml.XmlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora")
public class CalculadoraTributoController implements CalculadoraTributoControllerOpenApi {

    @Value("${info.app.version:unknown}")
    private String versaoAplicacao;
    private final VersaoBaseDadosService versaoBaseDadosService;
    private final CalculadoraService calculadoraService;
    private final XmlService xmlService;

    @Override
    @PostMapping("regime-geral")
    public ResponseEntity<ROC> calcularTributos(@RequestBody @Valid OperacaoInput operacao) {
        log.debug("ROC ID {}", operacao.getId());
        return ResponseEntity.ok().headers(getHeaders()).body(calculadoraService.calcularTributos(operacao));
    }

    @Override
    @PostMapping(value = "gerar-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ROC> gerarXml(@RequestBody ROC roc) {
        log.debug("Gerando xml...");
        return ResponseEntity.ok().headers(getHeaders()).body(roc);
    }

    @Override
    @PostMapping(value = "validar-xml", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Boolean> validarXml(@RequestBody String xml,
            @RequestParam(required = true) String tipo,
            @RequestParam(required = true) String subtipo) {
        boolean valido = xmlService.validarXml(xml, tipo, subtipo);
        return ResponseEntity.ok(valido);
    }

    private HttpHeaders getHeaders() {
        final var headers = new HttpHeaders();
        headers.add("X-CALC-APP-VERSION", versaoAplicacao);
        headers.add("X-CALC-DB-VERSION", versaoBaseDadosService.getUltimaVersao().getNumeroVersao());
        return headers;
    }

}

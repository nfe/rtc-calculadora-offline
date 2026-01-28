/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.openapi.controller.CalculadoraTributoControllerOpenApi;
import br.gov.serpro.rtc.domain.model.enumeration.TipoWarningDadosSimulados;
import br.gov.serpro.rtc.domain.service.CalculadoraService;
import br.gov.serpro.rtc.domain.service.VersaoAplicacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora")
public class CalculadoraTributoController implements CalculadoraTributoControllerOpenApi {

    private final CalculadoraService calculadoraService;
    private final VersaoAplicacaoService versaoAplicacaoService;

    @Override    
    @PostMapping(
        value = "regime-geral",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ROCDomain> calcularTributos(@RequestBody @Valid OperacaoInput operacao) {
        
        log.debug("ROC ID {}", operacao.getId());
        ROCDomain resultado = calculadoraService.calcularTributos(operacao);

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok()
                .headers(versaoAplicacaoService.getHeaders());

        TipoWarningDadosSimulados warningDadosSimulados = calculadoraService.getWarningDadosSimulados(operacao);
        if (warningDadosSimulados != null) {
            responseBuilder.header("x-warning-dados-simulados", String.valueOf(warningDadosSimulados.getValor()));
        }
        
        return responseBuilder.body(resultado);
    }

}

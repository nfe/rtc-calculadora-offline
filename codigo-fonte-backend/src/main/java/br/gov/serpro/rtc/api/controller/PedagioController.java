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

import br.gov.serpro.rtc.api.model.input.pedagio.PedagioInput;
import br.gov.serpro.rtc.api.model.output.pedagio.PedagioOutput;
import br.gov.serpro.rtc.api.openapi.controller.PedagioControllerOpenApi;
import br.gov.serpro.rtc.domain.model.enumeration.TipoWarningDadosSimulados;
import br.gov.serpro.rtc.domain.service.pedagio.PedagioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora")
public class PedagioController implements PedagioControllerOpenApi {

    private final PedagioService pedagioService;

    @Override
    @PostMapping(
        value = "pedagio",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PedagioOutput> calcularTributo(@RequestBody @Valid PedagioInput operacao) {
        PedagioOutput resultado = pedagioService.calcularCIBS(operacao);
        
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        
        TipoWarningDadosSimulados warning = pedagioService.getWarningDadosSimulados(operacao);
        if (warning != null) {
            responseBuilder.header("x-warning-dados-simulados", String.valueOf(warning.getValor()));
        }
        
        return responseBuilder.body(resultado);
    }

}

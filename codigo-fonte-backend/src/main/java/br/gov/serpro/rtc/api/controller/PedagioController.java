/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.input.pedagio.PedagioInput;
import br.gov.serpro.rtc.api.model.output.pedagio.PedagioOutput;
import br.gov.serpro.rtc.api.openapi.controller.PedagioControllerOpenApi;
import br.gov.serpro.rtc.domain.service.pedagio.PedagioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora")
public class PedagioController implements PedagioControllerOpenApi {

    private final PedagioService pedagioService;

    @Override
    @PostMapping("pedagio")
    public ResponseEntity<PedagioOutput> calcularTributo(@RequestBody @Valid PedagioInput operacao) {
        return ResponseEntity.ok(pedagioService.calcularCIBS(operacao));
    }

}

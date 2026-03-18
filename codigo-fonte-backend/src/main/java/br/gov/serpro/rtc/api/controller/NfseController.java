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

import br.gov.serpro.rtc.api.model.input.nfse.NfseBaseCalculoInput;
import br.gov.serpro.rtc.api.model.output.nfse.NfseBaseCalculoOutput;
import br.gov.serpro.rtc.api.openapi.controller.NfseControllerOpenApi;
import br.gov.serpro.rtc.domain.service.nfse.NfseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora/nfse")
public class NfseController implements NfseControllerOpenApi {

    private final NfseService nfseService;

    @Override
    @PostMapping(
        value = "base-calculo",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NfseBaseCalculoOutput> calcularBaseCalculo(@RequestBody NfseBaseCalculoInput input) {
        return ResponseEntity.ok(nfseService.calcularBaseCalculo(input));
    }

}

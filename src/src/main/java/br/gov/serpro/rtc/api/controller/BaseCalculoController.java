/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoCibsInput;
import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoISMercadoriasInput;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoCibsModel;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoISMercadoriasModel;
import br.gov.serpro.rtc.api.openapi.controller.BaseCalculoControllerOpenApi;
import br.gov.serpro.rtc.domain.service.basecalculo.BaseCalculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora/base-calculo")
public class BaseCalculoController implements BaseCalculoControllerOpenApi {

    private final BaseCalculoService baseCalculoService;

    @Override
    @PostMapping("is-mercadorias")
    public ResponseEntity<BaseCalculoISMercadoriasModel> calcularISMercadorias(
            @RequestBody @Valid BaseCalculoISMercadoriasInput input) {
        return ResponseEntity.ok(baseCalculoService.calcularISMercadorias(input));
    }

    @Override
    @PostMapping("cbs-ibs-mercadorias")
    public ResponseEntity<BaseCalculoCibsModel> calcularCibs(@RequestBody @Valid BaseCalculoCibsInput input) {
        return ResponseEntity.ok(baseCalculoService.calcularCibs(input));
    }

}

package br.gov.serpro.rtc.api.controller;

import br.gov.serpro.rtc.api.model.output.versaooffline.VersaoOfflineOutput;
import br.gov.serpro.rtc.domain.service.offline.VersaoOfflineService;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("offline")
@RestController
@RequiredArgsConstructor
public class VersaoOfflineController {

    private final VersaoOfflineService versaoOfflineService;

    @GetMapping("/versao/status")
    public VersaoOfflineOutput getVersaoStatus() {
        return versaoOfflineService.verificarVersao();
    }
}
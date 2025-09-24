package br.gov.serpro.rtc.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.output.DownloadUrlOutput;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("calculadora/download")
@Hidden
public class DownloadController {

    @Value("${application.downloadUrl.default:}")
    private String downloadUrlDefault;

    @Value("${application.downloadUrl.jar:}")
    private String downloadUrlJar;

    private final MeterRegistry meterRegistry;

    public DownloadController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/url")
    public ResponseEntity<DownloadUrlOutput> getDownloadUrl(
            @RequestParam(value = "platform") String platform) {

        try {
            String downloadUrl = getDownloadUrlByPlatform(platform);

            if (downloadUrl == null || downloadUrl.trim().isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            // Incrementa o contador com tag da plataforma
            Counter.builder("api_calc_downloads_total")
                    .description("Total number of download URL requests")
                    .tag("endpoint", "download-url")
                    .tag("platform", platform.toLowerCase())
                    .register(meterRegistry)
                    .increment();

            return ResponseEntity.ok(new DownloadUrlOutput(downloadUrl));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String getDownloadUrlByPlatform(String platform) {
        if (platform == null || platform.trim().isEmpty()) {
            throw new IllegalArgumentException("Parâmetro 'platform' não pode ser nulo ou vazio. Valores aceitos: 'default' ou 'jar'");
        }

        String platformLower = platform.toLowerCase().trim();

        if ("default".equals(platformLower)) {
            return downloadUrlDefault;
        } else if ("jar".equals(platformLower)) {
            return downloadUrlJar;
        } else {
            throw new IllegalArgumentException("Parâmetro 'platform' inválido. Valores aceitos: 'default' ou 'jar'");
        }
    }
}

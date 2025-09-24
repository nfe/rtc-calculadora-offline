/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.output.dadosabertos.AliquotaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.ClassificacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.FundamentacaoClassificacaoDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.MunicipioDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.NbsDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.NcmDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.SituacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.UfDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.VersaoOutput;
import br.gov.serpro.rtc.api.openapi.controller.DadosAbertosControllerOpenApi;
import br.gov.serpro.rtc.domain.service.VersaoBaseDadosService;
import br.gov.serpro.rtc.domain.service.dadosabertos.DadosAbertosService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora/dados-abertos")
public class DadosAbertosController implements DadosAbertosControllerOpenApi {

    @Value("${info.app.version:unknown}")
    private String versaoAplicacao;
    private final Environment environment;
    private final VersaoBaseDadosService versaoBaseDadosService;
    private final DadosAbertosService dadosAbertosService;

    @Override
    @GetMapping("/ufs")
    public ResponseEntity<List<UfDadosAbertosOutput>> consultarUfs() {
        return ResponseEntity.ok()
                .header("Cache-Control", "public, max-age=3600")
                .body(dadosAbertosService.consultarUfs());
    }

    @Override
    @GetMapping("/ufs/municipios")
    public ResponseEntity<List<MunicipioDadosAbertosOutput>> consultarMunicipiosPorSiglaUf(
            @RequestParam String siglaUf) {
        return ResponseEntity.ok()
                .header("Cache-Control", "public, max-age=3600")
                .body(dadosAbertosService.consultarMunicipiosPorSiglaUf(siglaUf));
    }

    @Override
    @GetMapping("/situacoes-tributarias/cbs-ibs")
    public ResponseEntity<List<SituacaoTributariaDadosAbertosOutput>> consultarSituacoesTributariasCbsIbs(
            @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarSituacoesTributarias(2L, data));
    }

    @Override
    @GetMapping("/classificacoes-tributarias/{idSituacaoTributaria}")
    public ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasPorIdSituacaoTributaria(
        @PathVariable Long idSituacaoTributaria, @RequestParam LocalDate data) {
        return ResponseEntity
                .ok(dadosAbertosService.consultarClassificacoesTributariasPorIdSituacaoTributaria(idSituacaoTributaria, data));
    }

    @Override
    @GetMapping("/classificacoes-tributarias/cbs-ibs")
    public ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasCbsIbs(
        @RequestParam LocalDate data) {
        return ResponseEntity
                .ok(dadosAbertosService.consultarClassificacoesTributariasCbsIbs(data));
    }

    @Override
    @GetMapping("/classificacoes-tributarias/imposto-seletivo")
    public ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasImpostoSeletivo(
        @RequestParam LocalDate data) {
        return ResponseEntity
                .ok(dadosAbertosService.consultarClassificacoesTributariasImpostoSeletivo(data));
    }

    @Override
    @GetMapping("/situacoes-tributarias/imposto-seletivo")
    public ResponseEntity<List<SituacaoTributariaDadosAbertosOutput>> consultarSituacoesTributariasImpostoSeletivo(
            @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarSituacoesTributarias(1L, data));
    }

    @Override
    @GetMapping("/ncm")
    public ResponseEntity<NcmDadosAbertosOutput> consultarNcm(
        @RequestParam String ncm, @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarNcm(ncm, data));
    }

    @Override
    @GetMapping("/nbs")
    public ResponseEntity<NbsDadosAbertosOutput> consultarNbs(
        @RequestParam String nbs, @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarNbs(nbs, data));
    }

    @Override
    @GetMapping("/fundamentacoes-legais")
    public ResponseEntity<List<FundamentacaoClassificacaoDadosAbertosOutput>> consultarFundamentacoesLegais(
        @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarFundamentacoesLegais(data));
    }

    @Override
    @GetMapping("/aliquota-uniao")
    public ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaUniao(
        @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarAliquota(2L, null, null, data));
    }

    @Override
    @GetMapping("/aliquota-uf")
    public ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaUf(
        @RequestParam Long codigoUf, @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarAliquota(3L, codigoUf, null, data));
    }

    @Override
    @GetMapping("/aliquota-municipio")
    public ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaMunicipio(
        @RequestParam Long codigoMunicipio, @RequestParam LocalDate data) {
        return ResponseEntity.ok(dadosAbertosService.consultarAliquota(4L, null, codigoMunicipio, data));
    }
    
    @Override
    @GetMapping("/versao")
    public ResponseEntity<VersaoOutput> consultarVersao() {
        String[] activeProfiles = environment.getActiveProfiles();
        String ambiente = activeProfiles.length > 0 ? String.join(",", activeProfiles) : "default";
        
        if ("local".equals(ambiente)) {
            ambiente = "online";
        }
        
        VersaoOutput versaoOutput = VersaoOutput.builder()
                .versaoApp(versaoAplicacao)
                .versaoDb(versaoBaseDadosService.getUltimaVersao().getNumeroVersao())
                .descricaoVersaoDb(versaoBaseDadosService.getUltimaVersao().getDescricao())
                .dataVersaoDb(versaoBaseDadosService.getUltimaVersao().getData().toString())
                .ambiente(ambiente)
                .build();
        return ResponseEntity.ok(versaoOutput);
    }

}

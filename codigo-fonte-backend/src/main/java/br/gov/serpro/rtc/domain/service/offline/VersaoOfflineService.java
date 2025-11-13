package br.gov.serpro.rtc.domain.service.offline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.gov.serpro.rtc.api.model.output.dadosabertos.VersaoOutput;
import br.gov.serpro.rtc.api.model.output.versaooffline.VersaoOfflineOutput;
import br.gov.serpro.rtc.domain.service.VersaoBaseDadosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Profile("offline")
@RequiredArgsConstructor
public class VersaoOfflineService {

    private static final int TENTATIVAS = 3;

    @Value("${versao.url}")
    private String urlVersaoRemota;

    @Value("${info.app.version:unknown}")
    private String versaoAplicacaoLocal;

    private final VersaoBaseDadosService versaoBaseDadosService;
    private final RestTemplate restTemplate = new RestTemplate();

    @EventListener(ApplicationReadyEvent.class)
    @Retryable(
        maxAttempts = TENTATIVAS,
        backoff = @Backoff(delay = 2000),
        noRetryFor = {IllegalArgumentException.class}
    )
    public void verificarVersaoNaInicializacao() {
        VersaoOfflineOutput status = verificarVersao();
        exibirStatusVersao(status);
    }

    @Recover
    public void recuperarDeErroDeVersao(Exception e) {
        log.warn("Não foi possível verificar versão remota após {} tentativas: {}", TENTATIVAS, e.getMessage());
    }

    public VersaoOfflineOutput verificarVersao() {
        VersaoOutput versaoRemota = consultarVersaoRemota();
        return compararVersoes(versaoRemota);
    }

    private VersaoOutput consultarVersaoRemota() {
        return restTemplate.getForObject(urlVersaoRemota, VersaoOutput.class);
    }

    private VersaoOfflineOutput compararVersoes(VersaoOutput versaoRemota) {
        String versaoDbLocal = versaoBaseDadosService.getUltimaVersao().getNumeroVersao();
        String dataDbLocal = versaoBaseDadosService.getUltimaVersao().getData().toString();

        String versaoAplicacaoRemota = versaoRemota != null ? versaoRemota.getVersaoApp() : null;
        String versaoDbRemota = versaoRemota != null ? versaoRemota.getVersaoDb() : null;
        String dataDbRemota = versaoRemota != null ? versaoRemota.getDataVersaoDb() : null;

        boolean aplicacaoAtualizada = versaoAplicacaoLocal.equals(versaoAplicacaoRemota);
        boolean dbAtualizada = versaoDbLocal.equals(versaoDbRemota);
        boolean dataDbAtualizada = dataDbLocal.equals(dataDbRemota);

        VersaoOfflineOutput output = new VersaoOfflineOutput();
        output.setAplicacaoAtualizada(aplicacaoAtualizada);
        output.setDbAtualizada(dbAtualizada);
        output.setDataDbAtualizada(dataDbAtualizada);
        output.setVersaoAplicacaoLocal(versaoAplicacaoLocal);
        output.setVersaoAplicacaoRemota(versaoAplicacaoRemota);
        output.setVersaoDbLocal(versaoDbLocal);
        output.setVersaoDbRemota(versaoDbRemota);
        output.setDataDbLocal(dataDbLocal);
        output.setDataDbRemota(dataDbRemota);

        return output;
    }

    private void exibirStatusVersao(VersaoOfflineOutput status) {
        List<String> atualizados = new ArrayList<>();
        List<String> desatualizados = new ArrayList<>();

        // Aplicação
        if (status.isAplicacaoAtualizada()) {
            atualizados.add("Aplicação (LOCAL):  " + status.getVersaoAplicacaoLocal());
            atualizados.add("Aplicação (ONLINE): " + status.getVersaoAplicacaoRemota());
        } else {
            desatualizados.add("Aplicação (LOCAL):  " + status.getVersaoAplicacaoLocal());
            desatualizados.add("Aplicação (ONLINE): " + status.getVersaoAplicacaoRemota());
        }

        // Banco de Dados
        if (status.isDbAtualizada()) {
            atualizados.add("Banco de Dados (LOCAL):  " + status.getVersaoDbLocal());
            atualizados.add("Banco de Dados (ONLINE): " + status.getVersaoDbRemota());
        } else {
            desatualizados.add("Banco de Dados (LOCAL):  " + status.getVersaoDbLocal());
            desatualizados.add("Banco de Dados (ONLINE): " + status.getVersaoDbRemota());
        }

        // Data do Banco
        if (status.isDataDbAtualizada()) {
            atualizados.add("Data do Banco (LOCAL):  " + status.getDataDbLocal());
            atualizados.add("Data do Banco (ONLINE): " + status.getDataDbRemota());
        } else {
            desatualizados.add("Data do Banco (LOCAL):  " + status.getDataDbLocal());
            desatualizados.add("Data do Banco (ONLINE): " + status.getDataDbRemota());
        }

        if (!atualizados.isEmpty()) {
            log.info("= VERSÃO ATUALIZADA " + "=".repeat(30));
            for (String linha : atualizados) {
                log.info(linha);
            }
        }
        if (!desatualizados.isEmpty()) {
            log.info("= VERSÃO DESATUALIZADA " + "=".repeat(27));
            for (String linha : desatualizados) {
                log.info(linha);
            }
        }
    }
}
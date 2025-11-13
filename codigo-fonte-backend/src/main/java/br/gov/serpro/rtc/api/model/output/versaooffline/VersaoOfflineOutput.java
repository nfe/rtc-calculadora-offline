package br.gov.serpro.rtc.api.model.output.versaooffline;

import lombok.Data;

@Data
public class VersaoOfflineOutput {
    private boolean aplicacaoAtualizada;
    private boolean dbAtualizada;
    private boolean dataDbAtualizada;
    private String versaoAplicacaoLocal;
    private String versaoAplicacaoRemota;
    private String versaoDbLocal;
    private String versaoDbRemota;
    private String dataDbLocal;
    private String dataDbRemota;
}

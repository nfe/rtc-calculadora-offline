/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class GrupoMonofasiaOutput {

    private GrupoEtapaMonofasiaOutput tributoMonofasico;
    private GrupoEtapaMonofasiaOutput tributoSujeitoRetencao;
    private GrupoEtapaMonofasiaOutput tributoRetido;
    private GrupoDiferimentoMonofasiaOutput tributoDiferido;

}

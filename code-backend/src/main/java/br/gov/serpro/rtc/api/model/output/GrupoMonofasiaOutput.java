/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class GrupoMonofasiaOutput {

    @Schema(name = "tributoMonofasico", description = "Tributo monofásico", example = "")
    private GrupoEtapaMonofasiaOutput tributoMonofasico;

    @Schema(name = "tributoSujeitoRetencao", description = "Tributo sujeito a retenção", example = "")
    private GrupoEtapaMonofasiaOutput tributoSujeitoRetencao;

    @Schema(name = "tributoRetido", description = "Tributo retido anteriormente", example = "")
    private GrupoEtapaMonofasiaOutput tributoRetido;

    @Schema(name = "tributoDiferido", description = "Tributo diferido", example = "")
    private GrupoDiferimentoMonofasiaOutput tributoDiferido;

}

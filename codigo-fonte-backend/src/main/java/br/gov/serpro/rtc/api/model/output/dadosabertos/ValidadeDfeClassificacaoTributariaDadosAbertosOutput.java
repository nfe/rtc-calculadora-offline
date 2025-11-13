/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.dadosabertos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class ValidadeDfeClassificacaoTributariaDadosAbertosOutput implements SerializationVisibility {

    @Schema(name = "siglaDfeInformado", description = "Sigla do DFE informado", example = "NFSe")
    private String siglaDfeInformado;

    @Schema(name = "validoParaSiglaDfeInformado", description = "Indica se a classificação tributária é válida para a sigla do DFE informado", example = "true")
    private boolean validoParaSiglaDfeInformado;

    @Schema(name = "nomenclatura", description = "Nomenclatura da classificação tributária informada", example = "NBS ou NCM")
    private String nomenclatura;

    @Schema(name = "exigeGrupoTributacaoRegular", description = "Indica se a classificação tributária exige grupo de tributação regular", example = "false")
    private boolean exigeGrupoTributacaoRegular;

    @Schema(name = "permiteDiferimento", description = "Indica se a classificação tributária permite diferimento", example = "false")
    private boolean permiteDiferimento;

}

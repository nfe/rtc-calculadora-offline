/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.dadosabertos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class SituacaoTributariaDadosAbertosOutput {

    @Schema(name = "id", description = "Identificador único da situacao tributária", example = "000")
    private Long id;

    @Schema(name = "codigo", description = "Código da situacao tributária", example = "000")
    private String codigo;

    @Schema(name = "descricao", description = "Descricao da situacao tributária", example = "Tributação Integral")
    private String descricao;

}

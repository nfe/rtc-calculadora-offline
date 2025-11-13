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
public class TipoDfeClassificacaoDadosAbertosOutput {

    @Schema(name = "tipo", description = "Tipo de DFE", example = "55")
    private Integer tipo;

    @Schema(name = "sigla", description = "Sigla do DFE", example = "NF-e")
    private String sigla;

    @Schema(name = "descricao", description = "Descrição do DFE", example = "Documento Fiscal Eletrônico")
    private String descricao;

}

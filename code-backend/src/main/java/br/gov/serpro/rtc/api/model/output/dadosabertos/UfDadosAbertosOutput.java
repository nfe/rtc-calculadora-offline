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
public class UfDadosAbertosOutput {

    @Schema(name = "sigla", description = "Sigla da UF", example = "RS")
    private final String sigla;

    @Schema(name = "nome", description = "Nome da UF", example = "Rio Grande do Sul")
    private final String nome;

    @Schema(name = "codigo", description = "Código IBGE da UF", example = "43")
    private final Long codigo;
}

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
public class MunicipioDadosAbertosOutput {

    @Schema(name = "codigo", description = "Código IBGE do Município", example = "4314902")
    private final Long codigo;

    @Schema(name = "nome", description = "Nome do município", example = "Porto Alegre")
    private final String nome;
}

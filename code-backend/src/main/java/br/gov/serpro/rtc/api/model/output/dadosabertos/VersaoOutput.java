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
public class VersaoOutput {

    @Schema(name = "versaoApp", description = "Versão do aplicativo", example = "0.0.0-SNAPSHOT")
    private final String versaoApp;

    @Schema(name = "versaoDb", description = "Versão do banco de dados", example = "1.0.0")
    private final String versaoDb;

    @Schema(name = "descricaoVersaoDb", description = "Descrição da versão do banco de dados", example = "Versão de homologação")
    private final String descricaoVersaoDb;

    @Schema(name = "dataVersaoDb", description = "Data da versão do banco de dados", example = "2026-01-01")
    private final String dataVersaoDb;

    @Schema(name = "ambiente", description = "Ambiente da aplicação", example = "Online")
    private final String ambiente;

}

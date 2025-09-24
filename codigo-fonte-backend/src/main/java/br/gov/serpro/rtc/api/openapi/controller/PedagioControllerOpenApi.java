/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.openapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import br.gov.serpro.rtc.api.model.input.pedagio.PedagioInput;
import br.gov.serpro.rtc.api.model.output.pedagio.PedagioOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedágio - VERSÃO BETA", description = "IVA - Calculadora de Tributos para o Pedágio")
public interface PedagioControllerOpenApi {

    @Operation(summary = "Cálculo do tributo", description = "Calculo do pedágio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PedagioOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    ResponseEntity<PedagioOutput> calcularTributo(
            @RequestBody(
                    description = "Dados para cálculo do pedágio",
                    required = true,
                    content = @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = PedagioInput.class)
                    )
                ) PedagioInput operacao);
}

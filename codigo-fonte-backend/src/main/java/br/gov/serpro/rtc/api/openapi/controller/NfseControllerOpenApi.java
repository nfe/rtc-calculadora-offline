/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.openapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import br.gov.serpro.rtc.api.model.input.nfse.NfseBaseCalculoInput;
import br.gov.serpro.rtc.api.model.output.nfse.NfseBaseCalculoOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "NFS-e - VERSÃO BETA", description = "Serviço para cálculo de Base de Cálculo de NFS-e")
public interface NfseControllerOpenApi {

    @Operation(summary = "Base de Cálculo NFS-e", description = "Afere a Base de Cálculo de uma NFS-e no período de transição. "
            + "ATENÇÃO: Os campos PIS e COFINS não podem ser informados a partir de 2027. "
            + "O campo ISS não pode ser informado a partir de 2033.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = NfseBaseCalculoOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido ou campos incompatíveis com o ano do fato gerador informado (PIS e COFINS a partir de 2027; ISS a partir de 2033)", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    ResponseEntity<NfseBaseCalculoOutput> calcularBaseCalculo(
            @Valid
            @RequestBody(
                    description = "Dados da base de cálculo da NFS-e", 
                    required = true, 
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE, 
                            schema = @Schema(implementation = NfseBaseCalculoInput.class))
                    ) NfseBaseCalculoInput baseCalculo);
}

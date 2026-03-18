/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.openapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoCibsInput;
import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoISMercadoriasInput;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoCibsModel;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoISMercadoriasModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Base de Cálculo - VERSÃO BETA", description = "Serviço para Base de Cálculo")
public interface BaseCalculoControllerOpenApi {

    @Operation(summary = "Imposto Seletivo", description = "Afere a Base de Cálculo do Imposto Seletivo de uma operação de consumo. "
            + "ATENÇÃO: Os campos ICMS e ISS não podem ser informados a partir de 2033.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseCalculoISMercadoriasModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido ou campos incompatíveis com o ano do fato gerador informado (ICMS e ISS a partir de 2033)", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    ResponseEntity<BaseCalculoISMercadoriasModel> calcularISMercadorias(
            @RequestBody(
                    description = "Dados da base de cálculo do Imposto Seletivo", 
                    required = true, 
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE, 
                            schema = @Schema(implementation = BaseCalculoISMercadoriasInput.class))
                    ) BaseCalculoISMercadoriasInput baseCalculo);

    @Operation(summary = "CIBS", description = "Afere a Base de Cálculo da CBS/IBS de uma operação de consumo. "
            + "ATENÇÃO: Os campos PIS, COFINS, PIS Importação e COFINS Importação não podem ser informados a partir de 2027. "
            + "Os campos ICMS e ISS não podem ser informados a partir de 2033.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseCalculoISMercadoriasModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido ou campos incompatíveis com o ano do fato gerador informado (PIS, COFINS, PIS Importação e COFINS a partir de 2027; ICMS e ISS a partir de 2033)", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    ResponseEntity<BaseCalculoCibsModel> calcularCibs(
            @RequestBody(
                    description = "Dados da base de cálculo do CIBS", 
                    required = true, 
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE, 
                            schema = @Schema(implementation = BaseCalculoCibsInput.class))
                    ) BaseCalculoCibsInput baseCalculo);
}

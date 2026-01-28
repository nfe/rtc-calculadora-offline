/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.openapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Calculadora - VERSÃO BETA", description = "Calculadora de Tributos")
public interface CalculadoraTributoControllerOpenApi {

    @Operation(summary = "Cálculo do tributo", description = "Calcula os tributos a partir dos dados de uma operação de consumo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso",
            headers = @Header(
                name = "x-warning-dados-simulados",
                description = "Indica que os dados são simulados. Valores possíveis: " +
                             "1 (alíquotas da CBS e do IBS ainda não definidas em lei), " +
                             "2 (alíquotas da CBS e do IBS, e redutor em Compras Governamentais ainda não definidos em lei), " +
                             "3 (alíquotas da CBS e do IBS, alíquotas e Classificações Tributárias do IS ainda não definidos em lei), " +
                             "4 (alíquotas da CBS e do IBS, redutor em Compras Governamentais fictícios, alíquotas e Classificações Tributárias do IS ainda não definidos em lei), " +
                             "5 (para o cClassTrib informado a alíquota ainda não foi definida em lei). ",
                schema = @Schema(type = "integer", example = "1")
            ), content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ROCDomain.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    ResponseEntity<ROCDomain> calcularTributos(
            @RequestBody(description = "Dados de uma operação de consumo", required = true, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OperacaoInput.class))) OperacaoInput operacao);

}
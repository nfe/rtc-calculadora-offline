/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.openapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import br.gov.serpro.rtc.api.model.output.dadosabertos.AliquotaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.ClassificacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.FundamentacaoClassificacaoDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.MunicipioDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.NbsDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.NcmDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.SituacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.UfDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.VersaoOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Dados Abertos - VERSÃO BETA", description = "Consultas para os Dados Abertos")
public interface DadosAbertosControllerOpenApi {

    @Operation(summary = "Unidade Federativa", description = "Obtém a lista das unidades federativas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UfDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<UfDadosAbertosOutput>> consultarUfs();

    @Operation(summary = "Município", description = "Obtém a lista dos municípios cadastrados com base na sigla de uma unidade federativa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = MunicipioDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Sigla da UF inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<MunicipioDadosAbertosOutput>> consultarMunicipiosPorSiglaUf(
            @Parameter(description = "Sigla da unidade federativa", example = "RS", required = true) String siglaUf);

    @Operation(summary = "Situação Tributária (CST)", description = "Obtém a lista das situações tributárias cadastradas vigentes em um determinada data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SituacaoTributariaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<SituacaoTributariaDadosAbertosOutput>> consultarSituacoesTributariasCbsIbs(
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Classificação Tributária (cClassTrib)", description = "Obtém a lista das classificações tributárias (cClassTrib) cadastradas com base no código CST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClassificacaoTributariaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Código CST informado inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasPorIdSituacaoTributaria(
            @Parameter(description = "Id da Situação Tributária (CST)", example = "1", required = true) Long idSituacaoTributaria,
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Situação Tributária (CST)", description = "Obtém a lista das situações tributárias cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SituacaoTributariaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<SituacaoTributariaDadosAbertosOutput>> consultarSituacoesTributariasImpostoSeletivo(
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);

    @Operation(summary = "Nomenclatura Comum do Mercosul (NCM)", description = "Obtém informações sobre a NCM em relação ao Imposto Seletivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = NcmDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<NcmDadosAbertosOutput> consultarNcm(
            @Parameter(description = "Código NCM sem formatação", example = "24021000", required = true) String ncm,
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);

    @Operation(summary = "Nomenclatura Brasileira de Serviços (NBS)", description = "Obtém informações sobre a NBS em relação ao Imposto Seletivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = NbsDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<NbsDadosAbertosOutput> consultarNbs(
            @Parameter(description = "Código NBS sem formatação", example = "114052200", required = true) String nbs,
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);

    @Operation(summary = "Fundamentação Legal", description = "Obtém informações sobre as fundamentações legais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FundamentacaoClassificacaoDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<FundamentacaoClassificacaoDadosAbertosOutput>> consultarFundamentacoesLegais(
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);

    @Operation(summary = "Classificação Tributária (cClassTrib)", description = "Obtém a lista das classificações tributárias (cClassTrib) para CBS e IBS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClassificacaoTributariaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Código CST informado inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasCbsIbs(
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Classificação Tributária (cClassTrib)", description = "Obtém a lista das classificações tributárias (cClassTrib) para Imposto Seletivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClassificacaoTributariaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Código CST informado inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasImpostoSeletivo(
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Alíquota Padrão ou de Referência", description = "Obtém a alíquota padrão ou de referência para CBS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AliquotaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Código CST informado inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaUniao(
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Alíquota Padrão ou de Referência", description = "Obtém a alíquota padrão ou de referência para IBS Estadual")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AliquotaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Código CST informado inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaUf(
            @Parameter(description = "Código da UF", example = "43", required = true) Long codigoUf,
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)",  example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Alíquota Padrão ou de Referência", description = "Obtém a alíquota padrão ou de referência para IBS Municipal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AliquotaDadosAbertosOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Código CST informado inexistente", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaMunicipio(
            @Parameter(description = "Código do Município (Tabela IBGE)", example = "4314902", required = true) Long codigoMunicipio,
            @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Versão do Aplicativo e do Banco de Dados", description = "Obtém a versão do aplicativo e do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = VersaoOutput.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição com problema", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Versão não encontrada", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<VersaoOutput> consultarVersao();
    
}

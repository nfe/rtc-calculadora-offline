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
import br.gov.serpro.rtc.api.model.output.dadosabertos.ValidadeDfeClassificacaoTributariaDadosAbertosOutput;
import br.gov.serpro.rtc.api.model.output.dadosabertos.VersaoOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Dados Abertos - VERSÃO BETA", description = "Consultas para os Dados Abertos")
public interface DadosAbertosControllerOpenApi {

    @Operation(summary = "Unidade Federativa", description = "Obtém a lista das unidades federativas cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UfDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "UFs Example",
                    value = """
                    [
                      { "sigla": "RO", "nome": "RONDÔNIA", "codigo": 11 },
                      { "sigla": "AC", "nome": "ACRE", "codigo": 12 }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                      "type": "about:blank",
                      "title": "Bad Request",
                      "status": 400,
                      "detail": "Parâmetro inválido ou ausente.",
                      "instance": "/api/calculadora/dados-abertos/ufs"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Erro na URL da requisição",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Not Found Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Not Found",
                        "status": 404,
                        "detail": "No static resource calculadora/dados-abertos/ufs/1.",
                        "instance": "/api/calculadora/dados-abertos/ufs/1"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/ufs"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<UfDadosAbertosOutput>> consultarUfs();

    @Operation(summary = "Município", description = "Obtém a lista dos municípios cadastrados com base na sigla de uma unidade federativa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = MunicipioDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Municípios Example",
                    value = """
                    [
                      {
                        "codigo": 4314902,
                        "nome": "Porto Alegre"
                      },
                      {
                        "codigo": 4305108,
                        "nome": "Caxias do Sul"
                      }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
            @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/municipios/RS"
                    }
                    """
                )
            )
        })
    })
    ResponseEntity<List<MunicipioDadosAbertosOutput>> consultarMunicipiosPorSiglaUf(
        @Parameter(description = "Sigla da unidade federativa", example = "RS", required = true) String siglaUf);

    @Operation(
        summary = "Situação Tributária (CST)",
        description = "Obtém a lista das situações tributárias cadastradas vigentes em uma determinada data para CBS/IBS"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = SituacaoTributariaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Situações Tributárias Example",
                    value = """
                    [
                        {
                            "codigo": "000001",
                            "descricao": "Situações tributadas integralmente pelo IBS e CBS.",
                            "tipoAliquota": "Padrão",
                            "nomenclatura": "NBS ou NCM",
                            "descricaoTratamentoTributario": "Tributação integral",
                            "incompativelComSuspensao": false,
                            "exigeGrupoDesoneracao": false,
                            "possuiPercentualReducao": true,
                            "indicaApropriacaoCreditoAdquirenteCbs": true,
                            "indicaApropriacaoCreditoAdquirenteIbs": true,
                            "indicaCreditoPresumidoFornecedor": false,
                            "indicaCreditoPresumidoAdquirente": false,
                            "creditoOperacaoAntecedente": "Manutenção",
                            "percentualReducaoCbs": 0,
                            "percentualReducaoIbsUf": 0,
                            "percentualReducaoIbsMun": 0,
                            "tiposDfeClassificacao": []
                        },
                        {
                            "codigo": "000002",
                            "descricao": "Exploração de via",
                            "tipoAliquota": "Padrão",
                            "nomenclatura": "NBS",
                            "descricaoTratamentoTributario": "Tributação integral",
                            "incompativelComSuspensao": false,
                            "exigeGrupoDesoneracao": false,
                            "possuiPercentualReducao": true,
                            "indicaApropriacaoCreditoAdquirenteCbs": true,
                            "indicaApropriacaoCreditoAdquirenteIbs": true,
                            "indicaCreditoPresumidoFornecedor": false,
                            "indicaCreditoPresumidoAdquirente": false,
                            "creditoOperacaoAntecedente": "Manutenção",
                            "percentualReducaoCbs": 0,
                            "percentualReducaoIbsUf": 0,
                            "percentualReducaoIbsMun": 0,
                            "tiposDfeClassificacao": []
                        }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Required parameter 'data' is not present.",
                        "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/situacoes-tributarias/cbs-ibs"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<SituacaoTributariaDadosAbertosOutput>> consultarSituacoesTributariasCbsIbs(
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Classificação Tributária (cClassTrib)", description = "Obtém a lista das classificações tributárias (cClassTrib) cadastradas com base no código CST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ClassificacaoTributariaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Classificações Tributárias Example",
                    value = """
                    [
                        {
                            "codigo": "000001",
                            "descricao": "Situações tributadas integralmente pelo IBS e CBS.",
                            "tipoAliquota": "Padrão",
                            "nomenclatura": "NBS ou NCM",
                            "descricaoTratamentoTributario": "Tributação integral",
                            "incompativelComSuspensao": false,
                            "exigeGrupoDesoneracao": false,
                            "possuiPercentualReducao": true,
                            "indicaApropriacaoCreditoAdquirenteCbs": false,
                            "indicaApropriacaoCreditoAdquirenteIbs": false,
                            "indicaCreditoPresumidoFornecedor": false,
                            "indicaCreditoPresumidoAdquirente": false,
                            "tiposDfeClassificacao": []
                        },
                        {
                            "codigo": "000002",
                            "descricao": "Exploração de via",
                            "tipoAliquota": "Padrão",
                            "nomenclatura": "NBS",
                            "descricaoTratamentoTributario": "Tributação integral",
                            "incompativelComSuspensao": false,
                            "exigeGrupoDesoneracao": false,
                            "possuiPercentualReducao": true,
                            "indicaApropriacaoCreditoAdquirenteCbs": false,
                            "indicaApropriacaoCreditoAdquirenteIbs": false,
                            "indicaCreditoPresumidoFornecedor": false,
                            "indicaCreditoPresumidoAdquirente": false,
                            "tiposDfeClassificacao": []
                        }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Required parameter 'data' is not present.",
                        "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/3244253"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/1"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasPorIdSituacaoTributaria(
        @Parameter(description = "Id da Situação Tributária (CST)", example = "1", required = true) Long idSituacaoTributaria,
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(
        summary = "Situação Tributária (CST)",
        description = "Obtém a lista das situações tributárias cadastradas para Imposto Seletivo"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = SituacaoTributariaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Situações Tributárias Example",
                    value = """
                    [
                      {
                        "id": 19,
                        "codigo": "000",
                        "descricao": "Tributado com Imposto Seletivo"
                      },
                      {
                        "id": 20,
                        "codigo": "100",
                        "descricao": "Imunidade"
                      }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                      "type": "about:blank",
                      "title": "Bad Request",
                      "status": 400,
                      "detail": "Required parameter 'data' is not present.",
                      "instance": "/api/calculadora/dados-abertos/situacoes-tributarias/imposto-seletivo"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/situacoes-tributarias/imposto-seletivo"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<SituacaoTributariaDadosAbertosOutput>> consultarSituacoesTributariasImpostoSeletivo(
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);


    @Operation(summary = "Nomenclatura Comum do Mercosul (NCM)", description = "Obtém informações sobre a NCM em relação ao Imposto Seletivo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = NcmDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "NCM Example",
                    value = """
                    {
                        "tributadoPeloImpostoSeletivo": false,
                        "capitulo": "Tabaco e seus sucedâneos manufaturados; produtos, mesmo com nicotina, destinados à inalação sem combustão; outros produtos que contenham nicotina destinados à absorção da nicotina pelo corpo humano.",
                        "posicao": "Charutos, cigarrilhas e cigarros, de tabaco ou dos seus sucedâneos.",
                        "subitem": "Charutos e cigarrilhas, que contenham tabaco"
                    }
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Required parameter 'data' is not present.",
                        "instance": "/api/calculadora/dados-abertos/ncm/24021000"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                        "type": "http://url-ambiente/errors/erro-interno",
                        "title": "Erro interno na API",
                        "status": 500,
                        "detail": "Falha ao processar a requisição.",
                        "instance": "/api/calculadora/dados-abertos/ncm/24021000"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<NcmDadosAbertosOutput> consultarNcm(
        @Parameter(description = "Código NCM sem formatação", example = "24021000", required = true) String ncm,
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);

    @Operation(summary = "Nomenclatura Brasileira de Serviços (NBS)", description = "Obtém informações sobre a NBS em relação ao Imposto Seletivo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = NbsDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "NBS Example",
                    value = """
                    {
                      "tributadoPeloImpostoSeletivo": false,
                      "capitulo": "Serviços veterinários",
                      "posicao": "Serviços veterinários para animais de corte",
                      "item": "Serviços de atendimento, assistência ou tratamento para animais de corte"
                    }
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                      "type": "about:blank",
                      "title": "Bad Request",
                      "status": 400,
                      "detail": "Required parameter 'data' is not present.",
                      "instance": "/api/calculadora/dados-abertos/nbs/114052200"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/nbs/114052200"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<NbsDadosAbertosOutput> consultarNbs(
        @Parameter(description = "Código NBS sem formatação", example = "114052200", required = true) String nbs,
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);


    @Operation(summary = "Fundamentação Legal", description = "Obtém informações sobre as fundamentações legais")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = FundamentacaoClassificacaoDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Fundamentações Legais Example",
                    value = """
                    [
                      {
                        "codigoClassificacaoTributaria": "000001",
                        "descricaoClassificacaoTributaria": "Situações tributadas integralmente pelo IBS e CBS.",
                        "codigoSituacaoTributaria": "000",
                        "descricaoSituacaoTributaria": "Tributação integral",
                        "conjuntoTributo": "CBS e IBS",
                        "texto": "",
                        "textoCurto": "",
                        "referenciaNormativa": "LIVRO I \\n\\nDO IMPOSTO SOBRE BENS E SERVIÇOS (IBS) E DA CONTRIBUIÇÃO SOCIAL SOBRE BENS E SERVIÇOS (CBS) \\n\\nTÍTULO I \\n\\nDAS NORMAS GERAIS DO IBS E DA CBS \\n\\nCAPÍTULO I \\n\\nDISPOSIÇÕES PRELIMINARES"
                      },
                      {
                        "codigoClassificacaoTributaria": "000002",
                        "descricaoClassificacaoTributaria": "Exploração de via",
                        "codigoSituacaoTributaria": "000",
                        "descricaoSituacaoTributaria": "Tributação integral",
                        "conjuntoTributo": "CBS e IBS",
                        "texto": "Art. 11. Considera-se local da operação com:\\nVIII - serviço de exploração de via, mediante cobrança de valor a qualquer título, incluindo tarifas, pedágios e quaisquer outras formas de cobrança, o território de cada Município e Estado, ou do Distrito Federal, proporcionalmente à correspondente extensão da via explorada;",
                        "textoCurto": "Art. 11, VIII",
                        "referenciaNormativa": "LIVRO I \\n\\nDO IMPOSTO SOBRE BENS E SERVIÇOS (IBS) E DA CONTRIBUIÇÃO SOCIAL SOBRE BENS E SERVIÇOS (CBS) \\n\\nTÍTULO I \\n\\nDAS NORMAS GERAIS DO IBS E DA CBS \\n\\nCAPÍTULO II\\n\\nDO IBS E DA CBS SOBRE OPERAÇÕES COM BENS E SERVIÇOS\\n\\nSeção IV\\n\\nDo Local da Operação"
                      }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                      "type": "about:blank",
                      "title": "Bad Request",
                      "status": 400,
                      "detail": "Required parameter 'data' is not present.",
                      "instance": "/api/calculadora/dados-abertos/fundamentacoes-legais"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/fundamentacoes-legais"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<FundamentacaoClassificacaoDadosAbertosOutput>> consultarFundamentacoesLegais(
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2027-01-01", required = true) LocalDate data);

    @Operation(summary = "Classificação Tributária (cClassTrib)", description = "Obtém a lista das classificações tributárias (cClassTrib) para CBS e IBS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ClassificacaoTributariaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Classificações Tributárias Example",
                    value = """
                    [
                        {
                        "codigo": "000004",
                        "descricao": "Regime automotivo - projetos incentivados (art. 312)",
                        "tipoAliquota": "Padrão",
                        "nomenclatura": "NCM",
                        "descricaoTratamentoTributario": "Tributação integral",
                        "incompativelComSuspensao": false,
                        "exigeGrupoDesoneracao": false,
                        "possuiPercentualReducao": true,
                        "indicaApropriacaoCreditoAdquirenteCbs": true,
                        "indicaApropriacaoCreditoAdquirenteIbs": true,
                        "indicaCreditoPresumidoFornecedor": true,
                        "indicaCreditoPresumidoAdquirente": false,
                        "creditoOperacaoAntecedente": "Manutenção",
                        "percentualReducaoCbs": 0,
                        "percentualReducaoIbsUf": 0,
                        "percentualReducaoIbsMun": 0,
                        "tiposDfeClassificacao": [
                            {
                            "tipo": 55,
                            "sigla": "NFe",
                            "descricao": "Nota Fiscal Eletrônica"
                            }
                        ]
                        },
                        {
                        "codigo": "010001",
                        "descricao": "Operações do FGTS não realizadas pela Caixa Econômica Federal",
                        "tipoAliquota": "Uniforme setorial",
                        "nomenclatura": "NBS",
                        "descricaoTratamentoTributario": "Tributação com alíquotas uniformes - operações do FGTS",
                        "incompativelComSuspensao": false,
                        "exigeGrupoDesoneracao": false,
                        "possuiPercentualReducao": true,
                        "indicaApropriacaoCreditoAdquirenteCbs": true,
                        "indicaApropriacaoCreditoAdquirenteIbs": true,
                        "indicaCreditoPresumidoFornecedor": false,
                        "indicaCreditoPresumidoAdquirente": false,
                        "creditoOperacaoAntecedente": "Manutenção",
                        "percentualReducaoCbs": 0,
                        "percentualReducaoIbsUf": 0,
                        "percentualReducaoIbsMun": 0,
                        "tiposDfeClassificacao": [
                            {
                            "tipo": 91,
                            "sigla": "NFSe",
                            "descricao": "Nota Fiscal de Serviços Eletrônica"
                            },
                            {
                            "tipo": 94,
                            "sigla": "DERE",
                            "descricao": "Declaração Eletrônica de Regimes Específicos"
                            }
                        ]
                        }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Required parameter 'data' is not present.",
                        "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                        "type": "http://url-ambiente/errors/erro-interno",
                        "title": "Erro interno na API",
                        "status": 500,
                        "detail": "Falha ao processar a requisição.",
                        "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasCbsIbs(
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(
        summary = "Classificação Tributária (cClassTrib) para Imposto Seletivo",
        description = "Obtém a lista das classificações tributárias (cClassTrib) para Imposto Seletivo"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Consulta realizada com sucesso",
            headers = @Header(
                name = "x-warning-dados-simulados",
                description = "Indica que os dados são simulados. Valores possíveis: " +
                             "1 (alíquotas da CBS e do IBS ainda não definidas em lei).",
                schema = @Schema(type = "integer", example = "1")
            ),
            content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ClassificacaoTributariaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Classificações Tributárias Imposto Seletivo Example",
                    value = """
                    [
                        {
                        "codigo": "000001",
                        "descricao": "Primeiro fornecimento a qualquer título de bem",
                        "tipoAliquota": "Alíquotas Combinadas (Ad Valorem e Ad Rem)",
                        "nomenclatura": "NBS ou NCM",
                        "descricaoTratamentoTributario": "Tributação pelo Imposto Seletivo - Com Cálculo",
                        "incompativelComSuspensao": false,
                        "exigeGrupoDesoneracao": false,
                        "possuiPercentualReducao": true,
                        "indicaApropriacaoCreditoAdquirenteCbs": false,
                        "indicaApropriacaoCreditoAdquirenteIbs": false,
                        "indicaCreditoPresumidoFornecedor": false,
                        "indicaCreditoPresumidoAdquirente": false
                        },
                        {
                        "codigo": "000002",
                        "descricao": "Arrematação em leilão público",
                        "tipoAliquota": "Alíquotas Combinadas (Ad Valorem e Ad Rem)",
                        "nomenclatura": "NBS ou NCM",
                        "descricaoTratamentoTributario": "Tributação pelo Imposto Seletivo - Com Cálculo",
                        "incompativelComSuspensao": false,
                        "exigeGrupoDesoneracao": false,
                        "possuiPercentualReducao": true,
                        "indicaApropriacaoCreditoAdquirenteCbs": false,
                        "indicaApropriacaoCreditoAdquirenteIbs": false,
                        "indicaCreditoPresumidoFornecedor": false,
                        "indicaCreditoPresumidoAdquirente": false
                        }
                    ]
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Required parameter 'data' is not present.",
                        "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/imposto-seletivo"
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                        "type": "http://url-ambiente/errors/erro-interno",
                        "title": "Erro interno na API",
                        "status": 500,
                        "detail": "Falha ao processar a requisição.",
                        "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/imposto-seletivo"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<List<ClassificacaoTributariaDadosAbertosOutput>> consultarClassificacoesTributariasImpostoSeletivo(
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(
        summary = "Alíquota Padrão ou de Referência",
        description = "Obtém a alíquota padrão ou de referência para CBS"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Consulta realizada com sucesso",
            headers = @Header(
                name = "x-warning-dados-simulados",
                description = "Indica que os dados são simulados. Valores possíveis: " +
                             "1 (Classificações Tributárias para o Imposto Seletivo ainda não foram estabelecidas em lei).",
                schema = @Schema(type = "integer", example = "1")
            ),
            content = @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = AliquotaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Alíquota União Example",
                    value = """
                    {
                        "aliquotaReferencia": 8.5
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                        "type": "about:blank",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Required parameter 'data' is not present.",
                        "instance": "/api/calculadora/dados-abertos/aliquota-uniao"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                        "type": "http://url-ambiente/errors/erro-interno",
                        "title": "Erro interno na API",
                        "status": 500,
                        "detail": "Falha ao processar a requisição.",
                        "instance": "/api/calculadora/dados-abertos/aliquota-uniao"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaUniao(
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);


    @Operation(
        summary = "Alíquota Padrão ou de Referência para IBS Estadual",
        description = "Obtém a alíquota padrão ou de referência para IBS Estadual"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Consulta realizada com sucesso",
            headers = @Header(
                name = "x-warning-dados-simulados",
                description = "Indica que os dados são simulados. Valores possíveis: " +
                             "1 (alíquotas da CBS ainda não definidas em lei).",
                schema = @Schema(type = "integer", example = "1")
            ),
            content = @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = AliquotaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Alíquota UF Example",
                    value = """
                    {
                      "aliquotaReferencia": 0.1
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                      "type": "about:blank",
                      "title": "Bad Request",
                      "status": 400,
                      "detail": "Required parameter 'data' is not present.",
                      "instance": "/api/calculadora/dados-abertos/aliquota-uf"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/aliquota-uf"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaUf(
        @Parameter(description = "Código da UF", example = "43", required = true) Long codigoUf,
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);


    @Operation(
        summary = "Alíquota Padrão ou de Referência para IBS Municipal",
        description = "Obtém a alíquota padrão ou de referência para IBS Municipal"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Consulta realizada com sucesso",
            headers = @Header(
                name = "x-warning-dados-simulados",
                description = "Indica que os dados são simulados. Valores possíveis: " +
                             "1 (alíquotas da CBS e do IBS ainda não definidas em lei).",
                schema = @Schema(type = "integer", example = "1")
            ),
            content = @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = AliquotaDadosAbertosOutput.class),
                examples = @ExampleObject(
                    name = "Alíquota Município Example",
                    value = """
                    {
                      "aliquotaReferencia": 0.25
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Requisição com problema",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                    {
                      "type": "about:blank",
                      "title": "Bad Request",
                      "status": 400,
                      "detail": "Required parameter 'data' is not present.",
                      "instance": "/api/calculadora/dados-abertos/aliquota-municipio"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno na API",
            content = @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/aliquota-municipio"
                    }
                    """
                )
            )
        )
    })
    ResponseEntity<AliquotaDadosAbertosOutput> consultarAliquotaMunicipio(
        @Parameter(description = "Código do Município (Tabela IBGE)", example = "4314902", required = true) Long codigoMunicipio,
        @Parameter(description = "Data no padrão ISO 8601 (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Classificação Tributária CBS/IBS por DFe e cClassTrib", description = "Obtém a classificação tributária CBS/IBS com base na sigla do DFe e código da classificação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ValidadeDfeClassificacaoTributariaDadosAbertosOutput.class))),
        @ApiResponse(responseCode = "400", description = "Requisição com problema",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Bad Request Example",
                    value = """
                        {
                            "type": "about:blank",
                            "title": "Bad Request",
                            "status": 400,
                            "detail": "Required parameter 'data' is not present.",
                            "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs/NFE/000001"
                        }
                        """
                ))),
        @ApiResponse(responseCode = "404", description = "Classificação tributária não encontrada",
            content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Not Found Example",
                    value = """
                        {
                            "type": "https://url-ambiente/errors/classificacao-tributaria-nao-encontrada",
                            "title": "Classificação Tributária Não Encontrada",
                            "status": 404,
                            "detail": "Classificação tributária não encontrada para DFe 'NFSE' e código '000001' na data '2026-01-01'",
                            "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs/NFSE/000001",
                            "timestamp": 1760724111.1784844
                        }
                        """
                ))),
            @ApiResponse(responseCode = "422", description = "Erro de validação nos parâmetros",
                content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(
                        name = "Validation Error Example",
                        value = """
                            {
                                "type": "http://url-ambiente/errors/sigla-dfe-nao-reconhecida",
                                "title": "Sigla DFe não reconhecida",
                                "status": 422,
                                "detail": "Sigla DFe inválida: NFEJ",
                                "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs/NFEe/620002",
                                "timestamp": 1760729074.1335235
                            }
                            """
                    ))),
                @ApiResponse(responseCode = "500", description = "Erro interno na API",
                    content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                        schema = @Schema(implementation = ProblemDetail.class),
                        examples = @ExampleObject(
                            name = "Internal Server Error Example",
                            value = """
                                {
                                  "type": "http://url-ambiente/errors/erro-de-sistema-nao-previsto",
                                  "title": "Erro de sistema não previsto",
                                  "status": 500,
                                  "detail": "Falha ao acessar o banco de dados",
                                  "instance": "/api/calculadora/dados-abertos/classificacoes-tributarias/cbs-ibs/NFE/620001",
                                  "timestamp": 1760730286.2395124
                                }
                                """
                        )
                    )
                )
    })
    ResponseEntity<ValidadeDfeClassificacaoTributariaDadosAbertosOutput> consultarValidadeDfeClassificacaoTributaria(
        @Parameter(description = "Sigla do tipo de Documento Fiscal Eletrônico", example = "NFSE", required = true) String siglaDfe,
        @Parameter(description = "Código da classificação tributária", example = "000001", required = true) String cClassTrib,
        @Parameter(description = "Data do fato gerador (yyyy-MM-dd)", example = "2026-01-01", required = true) LocalDate data);

    @Operation(summary = "Versão do Aplicativo e do Banco de Dados", description = "Obtém a versão do aplicativo e do banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = {
            @Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = VersaoOutput.class),
                examples = @ExampleObject(
                    name = "Versão Example",
                    value = """
                    {
                        "versaoApp": "0.0.0-SNAPSHOT",
                        "versaoDb": "v0011",
                        "descricaoVersaoDb": "Nova carga de classificação tributária e novos campos em CLASSIFICACAO_TRIBUTARIA e SITUACAO_TRIBUTARIA.",
                        "dataVersaoDb": "2025-10-16",
                        "ambiente": "online"
                    }
                    """
                )
            )
        }),
        @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
            @Content(
                mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class),
                examples = @ExampleObject(
                    name = "Internal Server Error Example",
                    value = """
                    {
                      "type": "http://url-ambiente/errors/erro-interno",
                      "title": "Erro interno na API",
                      "status": 500,
                      "detail": "Falha ao processar a requisição.",
                      "instance": "/api/calculadora/dados-abertos/versao"
                    }
                    """
                )
            )
        })
    })
    ResponseEntity<VersaoOutput> consultarVersao();
    
}

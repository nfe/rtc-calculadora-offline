package br.gov.serpro.rtc.api.openapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.xml.TipoDocumentoDTO;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoDocumento;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoXml;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Calculadora - VERSÃO BETA", description = "Calculadora de Tributos")
public interface XMLControllerOpenApi {

    @Operation(summary = "Geração de XML", description = "Converte os dados para o formato XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conversão realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_XML_VALUE, schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    public ResponseEntity<String> generate(
            @RequestBody(description = "Dados do CBS/IBS/IS", required = true, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ROCDomain.class))) ROCDomain roc,
            @Parameter(description = "Tipo do documento", required = true) TipoDocumento tipo);

    @Operation(summary = "Validação de XML", description = "Valida os dados no formato XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validação realizada com sucesso", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Boolean.class)) }),
            @ApiResponse(responseCode = "400", description = "Estrutura e/ou dados informados em formato não reconhecido", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Erro na URL da requisição", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno na API", content = {
                    @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)) }) })
    ResponseEntity<Boolean> validate(
            @RequestBody(description = "XML a ser validado", required = true, content = @Content(mediaType = APPLICATION_XML_VALUE, schema = @Schema(type = "string"))) String xml,
            @Parameter(description = "Tipo do XML", required = true) @RequestParam TipoDocumento tipo,
            @Parameter(description = "Subtipo do XML", required = true) @RequestParam TipoXml subtipo);
    
    @Operation(summary = "Tipos de DFe", description = "Tipos de DFe para geração de XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", 
                    description = "Consulta realizada com sucesso", 
                    content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, 
                            schema = @Schema(implementation = TipoDocumentoDTO.class), 
                            examples = @ExampleObject(name = "Tipos de DFe Example", 
                            value = """
                                [
                                  {
                                    "nome": "NFe",
                                    "mnemonico": "nfe",
                                    "versaoNotaTecnica": "v1.30"
                                  },
                                  {
                                    "nome": "NFCe",
                                    "mnemonico": "nfce",
                                    "versaoNotaTecnica": "v1.30"
                                  },
                                  {
                                    "nome": "CTe",
                                    "mnemonico": "cte",
                                    "versaoNotaTecnica": "v1.10"
                                  },
                                  {
                                    "nome": "CTe Simplificado",
                                    "mnemonico": "cte-simplificado",
                                    "versaoNotaTecnica": "v1.10"
                                  },
                                  {
                                    "nome": "BPe",
                                    "mnemonico": "bpe",
                                    "versaoNotaTecnica": "v1.10"
                                  },
                                  {
                                    "nome": "NF3E",
                                    "mnemonico": "nf3e",
                                    "versaoNotaTecnica": "v1.10"
                                  }
                                ]
                            """
                            )
                    ) 
            }),
            @ApiResponse(responseCode = "500", 
                description = "Erro interno na API", 
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
                            "instance": "/api/calculadora/xml/generate"
                        }
                    """)
                )
            ) 
    })
    List<TipoDocumentoDTO> getDocumentosGeracao();
    
    @Operation(summary = "Tipos de DFe", description = "Tipos de DFe para validação de XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", 
                    description = "Consulta realizada com sucesso", 
                    content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, 
                            schema = @Schema(implementation = TipoDocumentoDTO.class), 
                            examples = @ExampleObject(name = "Tipos de DFe Example", 
                            value = """
                                [
                                  {
                                    "nome": "NFe",
                                    "mnemonico": "nfe",
                                    "versaoNotaTecnica": "v1.30"
                                  },
                                  {
                                    "nome": "NFCe",
                                    "mnemonico": "nfce",
                                    "versaoNotaTecnica": "v1.30"
                                  },
                                  {
                                    "nome": "NFSe",
                                    "mnemonico": "nfse",
                                    "versaoNotaTecnica": "ND"
                                  },
                                  {
                                    "nome": "CTe",
                                    "mnemonico": "cte",
                                    "versaoNotaTecnica": "v1.10"
                                  },
                                  {
                                    "nome": "CTe Simplificado",
                                    "mnemonico": "cte-simplificado",
                                    "versaoNotaTecnica": "v1.10"
                                  },
                                  {
                                    "nome": "BPe",
                                    "mnemonico": "bpe",
                                    "versaoNotaTecnica": "v1.10"
                                  },
                                  {
                                    "nome": "NF3E",
                                    "mnemonico": "nf3e",
                                    "versaoNotaTecnica": "v1.10"
                                  }
                                ]
                            """
                            )
                    ) 
            }),
            @ApiResponse(responseCode = "500", 
                description = "Erro interno na API", 
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
                            "instance": "/api/calculadora/xml/validate"
                        }
                    """)
                )
            ) 
    })
    List<TipoDocumentoDTO> getDocumentosValidacao();
    
}

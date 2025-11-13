package br.gov.serpro.rtc.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.xml.TipoDocumentoDTO;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoDocumento;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoXml;
import br.gov.serpro.rtc.api.openapi.controller.XMLControllerOpenApi;
import br.gov.serpro.rtc.domain.service.VersaoAplicacaoService;
import br.gov.serpro.rtc.domain.service.xml.XmlService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("calculadora/xml")
public class XMLController implements XMLControllerOpenApi {
    
    private final XmlService xmlService;
    private final VersaoAplicacaoService versaoAplicacaoService;
    
    @Override
    @PostMapping(
        value = "generate",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_XML_VALUE
    )
    public ResponseEntity<String> generate(@RequestBody ROCDomain roc, @RequestParam TipoDocumento tipo) {
        try {
            log.debug("Gerando xml...");
            final var xml = xmlService.gerarXml(roc, tipo);
            return ResponseEntity.ok().headers(versaoAplicacaoService.getHeaders()).body(xml);
        } catch (JAXBException e) {
            log.error("Erro ao converter ROC para XML", e);
            throw new RuntimeException("Erro ao converter ROC para XML", e);
        }
    }

    @Override
    @GetMapping(value = "generate", produces = APPLICATION_JSON_VALUE)
    public List<TipoDocumentoDTO> getDocumentosGeracao() {
        return TipoDocumento.getDocumentosGeracao();
    }

    @Override
    @PostMapping(
        value = "validate",
        consumes = APPLICATION_XML_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> validate(@RequestBody String xml, @RequestParam TipoDocumento tipo,
            @RequestParam TipoXml subtipo) {
        boolean valido = xmlService.validarXml(xml, tipo, subtipo);
        return ResponseEntity.ok(valido);
    }
    
    @Override
    @GetMapping(value = "validate", produces = APPLICATION_JSON_VALUE)
    public List<TipoDocumentoDTO> getDocumentosValidacao() {
        return TipoDocumento.getDocumentosValidacao();
    }
}
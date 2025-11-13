package br.gov.serpro.rtc.domain.service.xml;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoDocumento;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoXml;
import br.gov.serpro.rtc.core.util.XmlUtil;
import br.gov.serpro.rtc.domain.service.exception.ErroXmlException;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class XmlService {

    private final XmlUtil xmlUtil;
    private final NfeXmlService nfeXmlService;
    private final NfceXmlService nfceXmlService;
    private final CteXmlService cteXmlService;
    private final CteSimplificadoXmlService cteSimplificadoXmlService;
    private final BpeXmlService bpeXmlService;
    private final BpeTMXmlService bpeTmXmlService;
    private final Nf3eXmlService nf3eXmlService;

    public boolean validarXml(String xml, TipoDocumento tipo, TipoXml subtipo) {
        try {
            final Schema schema = xmlUtil.getSchema(tipo, subtipo);

            final Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));
            return true; // Validation successful
        } catch (SAXParseException e) {
            log.error("Erro ao validar XML", e);
            String msg = String.format("Erro na linha %d, coluna %d: %s", e.getLineNumber(), e.getColumnNumber(),
                    e.getMessage());
            throw new ErroXmlException(msg);
        } catch (SAXException e) {
            log.error("Erro ao validar XML", e);
            throw new ErroXmlException(e.getMessage());
        } catch (IOException e) {
            log.error("Erro ao validar XML", e);
            throw new ErroXmlException("Erro de IO na validação de XML: " + e.getMessage());
        }
    }

    public String gerarXml(ROCDomain roc, TipoDocumento tipo) throws JAXBException {
        switch (tipo) {
        case NFE:
            return nfeXmlService.toXml(roc);
        case NFCE:
            return nfceXmlService.toXml(roc);
        case CTE:
            return cteXmlService.toXml(roc);
        case CTE_SIMPLIFICADO:
            return cteSimplificadoXmlService.toXml(roc);
        case BPE:
            return bpeXmlService.toXml(roc);
        case BPE_TM:
            return bpeTmXmlService.toXml(roc);
        case NF3E:
            return nf3eXmlService.toXml(roc);
        // case NFSE:
        // return nfseXmlService.toXml(roc);
        default:
            throw new IllegalArgumentException("Tipo de documento não suportado: " + tipo);
        }
    }

}
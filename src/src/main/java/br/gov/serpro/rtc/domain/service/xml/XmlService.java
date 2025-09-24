package br.gov.serpro.rtc.domain.service.xml;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import br.gov.serpro.rtc.domain.service.exception.ErroXmlException;

import java.io.StringReader;
import java.io.IOException;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class XmlService {

    public boolean validarXml(String xml, String tipo, String subtipo) {
        try {
            // obter o arquivo XSD como URL do classpath
            ClassLoader classLoader = getClass().getClassLoader();
            URL xsdUrl = classLoader.getResource("xml" + "/" + tipo + "/" + subtipo + ".xsd");
            if (xsdUrl == null) {
                throw new IOException("XSD file not found in classpath");
            }

            // usar o caminho do arquivo para que os includes dos arquivos XSD sejam resolvidos corretamente
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdUrl.openStream(), xsdUrl.toExternalForm()));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));
            return true; // Validation successful
        } catch (SAXParseException e) {
            String msg = String.format("Erro na linha %d, coluna %d: %s", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
            e.printStackTrace();
            throw new ErroXmlException(msg);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new ErroXmlException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ErroXmlException("Erro de IO na validação de XML: " + e.getMessage());
        }
    }
}
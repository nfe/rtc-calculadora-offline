package br.gov.serpro.rtc.core.util;

import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import br.gov.serpro.rtc.api.model.xml.enumeration.TipoDocumento;
import br.gov.serpro.rtc.api.model.xml.enumeration.TipoXml;

@Component
public class XmlUtil {

    @Cacheable(cacheNames = "XmlUtil.getSchema")
    public Schema getSchema(TipoDocumento tipo, TipoXml subtipo) throws IOException, SAXException {
        // obter o arquivo XSD como URL do classpath
        final ClassLoader classLoader = getClass().getClassLoader();
        final var path = String.format("xml/%s/%s", tipo.getMnemonico(), subtipo.getMnemonico());
        final URL xsdUrl = classLoader.getResource(path);
        if (xsdUrl == null) {
            throw new IOException("XSD file not found in classpath");
        }

        // usar o caminho do arquivo para que os includes dos arquivos XSD sejam
        // resolvidos corretamente
        final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // Permitir includes/imports de XSDs do classpath, jar e file
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "jar,file,nested");

        // Explicitamente desabilita processamento de entidades externas
        try {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (Exception e) {
            // ignora se não suportado
        }
        return factory.newSchema(new StreamSource(xsdUrl.openStream(), xsdUrl.toExternalForm()));
    }

}

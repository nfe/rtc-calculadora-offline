package br.gov.serpro.rtc.domain.service.xml;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.lang.Boolean.TRUE;

import java.io.StringWriter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.xml.bpe.normal.InfBPe;
import br.gov.serpro.rtc.api.model.xml.bpe.normal.TTribBPe;
import br.gov.serpro.rtc.api.model.xml.bpe.normal.InfBPe.Imp;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * Serviço para serialização de BPe em XML.
 * 
 * IMPORTANTE: Cria um novo Marshaller para cada operação para garantir thread-safety.
 */
@Service
public class BpeXmlService {

    private final JAXBContext jaxbContext;
    private final ModelMapper mapper = new ModelMapper();
    
    public BpeXmlService(@Qualifier("jaxbInfBPeContext") JAXBContext jaxbContext) {
        super();
        this.jaxbContext = jaxbContext;
    }

    public String toXml(ROCDomain roc) throws JAXBException {
        final StringWriter writer = new StringWriter();
        // Criar um novo Marshaller para cada operação (thread-safety)
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);
        marshaller.marshal(convert(roc), writer);
        return writer.toString();
    }

    private InfBPe convert(ROCDomain roc) {
        if (roc == null) {
            throw new IllegalArgumentException("ROC não pode ser nulo");
        }
        if (roc.getObjetos() == null || roc.getObjetos().isEmpty() || roc.getObjetos().size() > 1) {
            throw new IllegalArgumentException("ROC deve conter um item");
        }

        final var imp = new Imp();
        final var ibsCbs = roc.getObjetos().getFirst().getTribCalc().getIBSCBS();
        imp.setIBSCBS(this.mapper.map(ibsCbs, TTribBPe.class));
        
        final var bpe = new InfBPe();
        bpe.setImp(imp);
        return bpe;
    }

}

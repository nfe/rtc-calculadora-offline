package br.gov.serpro.rtc.domain.service.xml;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.lang.Boolean.TRUE;

import java.io.StringWriter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.roc.ObjetoDomain;
import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.xml.nfe.InfNFe;
import br.gov.serpro.rtc.api.model.xml.nfe.InfNFe.Det;
import br.gov.serpro.rtc.api.model.xml.nfe.InfNFe.Det.Imposto;
import br.gov.serpro.rtc.api.model.xml.nfe.InfNFe.Total;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * Serviço para serialização de NFe em XML.
 * 
 * IMPORTANTE: Cria um novo Marshaller para cada operação para garantir thread-safety,
 * já que Marshaller não é thread-safe. O JAXBContext é thread-safe e pode ser reutilizado.
 */
@Service
public class NfeXmlService {

    private final JAXBContext jaxbContext;
    private final ModelMapper mapper;
    
    public NfeXmlService(@Qualifier("jaxbInfNfeContext") JAXBContext jaxbContext) {
        super();
        this.jaxbContext = jaxbContext;
        this.mapper = new ModelMapper();
    }
    
    public String toXml(ROCDomain roc) throws JAXBException {
        StringWriter writer = new StringWriter();
        // Criar um novo Marshaller para cada operação (thread-safety)
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);
        marshaller.marshal(convert(roc), writer);
        return writer.toString();
    }

    private InfNFe convert(ROCDomain roc) {
        if (roc == null) {
            throw new IllegalArgumentException("ROC não pode ser nulo");
        }
        if (roc.getObjetos() == null || roc.getObjetos().isEmpty()) {
            throw new IllegalArgumentException("ROC deve conter ao menos um objeto");
        }
        if (roc.getTotal() == null || roc.getTotal().getTribCalc() == null) {
            throw new IllegalArgumentException("Total do ROC não informado");
        }
        
        var infNfe = new InfNFe();
        infNfe.setDet(roc.getObjetos().stream().map(this::objetoToDet).toList());
        infNfe.setTotal(this.mapper.map(roc.getTotal().getTribCalc(), Total.class));
        return infNfe;
    }

    private Det objetoToDet(ObjetoDomain r) {
        var d = new Det();
        d.setNItem(r.getNObj().toString());
        d.setImposto(this.mapper.map(r.getTribCalc(), Imposto.class));
        return d;
    }

}

package br.gov.serpro.rtc.domain.service.xml;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.lang.Boolean.TRUE;

import java.io.StringWriter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.roc.ObjetoDomain;
import br.gov.serpro.rtc.api.model.roc.ROCDomain;
import br.gov.serpro.rtc.api.model.xml.nf3e.InfNF3E;
import br.gov.serpro.rtc.api.model.xml.nf3e.InfNF3E.NFdet;
import br.gov.serpro.rtc.api.model.xml.nf3e.InfNF3E.NFdet.Det;
import br.gov.serpro.rtc.api.model.xml.nf3e.InfNF3E.NFdet.Det.DetItem.Imposto;
import br.gov.serpro.rtc.api.model.xml.nf3e.InfNF3E.Total;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

/**
 * Serviço para serialização de NF3E em XML.
 * 
 * IMPORTANTE: Cria um novo Marshaller para cada operação para garantir thread-safety.
 */
@Service
public class Nf3eXmlService {

    private final JAXBContext jaxbContext;
    private final ModelMapper mapper = new ModelMapper();
    
    public Nf3eXmlService(@Qualifier("jaxbInfNF3EContext") JAXBContext jaxbContext) {
        super();
        this.jaxbContext = jaxbContext;
    }

    public String toXml(ROCDomain roc) throws JAXBException {
        final var writer = new StringWriter();
        // Criar um novo Marshaller para cada operação (thread-safety)
        final var marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);
        marshaller.marshal(convert(roc), writer);
        return writer.toString();
    }

    private InfNF3E convert(ROCDomain roc) {
        if (roc == null) {
            throw new IllegalArgumentException("ROC não pode ser nulo");
        }
        if (roc.getObjetos() == null || roc.getObjetos().isEmpty()) {
            throw new IllegalArgumentException("ROC deve conter ao menos um item");
        }
        if (roc.getTotal() == null || roc.getTotal().getTribCalc() == null) {
            throw new IllegalArgumentException("Total do ROC não informado");
        }
        
        /**
         * Atualmente, todos os itens do ROCDomain irao compor o mesmo NFdet, conforme abaixo:
         * 
         * infNf3e
         *  NFdet [1..13]   <-- atualmente, conseguimos gerar somente 1 NFdet para a NF3E...
         *   det [1..990]   <-- ... e alocamos todos os ObjetosDomain aqui
         *    nItem
         *    detItem
         *     imposto      <-- aqui vao os tributos do ObjetoDomain
         *  total
         */
        var infNf3e = new InfNF3E();
        var nfDet = new NFdet();
        var dets = roc.getObjetos().stream().map(this::objetoToDet).toList();
        nfDet.setDet(dets);
        
        infNf3e.setNFdet(List.of(nfDet));
        infNf3e.setTotal(this.mapper.map(roc.getTotal().getTribCalc(), Total.class));
        return infNf3e;
    }

    private Det objetoToDet(ObjetoDomain r) {
        var det = new NFdet.Det();
        det.setNItem(r.getNObj().toString());
        
        var detItem = new NFdet.Det.DetItem();
        detItem.setImposto(this.mapper.map(r.getTribCalc(), Imposto.class));
        det.setDetItem(detItem);
        return det;
    }

}

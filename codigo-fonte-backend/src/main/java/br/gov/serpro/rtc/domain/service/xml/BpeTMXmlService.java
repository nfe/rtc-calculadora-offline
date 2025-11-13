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
import br.gov.serpro.rtc.api.model.xml.bpe.tm.InfBPe;
import br.gov.serpro.rtc.api.model.xml.bpe.tm.InfBPe.DetBPeTM;
import br.gov.serpro.rtc.api.model.xml.bpe.tm.InfBPe.DetBPeTM.Det;
import br.gov.serpro.rtc.api.model.xml.bpe.tm.InfBPe.DetBPeTM.Det.Imp;
import br.gov.serpro.rtc.api.model.xml.bpe.tm.InfBPe.Total;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * Serviço para serialização de BPe TM em XML.
 * 
 * IMPORTANTE: Cria um novo Marshaller para cada operação para garantir thread-safety.
 */
@Service
public class BpeTMXmlService {

    private final JAXBContext jaxbContext;
    private final ModelMapper mapper = new ModelMapper();
    
    public BpeTMXmlService(@Qualifier("jaxbInfBPeTMContext") JAXBContext jaxbContext) {
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
        if (roc.getTotal() == null || roc.getTotal().getTribCalc() == null) {
            throw new IllegalArgumentException("Total do ROC não informado");
        }
        
        /**
         * Atualmente, o BPe TM tera somente um item:
         * 
         * infBPe
         *  detBPeTM [1..99]    <-- atualmente, conseguimos gerar somente 1 DetBPeTM para a BPe TM...
         *   det [1..990]       <-- ... e alocamos todos os ObjetosDomain aqui
         *    nViagem
         *    imp               <-- aqui vao os tributos do ObjetoDomain
         *  total
         */
        var infBPe = new InfBPe();
        var detBPeTM = new DetBPeTM();
        infBPe.setDetBPeTM(List.of(detBPeTM));
        
        var det = objetoToDet(roc.getObjetos().getFirst());
        detBPeTM.setDet(List.of(det));
        
        infBPe.setTotal(this.mapper.map(roc.getTotal().getTribCalc(), Total.class));
        return infBPe;
    }

    private Det objetoToDet(ObjetoDomain r) {
        var det = new Det();
        det.setNViagem(r.getNObj().toString());
        det.setImp(this.mapper.map(r.getTribCalc(), Imp.class));
        return det;
    }

}

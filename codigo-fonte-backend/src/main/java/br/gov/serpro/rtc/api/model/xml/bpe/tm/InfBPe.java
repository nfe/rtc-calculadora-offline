//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.bpe.tm;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "detBPeTM", "total" })
@XmlRootElement(name = "infBPe")
public class InfBPe {

    @XmlElement(required = true)
    private List<InfBPe.DetBPeTM> detBPeTM;

    @XmlElement(required = true)
    private InfBPe.Total total;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "det" })
    public static class DetBPeTM {

        @XmlElement(required = true)
        private List<InfBPe.DetBPeTM.Det> det;

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "imp" })
        public static class Det {

            @XmlElement(required = true)
            private InfBPe.DetBPeTM.Det.Imp imp;

            @XmlAttribute(required = true)
            private String nViagem;

            @Getter
            @Setter
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "IBSCBS" })
            public static class Imp {

                private TTribBPe IBSCBS;
            }
        }
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "IBSCBSTot" })
    public static class Total {

        private TIBSCBSTot IBSCBSTot;
    }

}

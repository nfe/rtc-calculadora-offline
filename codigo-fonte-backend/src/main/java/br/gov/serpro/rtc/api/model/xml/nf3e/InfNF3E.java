//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nf3e;

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
@XmlType(name = "", propOrder = { "NFdet", "total" })
@XmlRootElement(name = "infNF3e")
public class InfNF3E {

    @XmlElement(required = true)
    private List<InfNF3E.NFdet> NFdet;

    @XmlElement(required = true)
    private InfNF3E.Total total;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "det" })
    public static class NFdet {

        @XmlElement(required = true)
        private List<InfNF3E.NFdet.Det> det;

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "detItem" })
        public static class Det {

            private InfNF3E.NFdet.Det.DetItem detItem;

            @XmlAttribute(required = true)
            private String nItem;

            @Getter
            @Setter
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "imposto" })
            public static class DetItem {

                @XmlElement(required = true)
                private InfNF3E.NFdet.Det.DetItem.Imposto imposto;

                @Getter
                @Setter
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "IBSCBS" })
                public static class Imposto extends TImp {

                    private TTribNF3E IBSCBS;
                }
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

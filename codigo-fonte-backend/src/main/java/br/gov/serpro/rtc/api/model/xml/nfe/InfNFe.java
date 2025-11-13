//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nfe;

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
@XmlType(name = "", propOrder = { "det", "total" })
@XmlRootElement(name = "infNFe")
public class InfNFe {

    private List<InfNFe.Det> det;

    @XmlElement(required = true)
    private InfNFe.Total total;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "imposto" })
    public static class Det {

        @XmlElement(required = true)
        private InfNFe.Det.Imposto imposto;

        @XmlAttribute(name = "nItem", required = true)
        private String nItem;

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "IS", "IBSCBS" })
        public static class Imposto {

            private TIS IS;

            @XmlElement(required = true)
            private TTribNFe IBSCBS;
        }

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "ISTot", "IBSCBSTot" })
    public static class Total {

        private TISTot ISTot;
        private TIBSCBSMonoTot IBSCBSTot;
    }

}

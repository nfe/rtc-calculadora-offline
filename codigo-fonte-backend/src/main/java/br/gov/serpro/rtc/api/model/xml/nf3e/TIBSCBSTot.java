//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nf3e;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TIBSCBSTot", propOrder = { "vBCIBSCBS", "gIBS", "gCBS", "gEstornoCred" })
public class TIBSCBSTot {

    @XmlElement(required = true)
    private String vBCIBSCBS;

    @XmlElement(required = true)
    private TIBSCBSTot.GIBS gIBS;

    @XmlElement(required = true)
    private TIBSCBSTot.GCBS gCBS;

    private TIBSCBSTot.GEstornoCred gEstornoCred;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "vDif", "vDevTrib", "vCBS" })
    public static class GCBS {

        @XmlElement(required = true)
        private String vDif;

        @XmlElement(required = true)
        private String vDevTrib;

        @XmlElement(required = true)
        private String vCBS;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "vIBSEstCred", "vCBSEstCred" })
    public static class GEstornoCred {

        @XmlElement(required = true)
        private String vIBSEstCred;

        @XmlElement(required = true)
        private String vCBSEstCred;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "gIBSUF", "gIBSMun", "vIBS" })
    public static class GIBS {

        @XmlElement(required = true)
        private TIBSCBSTot.GIBS.GIBSUF gIBSUF;

        @XmlElement(required = true)
        private TIBSCBSTot.GIBS.GIBSMun gIBSMun;

        @XmlElement(required = true)
        private String vIBS;

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "vDif", "vDevTrib", "vIBSMun" })
        public static class GIBSMun {

            @XmlElement(required = true)
            private String vDif;

            @XmlElement(required = true)
            private String vDevTrib;

            @XmlElement(required = true)
            private String vIBSMun;
        }

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "vDif", "vDevTrib", "vIBSUF" })
        public static class GIBSUF {

            @XmlElement(required = true)
            private String vDif;

            @XmlElement(required = true)
            private String vDevTrib;

            @XmlElement(required = true)
            private String vIBSUF;
        }
    }

}

//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nfe;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TIBSCBSMonoTot", propOrder = { "vBCIBSCBS", "gIBS", "gCBS", "gMono", "gEstornoCred" })
public class TIBSCBSMonoTot {

    @XmlElement(required = true)
    private BigDecimal vBCIBSCBS;

    private TIBSCBSMonoTot.GIBS gIBS;
    private TIBSCBSMonoTot.GCBS gCBS;
    private TIBSCBSMonoTot.GMono gMono;
    private TIBSCBSMonoTot.GEstornoCred gEstornoCred;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "vDif", "vDevTrib", "vCBS", "vCredPres", "vCredPresCondSus" })
    public static class GCBS {

        @XmlElement(required = true)
        private BigDecimal vDif;

        @XmlElement(required = true)
        private BigDecimal vDevTrib;

        @XmlElement(required = true)
        private BigDecimal vCBS;

        @XmlElement(required = true)
        private BigDecimal vCredPres;

        @XmlElement(required = true)
        private BigDecimal vCredPresCondSus;

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "vIBSEstCred", "vCBSEstCred" })
    public static class GEstornoCred {

        @XmlElement(required = true)
        private BigDecimal vIBSEstCred;

        @XmlElement(required = true)
        private BigDecimal vCBSEstCred;

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "gIBSUF", "gIBSMun", "vIBS", "vCredPres", "vCredPresCondSus" })
    public static class GIBS {

        @XmlElement(required = true)
        private TIBSCBSMonoTot.GIBS.GIBSUF gIBSUF;

        @XmlElement(required = true)
        private TIBSCBSMonoTot.GIBS.GIBSMun gIBSMun;

        @XmlElement(required = true)
        private BigDecimal vIBS;

        @XmlElement(required = true)
        private BigDecimal vCredPres;

        @XmlElement(required = true)
        private BigDecimal vCredPresCondSus;

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "vDif", "vDevTrib", "vIBSMun" })
        public static class GIBSMun {

            @XmlElement(required = true)
            private BigDecimal vDif;

            @XmlElement(required = true)
            private BigDecimal vDevTrib;

            @XmlElement(required = true)
            private BigDecimal vIBSMun;

        }

        @Getter
        @Setter
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "vDif", "vDevTrib", "vIBSUF" })
        public static class GIBSUF {

            @XmlElement(required = true)
            private BigDecimal vDif;

            @XmlElement(required = true)
            private BigDecimal vDevTrib;

            @XmlElement(required = true)
            private BigDecimal vIBSUF;

        }

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "vIBSMono", "vCBSMono", "vIBSMonoReten", "vCBSMonoReten", "vIBSMonoRet",
            "vCBSMonoRet" })
    public static class GMono {

        @XmlElement(required = true)
        private BigDecimal vIBSMono;

        @XmlElement(required = true)
        private BigDecimal vCBSMono;

        @XmlElement(required = true)
        private BigDecimal vIBSMonoReten;

        @XmlElement(required = true)
        private BigDecimal vCBSMonoReten;

        @XmlElement(required = true)
        private BigDecimal vIBSMonoRet;

        @XmlElement(required = true)
        private BigDecimal vCBSMonoRet;

    }

}

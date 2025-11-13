//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nfce;

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
@XmlType(name = "TMonofasia", propOrder = { "gMonoPadrao", "gMonoReten", "gMonoRet", "gMonoDif", "vTotIBSMonoItem",
        "vTotCBSMonoItem" })
public class TMonofasia {

    private TMonofasia.GMonoPadrao gMonoPadrao;
    private TMonofasia.GMonoReten gMonoReten;
    private TMonofasia.GMonoRet gMonoRet;
    private TMonofasia.GMonoDif gMonoDif;

    @XmlElement(required = true)
    private BigDecimal vTotIBSMonoItem;

    @XmlElement(required = true)
    private BigDecimal vTotCBSMonoItem;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "pDifIBS", "vIBSMonoDif", "pDifCBS", "vCBSMonoDif" })
    public static class GMonoDif {

        @XmlElement(required = true)
        private BigDecimal pDifIBS;

        @XmlElement(required = true)
        private BigDecimal vIBSMonoDif;

        @XmlElement(required = true)
        private BigDecimal pDifCBS;

        @XmlElement(required = true)
        private BigDecimal vCBSMonoDif;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "qBCMono", "adRemIBS", "adRemCBS", "vIBSMono", "vCBSMono" })
    public static class GMonoPadrao {

        @XmlElement(required = true)
        private BigDecimal qBCMono;

        @XmlElement(required = true)
        private BigDecimal adRemIBS;

        @XmlElement(required = true)
        private BigDecimal adRemCBS;

        @XmlElement(required = true)
        private BigDecimal vIBSMono;

        @XmlElement(required = true)
        private BigDecimal vCBSMono;

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "qBCMonoRet", "adRemIBSRet", "vIBSMonoRet", "adRemCBSRet", "vCBSMonoRet" })
    public static class GMonoRet {

        @XmlElement(required = true)
        private BigDecimal qBCMonoRet;

        @XmlElement(required = true)
        private BigDecimal adRemIBSRet;

        @XmlElement(required = true)
        private BigDecimal vIBSMonoRet;

        @XmlElement(required = true)
        private BigDecimal adRemCBSRet;

        @XmlElement(required = true)
        private BigDecimal vCBSMonoRet;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "qBCMonoReten", "adRemIBSReten", "vIBSMonoReten", "adRemCBSReten",
            "vCBSMonoReten" })
    public static class GMonoReten {

        @XmlElement(required = true)
        private BigDecimal qBCMonoReten;

        @XmlElement(required = true)
        private BigDecimal adRemIBSReten;

        @XmlElement(required = true)
        private BigDecimal vIBSMonoReten;

        @XmlElement(required = true)
        private BigDecimal adRemCBSReten;

        @XmlElement(required = true)
        private BigDecimal vCBSMonoReten;
    }

}

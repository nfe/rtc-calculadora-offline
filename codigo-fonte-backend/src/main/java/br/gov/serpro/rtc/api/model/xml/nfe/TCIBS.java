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
@XmlType(name = "TCIBS", propOrder = { "vBC", "gIBSUF", "gIBSMun", "vIBS", "gCBS", "gTribRegular", "gTribCompraGov" })
public class TCIBS {

    @XmlElement(required = true)
    private BigDecimal vBC;

    @XmlElement(required = true)
    private TCIBS.GIBSUF gIBSUF;

    @XmlElement(required = true)
    private TCIBS.GIBSMun gIBSMun;

    @XmlElement(required = true)
    private BigDecimal vIBS;

    @XmlElement(required = true)
    private TCIBS.GCBS gCBS;

    private TTribRegular gTribRegular;
    private TTribCompraGov gTribCompraGov;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "pCBS", "gDif", "gDevTrib", "gRed", "vCBS" })
    public static class GCBS {

        @XmlElement(required = true)
        private BigDecimal pCBS;

        private TDif gDif;
        private TDevTrib gDevTrib;
        private TRed gRed;

        @XmlElement(required = true)
        private BigDecimal vCBS;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "pIBSMun", "gDif", "gDevTrib", "gRed", "vIBSMun" })
    public static class GIBSMun {

        @XmlElement(required = true)
        private BigDecimal pIBSMun;

        private TDif gDif;
        private TDevTrib gDevTrib;
        private TRed gRed;

        @XmlElement(required = true)
        private BigDecimal vIBSMun;

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "pIBSUF", "gDif", "gDevTrib", "gRed", "vIBSUF" })
    public static class GIBSUF {

        @XmlElement(required = true)
        private BigDecimal pIBSUF;

        private TDif gDif;
        private TDevTrib gDevTrib;
        private TRed gRed;

        @XmlElement(required = true)
        private BigDecimal vIBSUF;

    }

}

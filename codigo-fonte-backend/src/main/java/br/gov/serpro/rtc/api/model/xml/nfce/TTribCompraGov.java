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
@XmlType(name = "TTribCompraGov", propOrder = { "pAliqIBSUF", "vTribIBSUF", "pAliqIBSMun", "vTribIBSMun", "pAliqCBS",
        "vTribCBS" })
public class TTribCompraGov {

    @XmlElement(required = true)
    private BigDecimal pAliqIBSUF;

    @XmlElement(required = true)
    private BigDecimal vTribIBSUF;

    @XmlElement(required = true)
    private BigDecimal pAliqIBSMun;

    @XmlElement(required = true)
    private BigDecimal vTribIBSMun;

    @XmlElement(required = true)
    private BigDecimal pAliqCBS;

    @XmlElement(required = true)
    private BigDecimal vTribCBS;

}

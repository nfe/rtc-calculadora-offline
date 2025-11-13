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
@XmlType(name = "TTribRegular", propOrder = { "CSTReg", "cClassTribReg", "pAliqEfetRegIBSUF", "vTribRegIBSUF",
        "pAliqEfetRegIBSMun", "vTribRegIBSMun", "pAliqEfetRegCBS", "vTribRegCBS" })
public class TTribRegular {

    @XmlElement(required = true)
    private String CSTReg;

    @XmlElement(required = true)
    private String cClassTribReg;

    @XmlElement(required = true)
    private BigDecimal pAliqEfetRegIBSUF;

    @XmlElement(required = true)
    private BigDecimal vTribRegIBSUF;

    @XmlElement(required = true)
    private BigDecimal pAliqEfetRegIBSMun;

    @XmlElement(required = true)
    private BigDecimal vTribRegIBSMun;

    @XmlElement(required = true)
    private BigDecimal pAliqEfetRegCBS;

    @XmlElement(required = true)
    private BigDecimal vTribRegCBS;

}

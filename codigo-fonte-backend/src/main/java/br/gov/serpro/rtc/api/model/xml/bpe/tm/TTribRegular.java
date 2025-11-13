//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.bpe.tm;

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
    private String pAliqEfetRegIBSUF;

    @XmlElement(required = true)
    private String vTribRegIBSUF;

    @XmlElement(required = true)
    private String pAliqEfetRegIBSMun;

    @XmlElement(required = true)
    private String vTribRegIBSMun;

    @XmlElement(required = true)
    private String pAliqEfetRegCBS;

    @XmlElement(required = true)
    private String vTribRegCBS;

}

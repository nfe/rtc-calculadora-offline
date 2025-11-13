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
@XmlType(name = "TTribCompraGov", propOrder = { "pAliqIBSUF", "vTribIBSUF", "pAliqIBSMun", "vTribIBSMun", "pAliqCBS",
        "vTribCBS" })
public class TTribCompraGov {

    @XmlElement(required = true)
    private String pAliqIBSUF;

    @XmlElement(required = true)
    private String vTribIBSUF;

    @XmlElement(required = true)
    private String pAliqIBSMun;

    @XmlElement(required = true)
    private String vTribIBSMun;

    @XmlElement(required = true)
    private String pAliqCBS;

    @XmlElement(required = true)
    private String vTribCBS;

}

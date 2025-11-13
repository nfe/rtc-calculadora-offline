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
@XmlType(name = "TIS", propOrder = { "CSTIS", "cClassTribIS", "vBCIS", "pIS", "pISEspec", "uTrib", "qTrib", "vIS" })
public class TIS {

    @XmlElement(required = true)
    private String CSTIS;

    @XmlElement(required = true)
    private String cClassTribIS;

    private BigDecimal vBCIS;
    private BigDecimal pIS;
    private BigDecimal pISEspec;
    private String uTrib;
    private String qTrib;
    private BigDecimal vIS;

}
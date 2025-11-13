//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nfe;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPresIBSZFM", propOrder = { "competApur", "tpCredPresIBSZFM", "vCredPresIBSZFM" })
public class TCredPresIBSZFM {

    @XmlElement(required = true)
    @XmlSchemaType(name = "gYearMonth")
    private XMLGregorianCalendar competApur;

    @XmlElement(required = true)
    private String tpCredPresIBSZFM;

    @XmlElement(required = true)
    private BigDecimal vCredPresIBSZFM;

}

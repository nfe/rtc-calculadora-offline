//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//

package br.gov.serpro.rtc.api.model.xml.nfe;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribNFe", propOrder = { "CST", "cClassTrib", "indDoacao", "gIBSCBS", "gIBSCBSMono", "gTransfCred",
        "gAjusteCompet", "gEstornoCred", "gCredPresOper", "gCredPresIBSZFM" })
public class TTribNFe {

    @XmlElement(required = true)
    private String CST;

    @XmlElement(required = true)
    private String cClassTrib;

    private String indDoacao;
    private TCIBS gIBSCBS;
    private TMonofasia gIBSCBSMono;
    private TTransfCred gTransfCred;
    private TAjusteCompet gAjusteCompet;
    private TEstornoCred gEstornoCred;
    private TCredPresOper gCredPresOper;
    private TCredPresIBSZFM gCredPresIBSZFM;

}
package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public final class Objeto implements Comparable<Objeto> {
    
    @EqualsAndHashCode.Include
    @JacksonXmlProperty(localName = "nItem", isAttribute = true)
    @JsonProperty(value = "nObj", index = 0)
    private Integer nObj;
    
    @JacksonXmlProperty(localName = "imposto")
    @JsonProperty(value = "tribCalc", index = 1)
    private Tributos tribCalc;
    
    @JsonIgnore
    public boolean possuiImpostoSeletivo() {
        return tribCalc != null && tribCalc.possuiImpostoSeletivo();
    }
    
    @JsonIgnore
    public ImpostoSeletivo getImpostoSeletivo() {
        return tribCalc != null ? tribCalc.getIS() : null;
    }
    
    @JsonIgnore
    public BigDecimal getValorImpostoSeletivo() {
        return possuiImpostoSeletivo() ? tribCalc.getValorImpostoSeletivo() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiBaseCalculoIBSCBS() {
        return tribCalc != null && tribCalc.possuiBaseCalculoIBSCBS();
    }
    
    @JsonIgnore
    public BigDecimal getValorBaseCalculoIBSCBS() {
        return possuiBaseCalculoIBSCBS() ? tribCalc.getValorBaseCalculoIBSCBS() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiCbs() {
        return tribCalc != null && tribCalc.possuiCbs();
    }
    
    @JsonIgnore
    public CBS getGCBS() {
        return tribCalc != null ? tribCalc.getGCBS() : null;
    }    
    
    @JsonIgnore
    public boolean possuiCreditoPresumidoCbs() {
        return tribCalc != null && tribCalc.possuiCreditoPresumidoCbs();
    }
    
    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return tribCalc != null && tribCalc.possuiCredPresIbs();
    }
    
    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return tribCalc != null && tribCalc.possuiCredPresCondSusIbs();
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? tribCalc.getVCredPresIbs() : ZERO;
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? tribCalc.getVCredPresCondSusIbs() : ZERO;
    }
    
    @JsonIgnore
    public GrupoIBSCBS getGrupoIBSCBS() {
        return tribCalc != null ? tribCalc.getGrupoIBSCBS() : null;
    }
    
    @JsonIgnore
    public boolean possuiIBSUF() {
        return tribCalc != null && tribCalc.possuiIBSUF();
    }
    
    @JsonIgnore
    public IBSUF getGIBSUF() {
        return tribCalc != null ? tribCalc.getGIBSUF() : null;
    }
    
    @JsonIgnore
    public boolean possuiIBSMun() {
        return tribCalc != null && tribCalc.possuiIBSMun();
    }
    
    @JsonIgnore
    public IBSMun getGIBSMun() {
        return tribCalc != null ? tribCalc.getGIBSMun() : null;
    }
    
    @JsonIgnore
    public boolean possuiMonofasia() {
        return tribCalc != null && tribCalc.possuiMonofasia();
    }
    
    @JsonIgnore
    public Monofasia getGIBSCBSMono() {
        return possuiMonofasia() ? tribCalc.getGIBSCBSMono() : null;
    }
    
    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return tribCalc != null && tribCalc.possuiTributacaoRegular();
    }
    
    @JsonIgnore
    public TributacaoRegular getTributacaoRegular() {
        return tribCalc != null ? tribCalc.getTributacaoRegular() : null;
    }

    @Override
    public int compareTo(Objeto o) {
        return Comparator.nullsFirst(Integer::compareTo).compare(this.nObj, o.nObj);
    }
}

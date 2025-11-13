package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "nObj", "tribCalc" })
public final class ObjetoDomain implements SerializationVisibility, Comparable<ObjetoDomain> {
    
    @EqualsAndHashCode.Include
    private Integer nObj;
    
    private TributosDomain tribCalc;
    
    @JsonIgnore
    public boolean possuiImpostoSeletivo() {
        return tribCalc != null && tribCalc.possuiImpostoSeletivo();
    }
    
    @JsonIgnore
    public ImpostoSeletivoDomain getImpostoSeletivo() {
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
    public CBSDomain getGCBS() {
        return tribCalc != null ? tribCalc.getGCBS() : null;
    }
    
    @JsonIgnore
    public boolean possuiCredPresCbs() {
        return tribCalc != null && tribCalc.possuiCredPresCbs();
    }
    
    @JsonIgnore
    public IBSCBSCreditoPresumidoDomain getCredPresCbs() {
        return possuiCredPresCbs() ? tribCalc.getCredPresCbs() : null;
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
    public GrupoIBSCBSDomain getGrupoIBSCBS() {
        return tribCalc != null ? tribCalc.getGrupoIBSCBS() : null;
    }
    
    @JsonIgnore
    public boolean possuiIBSUF() {
        return tribCalc != null && tribCalc.possuiIBSUF();
    }
    
    @JsonIgnore
    public IBSUFDomain getGIBSUF() {
        return tribCalc != null ? tribCalc.getGIBSUF() : null;
    }
    
    @JsonIgnore
    public boolean possuiIBSMun() {
        return tribCalc != null && tribCalc.possuiIBSMun();
    }
    
    @JsonIgnore
    public IBSMunDomain getGIBSMun() {
        return tribCalc != null ? tribCalc.getGIBSMun() : null;
    }
    
    @JsonIgnore
    public boolean possuiMonofasia() {
        return tribCalc != null && tribCalc.possuiMonofasia();
    }
    
    @JsonIgnore
    public MonofasiaDomain getGIBSCBSMono() {
        return possuiMonofasia() ? tribCalc.getGIBSCBSMono() : null;
    }
    
    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return tribCalc != null && tribCalc.possuiTributacaoRegular();
    }
    
    @JsonIgnore
    public TributacaoRegularDomain getTributacaoRegular() {
        return tribCalc != null ? tribCalc.getTributacaoRegular() : null;
    }

    @Override
    public int compareTo(ObjetoDomain o) {
        return Comparator.nullsFirst(Integer::compareTo).compare(this.nObj, o.nObj);
    }
    
    @JsonIgnore
    public boolean possuiEstornoCredito() {
        return tribCalc != null && tribCalc.possuiEstornoCredito();
    }
    
    @JsonIgnore
    public EstornoCreditoDomain getGEstornoCred() {
        return tribCalc != null ? tribCalc.getGEstornoCred() : null;
    }
}

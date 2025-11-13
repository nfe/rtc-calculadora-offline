package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "IS", "IBSCBS" })
public class TributosDomain implements SerializationVisibility {

    @Schema(description = "Informações do Imposto Seletivo")
    private ImpostoSeletivoDomain IS;

    @Schema(description = "Informações do Imposto de Bens e Serviços - IBS e da Contribuição de Bens e Serviços - CBS")
    private IBSCBSDomain IBSCBS;

    @JsonIgnore
    public boolean possuiImpostoSeletivo() {
        return IS != null;
    }

    @JsonIgnore
    public BigDecimal getValorImpostoSeletivo() {
        return possuiImpostoSeletivo() ? IS.getValorImpostoSeletivo() : ZERO;
    }

    @JsonIgnore
    public boolean possuiBaseCalculoIBSCBS() {
        return IBSCBS != null && IBSCBS.possuiBaseCalculoIBSCBS();
    }

    @JsonIgnore
    public BigDecimal getValorBaseCalculoIBSCBS() {
        return possuiBaseCalculoIBSCBS() ? IBSCBS.getValorBaseCalculoIBSCBS() : ZERO;
    }

    @JsonIgnore
    public boolean possuiCbs() {
        return IBSCBS != null && IBSCBS.possuiCbs();
    }
    
    @JsonIgnore
    public CBSDomain getGCBS() {
        return IBSCBS != null ? IBSCBS.getGCBS() : null;
    }

    @JsonIgnore
    public boolean possuiCredPresCbs() {
        return IBSCBS != null && IBSCBS.possuiCredPresCbs();
    }
    
    @JsonIgnore
    public IBSCBSCreditoPresumidoDomain getCredPresCbs() {
        return possuiCredPresCbs() ? IBSCBS.getCredPresCbs() : null;
    }

    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return IBSCBS != null && IBSCBS.possuiCredPresIbs();
    }

    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? IBSCBS.getVCredPresIbs() : ZERO;
    }

    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return IBSCBS != null && IBSCBS.possuiCredPresCondSusIbs();
    }

    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? IBSCBS.getVCredPresCondSusIbs() : ZERO;
    }

    @JsonIgnore
    public GrupoIBSCBSDomain getGrupoIBSCBS() {
        return IBSCBS != null ? IBSCBS.getGrupoIBSCBS() : null;
    }

    @JsonIgnore
    public boolean possuiIBSUF() {
        return IBSCBS != null && IBSCBS.possuiIBSUF();
    }

    @JsonIgnore       
    public boolean possuiIBSMun() {
        return IBSCBS != null && IBSCBS.possuiIBSMun();
    }

    @JsonIgnore
    public IBSUFDomain getGIBSUF() {
        return IBSCBS != null ? IBSCBS.getGIBSUF() : null;
    }

    @JsonIgnore
    public IBSMunDomain getGIBSMun() {
        return IBSCBS != null ? IBSCBS.getGIBSMun() : null;
    }

    @JsonIgnore
    public boolean possuiMonofasia() {
        return IBSCBS != null && IBSCBS.possuiMonofasia();
    }

    @JsonIgnore
    public MonofasiaDomain getGIBSCBSMono() {
        return possuiMonofasia() ? IBSCBS.getGIBSCBSMono() : null;
    }
    
    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return IBSCBS != null && IBSCBS.possuiTributacaoRegular();
    }

    @JsonIgnore
    public TributacaoRegularDomain getTributacaoRegular() {
        return IBSCBS != null ? IBSCBS.getTributacaoRegular() : null;
    }

    @JsonIgnore
    public boolean possuiEstornoCredito() {
        return IBSCBS != null && IBSCBS.possuiEstornoCredito();
    }

    @JsonIgnore
    public EstornoCreditoDomain getGEstornoCred() {
        return IBSCBS != null ? IBSCBS.getGEstornoCred() : null;
    }

}
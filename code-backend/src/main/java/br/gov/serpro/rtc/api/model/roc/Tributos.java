package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class Tributos {

    @Schema(description = "Informações do Imposto Seletivo")
    @JsonProperty(value = "IS", index = 0)
    private ImpostoSeletivo IS;

    @Schema(description = "Informações do Imposto de Bens e Serviços - IBS e da Contribuição de Bens e Serviços - CBS")
    @JsonProperty(value = "IBSCBS", index = 1)
    private IBSCBS IBSCBS;

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
    public CBS getGCBS() {
        return IBSCBS != null ? IBSCBS.getGCBS() : null;
    }

    @JsonIgnore
    public boolean possuiCreditoPresumidoCbs() {
        return IBSCBS != null && IBSCBS.possuiCreditoPresumidoCbs();
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
    public GrupoIBSCBS getGrupoIBSCBS() {
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
    public IBSUF getGIBSUF() {
        return IBSCBS != null ? IBSCBS.getGIBSUF() : null;
    }

    @JsonIgnore
    public IBSMun getGIBSMun() {
        return IBSCBS != null ? IBSCBS.getGIBSMun() : null;
    }

    @JsonIgnore
    public boolean possuiMonofasia() {
        return IBSCBS != null && IBSCBS.possuiMonofasia();
    }

    @JsonIgnore
    public Monofasia getGIBSCBSMono() {
        return possuiMonofasia() ? IBSCBS.getGIBSCBSMono() : null;
    }
    
    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return IBSCBS != null && IBSCBS.possuiTributacaoRegular();
    }

    @JsonIgnore
    public TributacaoRegular getTributacaoRegular() {
        return IBSCBS != null ? IBSCBS.getTributacaoRegular() : null;
    }

}
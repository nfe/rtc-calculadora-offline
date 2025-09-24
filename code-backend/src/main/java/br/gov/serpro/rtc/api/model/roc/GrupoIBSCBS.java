package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
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
public class GrupoIBSCBS {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Base de cálculo do IBS e CBS")
    @JsonProperty(value = "vBC", index = 0)
    private BigDecimal vBC;
    
    @Schema(description = "Grupo de Informações do IBS para a UF")
    @JsonProperty(value = "gIBSUF", index = 1)
    private IBSUF gIBSUF;
    
    @Schema(description = "Grupo de Informações do IBS para o município")
    @JsonProperty(value = "gIBSMun", index = 2)
    private IBSMun gIBSMun;
    
    @Schema(description = "Grupo de Informações da CBS")
    @JsonProperty(value = "gCBS", index = 3)
    private CBS gCBS;
    
    @Schema(description = "Grupo de Informações da Tributação Regular")
    @JsonProperty(value = "gTribRegular", index = 4)
    private TributacaoRegular gTribRegular;
    
    @Schema(description = "Grupo de Informações do Crédito Presumido referente ao IBS")
    @JsonProperty(value = "gIBSCredPres", index = 5)
    private CreditoPresumido gIBSCredPres;
    
    @Schema(description = "Grupo de Informações do Crédito Presumido referente a CBS")
    @JsonProperty(value = "gCBSCredPres", index = 6)
    private CreditoPresumido gCBSCredPres;
    
    @Schema(description = "Grupo de informações da composição do valor do IBS e da CBS em compras governamentais")
    @JsonProperty(value = "gTribCompraGov", index = 7)
    private TributacaoCompraGovernamental gTribCompraGov;
    
    @JsonIgnore
    public boolean possuiBaseCalculoIBSCBS() {
        return vBC != null;
    }
    
    @JsonIgnore
    public BigDecimal getValorBaseCalculoIBSCBS() {
        return possuiBaseCalculoIBSCBS() ? vBC : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiCbs() {
        return gCBS != null;
    }
    
    @JsonIgnore
    public boolean possuiCreditoPresumidoCbs() {
        return gCBSCredPres != null;
    }
    
    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return gIBSCredPres != null && gIBSCredPres.getVCredPres() != null;
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? gIBSCredPres.getVCredPres() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return gIBSCredPres != null && gIBSCredPres.getVCredPresCondSus() != null;
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? gIBSCredPres.getVCredPresCondSus() : ZERO;
    }

    @JsonIgnore
    public boolean possuiIBSUF() {
        return gIBSUF != null;
    }
    
    @JsonIgnore
    public boolean possuiIBSMun() {
        return gIBSMun != null;
    }
    
    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return gTribRegular != null;
    }

    @JsonIgnore
    public TributacaoRegular getTributacaoRegular() {
        return gTribRegular;
    }

}
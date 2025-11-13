package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vBC", "gIBSUF", "gIBSMun", "vIBS", "gCBS", "gTribRegular", "gTribCompraGov" })
public class GrupoIBSCBSDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Base de cálculo do IBS e CBS")
    private BigDecimal vBC;
    
    @Schema(description = "Grupo de Informações do IBS para a UF")
    private IBSUFDomain gIBSUF;
    
    @Schema(description = "Grupo de Informações do IBS para o município")
    private IBSMunDomain gIBSMun;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS")
    private BigDecimal vIBS;
    
    @Schema(description = "Grupo de Informações da CBS")
    private CBSDomain gCBS;
    
    @Schema(description = "Grupo de Informações da Tributação Regular")
    private TributacaoRegularDomain gTribRegular;

    @Schema(description = "Grupo de informações da composição do valor do IBS e da CBS em compras governamentais")
    private TributacaoCompraGovernamentalDomain gTribCompraGov;
    
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
    public TributacaoRegularDomain getTributacaoRegular() {
        return gTribRegular;
    }

}
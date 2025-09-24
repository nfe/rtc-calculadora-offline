package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.IntegerTCSTSerializer;
import br.gov.serpro.rtc.config.serializer.StringTcClassTribSerializer;
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
public class IBSCBS {

    @JsonSerialize(using = IntegerTCSTSerializer.class)
    @Schema(description = "Código de Situação Tributária do IBS e CBS")
    @JsonProperty(value = "CST", index = 0)
    private Integer CST; // aqui esta string no ROC, mas na NT consta numérico e não alfanumerico...
    
    @JsonSerialize(using = StringTcClassTribSerializer.class)
    @Schema(description = "Código de Classificação Tributária do IBS e CBS")
    @JsonProperty(value = "cClassTrib", index = 1)
    private String cClassTrib;
    
    @Schema(description = "Grupo de Informações do IBS e da CBS")
    @JsonProperty(value = "gIBSCBS", index = 2)
    private GrupoIBSCBS gIBSCBS;
    
    @Schema(description = "Grupo de Informações do IBS e CBS em operações com imposto monofásico")
    @JsonProperty(value = "gIBSCBSMono", index = 3)
    private Monofasia gIBSCBSMono;
    
    @Schema(description = "Transferências de Crédito")
    @JsonProperty(value = "gTransfCred", index = 4)
    private TransferenciaCredito gTransfCred;
    
    @Schema(description = "Informações do crédito presumido de IBS para fornecimentos a partir da ZFM")
    @JsonProperty(value = "gCredPresIBSZFM", index = 5)
    private CreditoPresumidoIBSZFM gCredPresIBSZFM;

    @JsonIgnore
    public boolean possuiBaseCalculoIBSCBS() {
        return gIBSCBS != null && gIBSCBS.possuiBaseCalculoIBSCBS();
    }
    
    @JsonIgnore
    public BigDecimal getValorBaseCalculoIBSCBS() {
        return possuiBaseCalculoIBSCBS() ? gIBSCBS.getValorBaseCalculoIBSCBS() : ZERO;
    }

    @JsonIgnore
    public boolean possuiCbs() {
        return gIBSCBS != null && gIBSCBS.possuiCbs();
    }
    
    @JsonIgnore
    public CBS getGCBS() {
        return gIBSCBS != null ? gIBSCBS.getGCBS() : null;
    }
    
    @JsonIgnore
    public boolean possuiCreditoPresumidoCbs() {
        return gIBSCBS != null && gIBSCBS.possuiCreditoPresumidoCbs();
    }

    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return gIBSCBS != null && gIBSCBS.possuiCredPresIbs();
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? gIBSCBS.getVCredPresIbs() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return gIBSCBS != null && gIBSCBS.possuiCredPresCondSusIbs();
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? gIBSCBS.getVCredPresCondSusIbs() : ZERO;
    }

    @JsonIgnore
    public GrupoIBSCBS getGrupoIBSCBS() {
        return gIBSCBS;
    }

    @JsonIgnore
    public boolean possuiIBSUF() {
        return gIBSCBS != null && gIBSCBS.possuiIBSUF(); 
    }

    @JsonIgnore
    public boolean possuiIBSMun() {
        return gIBSCBS != null && gIBSCBS.possuiIBSMun();
    }

    @JsonIgnore
    public IBSUF getGIBSUF() {
        return gIBSCBS != null ? gIBSCBS.getGIBSUF() : null;
    }

    @JsonIgnore
    public IBSMun getGIBSMun() {
        return gIBSCBS != null ? gIBSCBS.getGIBSMun() : null;
    }

    @JsonIgnore
    public boolean possuiMonofasia() {
        return gIBSCBSMono != null;
    }

    @JsonIgnore
    public boolean possuiTributacaoRegular() {
        return gIBSCBS != null && gIBSCBS.possuiTributacaoRegular();
    }

    @JsonIgnore
    public TributacaoRegular getTributacaoRegular() {
        return gIBSCBS != null ? gIBSCBS.getTributacaoRegular() : null;
    }
    
}
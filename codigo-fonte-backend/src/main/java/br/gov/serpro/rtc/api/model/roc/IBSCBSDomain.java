package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.config.serializer.StringTCSTSerializer;
import br.gov.serpro.rtc.config.serializer.StringTcClassTribSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "CST", "cClassTrib", "indDoacao", "gIBSCBS", "gIBSCBSMono", "gTransfCred", "gAjusteCompet",
        "gEstornoCred", "gCredPresOper", "gCredPresIBSZFM" })
public class IBSCBSDomain implements SerializationVisibility {

    @JsonSerialize(using = StringTCSTSerializer.class)
    @Schema(description = "Código de Situação Tributária do IBS e CBS")
    private String CST; // aqui esta string no ROC, mas na NT consta numérico e não alfanumerico...
    
    @JsonSerialize(using = StringTcClassTribSerializer.class)
    @Schema(description = "Código de Classificação Tributária do IBS e CBS")
    private String cClassTrib;
    
    @Schema(description = "Indica a natureza da operação de doação, orientando a apuração e a geração de débitos ou estornos conforme o cenário")
    private Integer indDoacao;

    @Schema(description = "Grupo de Informações do IBS e da CBS")
    private GrupoIBSCBSDomain gIBSCBS;
    
    @Schema(description = "Grupo de Informações do IBS e CBS em operações com imposto monofásico")
    private MonofasiaDomain gIBSCBSMono;
    
    @Schema(description = "Transferências de Crédito")
    private TransferenciaCreditoDomain gTransfCred;
    
    @Schema(description = "Ajuste de competência")
    private AjusteCompetenciaDomain gAjusteCompet;

    @Schema(description = "Estorno de crédito")
    private EstornoCreditoDomain gEstornoCred;

    @Schema(description = "Crédito presumido da Operação")
    private CreditoPresumidoOperacaoDomain gCredPresOper;
    
    @Schema(description = "Informações do crédito presumido de IBS para fornecimentos a partir da ZFM")
    private CreditoPresumidoIBSZFMDomain gCredPresIBSZFM;

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
    public CBSDomain getGCBS() {
        return gIBSCBS != null ? gIBSCBS.getGCBS() : null;
    }
    
    @JsonIgnore
    public boolean possuiCredPresCbs() {
        return gCredPresOper != null && gCredPresOper.possuiCredPresCbs();
    }
    
    @JsonIgnore
    public IBSCBSCreditoPresumidoDomain getCredPresCbs() {
        return possuiCredPresCbs() ? gCredPresOper.getGCBSCredPres() : null;
    }

    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return gCredPresOper != null && gCredPresOper.possuiCredPresIbs();
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? gCredPresOper.getVCredPresIbs() : ZERO;
    }
    
    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return gCredPresOper != null && gCredPresOper.possuiCredPresCondSusIbs();
    }
    
    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? gCredPresOper.getVCredPresCondSusIbs() : ZERO;
    }

    @JsonIgnore
    public GrupoIBSCBSDomain getGrupoIBSCBS() {
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
    public IBSUFDomain getGIBSUF() {
        return gIBSCBS != null ? gIBSCBS.getGIBSUF() : null;
    }

    @JsonIgnore
    public IBSMunDomain getGIBSMun() {
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
    public TributacaoRegularDomain getTributacaoRegular() {
        return gIBSCBS != null ? gIBSCBS.getTributacaoRegular() : null;
    }
    
    @JsonIgnore
    public boolean possuiEstornoCredito() {
        return gEstornoCred != null;
    }
    
}
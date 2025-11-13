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
@JsonPropertyOrder({ "gMonoPadrao", "gMonoReten", "gMonoRet", "gMonoDif", "vTotIBSMonoItem", "vTotCBSMonoItem" })
public class MonofasiaDomain implements SerializationVisibility {
    
    @Schema(description = "Grupo de informações da Tributação Monofásica Padrão")
    private MonofasiaPadraoDomain gMonoPadrao;
    
    @Schema(description = "Grupo de informações da Tributação Monofásica Sujeita à Retenção")
    private MonofasiaRetencaoDomain gMonoReten;
    
    @Schema(description = "Grupo de informações da Tributação Monofásica Retida Anteriormente")
    private MonofasiaRetidoAnteriormenteDomain gMonoRet;
    
    @Schema(description = "Grupo de informações do Diferimento da Tributação Monofásica")
    private MonofasiaDiferimentoDomain gMonoDif;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total de IBS monofásico")
    @Builder.Default
    private BigDecimal vTotIBSMonoItem = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total de CBS monofásica")
    @Builder.Default
    private BigDecimal vTotCBSMonoItem = ZERO;

    @JsonIgnore
    public BigDecimal getVIBSMono() {
        return gMonoPadrao != null ? gMonoPadrao.getVIBSMono() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVCBSMono() {
        return gMonoPadrao != null ? gMonoPadrao.getVCBSMono() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVIBSMonoReten() {
        return gMonoReten != null ? gMonoReten.getVIBSMonoReten() : ZERO; 
    }

    @JsonIgnore
    public BigDecimal getVCBSMonoReten() {
        return gMonoReten != null ? gMonoReten.getVCBSMonoReten() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVIBSMonoRet() {
        return gMonoRet != null ? gMonoRet.getVIBSMonoRet() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVCBSMonoRet() {
        return gMonoRet != null ? gMonoRet.getVCBSMonoRet() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVIBSMonoDif() {
        return gMonoDif != null ? gMonoDif.getVIBSMonoDif() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVCBSMonoDif() {
        return gMonoDif != null ? gMonoDif.getVCBSMonoDif() : ZERO;
    }

    /**
     * Getter customizado para garantir que vTotIBSMonoItem nunca seja null
     */
    public BigDecimal getVTotIBSMonoItem() {
        return vTotIBSMonoItem != null ? vTotIBSMonoItem : ZERO;
    }

    /**
     * Getter customizado para garantir que vTotCBSMonoItem nunca seja null
     */
    public BigDecimal getVTotCBSMonoItem() {
        return vTotCBSMonoItem != null ? vTotCBSMonoItem : ZERO;
    }

}

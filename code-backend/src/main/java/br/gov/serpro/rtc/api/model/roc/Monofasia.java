package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1104OpSerializer;
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
public class Monofasia {
    
    @JsonSerialize(using = BigDecimalTDec1104OpSerializer.class)
    @Schema(description = "Quantidade tributada na monofasia")
    @JsonProperty(value = "qBCMono", index = 0)
    @Builder.Default
    private BigDecimal qBCMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem do IBS")
    @JsonProperty(value = "adRemIBS", index = 1)
    @Builder.Default
    private BigDecimal adRemIBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem da CBS")
    @JsonProperty(value = "adRemCBS", index = 2)
    @Builder.Default
    private BigDecimal adRemCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS monofásico")
    @JsonProperty(value = "vIBSMono", index = 3)
    @Builder.Default
    private BigDecimal vIBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS monofásica")
    @JsonProperty(value = "vCBSMono", index = 4)
    @Builder.Default
    private BigDecimal vCBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1104OpSerializer.class)
    @Schema(description = "Quantidade tributada sujeita à retenção na monofasia")
    @JsonProperty(value = "qBCMonoReten", index = 5)
    @Builder.Default
    private BigDecimal qBCMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem do IBS sujeito a retenção")
    @JsonProperty(value = "adRemIBSReten", index = 6)
    @Builder.Default
    private BigDecimal adRemIBSReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS monofásico sujeito a retenção")
    @JsonProperty(value = "vIBSMonoReten", index = 7)
    @Builder.Default
    private BigDecimal vIBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem da CBS sujeita a retenção")
    @JsonProperty(value = "adRemCBSReten", index = 8)
    @Builder.Default
    private BigDecimal adRemCBSReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS monofásica sujeita a retenção")
    @JsonProperty(value = "vCBSMonoReten", index = 9)
    @Builder.Default
    private BigDecimal vCBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1104OpSerializer.class)
    @Schema(description = "Quantidade tributada retida anteriormente")
    @JsonProperty(value = "qBCMonoRet", index = 10)
    @Builder.Default
    private BigDecimal qBCMonoRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem do IBS retido anteriormente")
    @JsonProperty(value = "adRemIBSRet", index = 11)
    @Builder.Default
    private BigDecimal adRemIBSRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS retido anteriormente")
    @JsonProperty(value = "vIBSMonoRet", index = 12)
    @Builder.Default
    private BigDecimal vIBSMonoRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem da CBS retida anteriormente")
    @JsonProperty(value = "adRemCBSRet", index = 13)
    @Builder.Default
    private BigDecimal adRemCBSRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS retida anteriormente")
    @JsonProperty(value = "vCBSMonoRet", index = 14)
    @Builder.Default
    private BigDecimal vCBSMonoRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual do diferimento do imposto monofásico do IBS")
    @JsonProperty(value = "pDifIBS", index = 15)
    @Builder.Default
    private BigDecimal pDifIBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS monofásico diferido")
    @JsonProperty(value = "vIBSMonoDif", index = 16)
    @Builder.Default
    private BigDecimal vIBSMonoDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual do diferimento do imposto monofásico da CBS")
    @JsonProperty(value = "pDifCBS", index = 17)
    @Builder.Default
    private BigDecimal pDifCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS monofásica diferida")
    @JsonProperty(value = "vCBSMonoDif", index = 18)
    @Builder.Default
    private BigDecimal vCBSMonoDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total de IBS monofásico")
    @JsonProperty(value = "vTotIBSMonoItem", index = 19)
    @Builder.Default
    private BigDecimal vTotIBSMonoItem = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Total de CBS monofásica")
    @JsonProperty(value = "vTotCBSMonoItem", index = 20)
    @Builder.Default
    private BigDecimal vTotCBSMonoItem = ZERO;

}

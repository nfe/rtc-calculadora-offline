package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
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
public class TributacaoRegular {

    @JsonSerialize(using = IntegerTCSTSerializer.class)
    @Schema(description = "Código de Situação Tributária do IBS e CBS")
    @JsonProperty(value = "CSTReg", index = 0)
    private Integer CSTReg;
    
    @JsonSerialize(using = StringTcClassTribSerializer.class)
    @Schema(description = "Código de Classificação Tributária do IBS e CBS")
    @JsonProperty(value = "cClassTribReg", index = 1)
    private String cClassTribReg;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Valor da alíquota do IBS da UF")
    @JsonProperty(value = "pAliqEfetRegIBSUF", index = 2)
    @Builder.Default
    private BigDecimal pAliqEfetRegIBSUF = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo do IBS da UF")
    @JsonProperty(value = "vTribRegIBSUF", index = 3)
    @Builder.Default
    private BigDecimal vTribRegIBSUF = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Valor da alíquota do IBS do Município")
    @JsonProperty(value = "pAliqEfetRegIBSMun", index = 4)
    @Builder.Default
    private BigDecimal pAliqEfetRegIBSMun = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo do IBS do Município")
    @JsonProperty(value = "vTribRegIBSMun", index = 5)
    @Builder.Default
    private BigDecimal vTribRegIBSMun = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Valor da alíquota da CBS")
    @JsonProperty(value = "pAliqEfetRegCBS", index = 6)
    @Builder.Default
    private BigDecimal pAliqEfetRegCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo da CBS")
    @JsonProperty(value = "vTribRegCBS", index = 7)
    @Builder.Default
    private BigDecimal vTribRegCBS = ZERO;

}

package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
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
@JsonPropertyOrder({ "CSTReg", "cClassTribReg", "pAliqEfetRegIBSUF", "vTribRegIBSUF", "pAliqEfetRegIBSMun", "vTribRegIBSMun", "pAliqEfetRegCBS", "vTribRegCBS" })
public class TributacaoRegularDomain implements SerializationVisibility {

    @JsonSerialize(using = StringTCSTSerializer.class)
    @Schema(description = "Código de Situação Tributária do IBS e CBS")
    private String CSTReg;
    
    @JsonSerialize(using = StringTcClassTribSerializer.class)
    @Schema(description = "Código de Classificação Tributária do IBS e CBS")
    private String cClassTribReg;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Valor da alíquota do IBS da UF")
    @Builder.Default
    private BigDecimal pAliqEfetRegIBSUF = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo do IBS da UF")
    @Builder.Default
    private BigDecimal vTribRegIBSUF = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Valor da alíquota do IBS do Município")
    @Builder.Default
    private BigDecimal pAliqEfetRegIBSMun = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo do IBS do Município")
    @Builder.Default
    private BigDecimal vTribRegIBSMun = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Valor da alíquota da CBS")
    @Builder.Default
    private BigDecimal pAliqEfetRegCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Tributo da CBS")
    @Builder.Default
    private BigDecimal vTribRegCBS = ZERO;

}

package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Usado para IBS Estadual
 */
@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class Diferimento {

    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual do Diferimento")
    @JsonProperty(value = "pDif", index = 0)
    @Builder.Default
    private BigDecimal pDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Diferimento")
    @JsonProperty(value = "vDif", index = 1)
    @Builder.Default
    private BigDecimal vDif = ZERO;

}
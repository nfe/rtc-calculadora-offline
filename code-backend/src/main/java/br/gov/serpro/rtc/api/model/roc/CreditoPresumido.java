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

@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class CreditoPresumido {

    @Schema(description = "Código de Classificação do Crédito Presumido")
    @JsonProperty(value = "cCredPres", index = 0)
    private Integer cCredPres;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual do Crédito Presumido")
    @JsonProperty(value = "pCredPres", index = 1)
    @Builder.Default
    private BigDecimal pCredPres = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Crédito Presumido")
    @JsonProperty(value = "vCredPres", index = 2)
    @Builder.Default
    private BigDecimal vCredPres = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Crédito Presumido em condição suspensiva")
    @JsonProperty(value = "vCredPresCondSus", index = 3)
    @Builder.Default
    private BigDecimal vCredPresCondSus = ZERO;

}

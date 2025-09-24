package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

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
public class CreditoPresumidoIBSZFM {
    
    @Schema(description = "Tipo de classificação de acordo com o art. 450, § 1º, da LC 214/25 para o cálculo do crédito presumido na ZFM")
    @JsonProperty(value = "tpCredPresIBSZFM", index = 0)
    private Integer tpCredPresIBSZFM;  
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do crédito presumido calculado sobre o saldo devedor apurado")
    @JsonProperty(value = "vCredPresIBSZFM", index = 1)
    @Builder.Default
    private BigDecimal vCredPresIBSZFM = ZERO;
}

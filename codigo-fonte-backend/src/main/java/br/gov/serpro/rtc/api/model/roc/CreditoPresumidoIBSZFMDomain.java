package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

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
@JsonPropertyOrder({ "competApur", "tpCredPresIBSZFM", "vCredPresIBSZFM" })
public class CreditoPresumidoIBSZFMDomain implements SerializationVisibility {
    
    @Schema(description = "Ano e mês referência do período de apuração (AAAA-MM)")
    private String competApur;

    @Schema(description = "Tipo de classificação de acordo com o art. 450, § 1º, da LC 214/25 para o cálculo do crédito presumido na ZFM")
    private Integer tpCredPresIBSZFM;  
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do crédito presumido calculado sobre o saldo devedor apurado")
    @Builder.Default
    private BigDecimal vCredPresIBSZFM = ZERO;
}

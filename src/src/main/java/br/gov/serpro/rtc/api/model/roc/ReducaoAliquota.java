package br.gov.serpro.rtc.api.model.roc;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
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
public class ReducaoAliquota {

    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual da redução de alíquota do cClassTrib")
    @JsonProperty(value = "pRedAliq", index = 0)
    private BigDecimal pRedAliq;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota Efetiva que será aplicada a Base de Cálculo")
    @JsonProperty(value = "pAliqEfet", index = 1)
    private BigDecimal pAliqEfet;

}

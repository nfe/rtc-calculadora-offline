package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class IBSUF extends GrupoImpostoCalculado {

    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota do IBS de competência das UF")
    @JsonProperty(value = "pIBSUF", index = 0)
    @Override
    public BigDecimal getAliquota() {
        return requireNonNullElse(super.getAliquota(), ZERO);
    }

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS de competência da UF")
    @JsonProperty(value = "vIBSUF", index = 4)
    @Override
    public BigDecimal getValorImposto() {
        return requireNonNullElse(super.getValorImposto(), ZERO);
    }

}
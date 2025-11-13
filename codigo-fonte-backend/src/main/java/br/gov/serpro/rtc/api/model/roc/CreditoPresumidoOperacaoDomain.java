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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "vBCCredPres", "cCredPres", "gIBSCredPres", "gCBSCredPres" })
public class CreditoPresumidoOperacaoDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da Base de Cálculo do Crédito Presumido da Operação")
    private BigDecimal vBCCredPres;

    @Schema(description = "Código de Classificação do Crédito Presumido")
    private Integer cCredPres;

    @Schema(description = "Grupo de Informações do Crédito Presumido referente ao IBS")
    private IBSCBSCreditoPresumidoDomain gIBSCredPres;

    @Schema(description = "Grupo de Informações do Crédito Presumido referente a CBS")
    private IBSCBSCreditoPresumidoDomain gCBSCredPres;

    @JsonIgnore
    public boolean possuiCredPresCbs() {
        return gCBSCredPres != null;
    }

    @JsonIgnore
    public boolean possuiCredPresIbs() {
        return gIBSCredPres != null && gIBSCredPres.getVCredPres() != null;
    }

    @JsonIgnore
    public BigDecimal getVCredPresIbs() {
        return possuiCredPresIbs() ? gIBSCredPres.getVCredPres() : ZERO;
    }

    @JsonIgnore
    public boolean possuiCredPresCondSusIbs() {
        return gIBSCredPres != null && gIBSCredPres.getVCredPresCondSus() != null;
    }

    @JsonIgnore
    public BigDecimal getVCredPresCondSusIbs() {
        return possuiCredPresCondSusIbs() ? gIBSCredPres.getVCredPresCondSus() : ZERO;
    }
}
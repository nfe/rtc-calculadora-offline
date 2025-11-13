package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "pIBSMun", "gDif", "gDevTrib", "gRed", "vIBSMun", "memoriaCalculo" })
public class IBSMunDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota do IBS de competência do Município")
    private BigDecimal pIBSMun = ZERO;
    
    @Schema(description = "Grupo de Informações do Diferimento")
    private DiferimentoDomain gDif;

    @Schema(description = "Grupo de Informações da devolução de tributos")
    private DevolucaoTributosDomain gDevTrib;

    @Schema(description = "Grupo de Informações da redução da alíquota")
    private ReducaoAliquotaDomain gRed;

    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS de competência do Município")
    private BigDecimal vIBSMun = ZERO;
    
    @Schema(description = "Memória de Cálculo")
    private String memoriaCalculo;
    
    @JsonIgnore
    public BigDecimal getVDif() {
        return getGDif() != null ? getGDif().getVDif() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVDevTrib() {
        return getGDevTrib() != null ? getGDevTrib().getVDevTrib() : ZERO;
    }

}
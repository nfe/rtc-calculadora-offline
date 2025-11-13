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
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1104OpRTCSerializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import br.gov.serpro.rtc.config.serializer.StringTCSTSerializer;
import br.gov.serpro.rtc.config.serializer.StringTcClassTribSerializer;
import br.gov.serpro.rtc.config.serializer.StringUTribSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "CSTIS", "cClassTribIS", "vBCIS", "pIS", "pISEspec", "uTrib", "qTrib", "vIS", "memoriaCalculo" })
public class ImpostoSeletivoDomain implements SerializationVisibility  {

    @JsonSerialize(using = StringTCSTSerializer.class)
    @Schema(description = "Código de Situação Tributária do Imposto Seletivo")
    private String CSTIS;
    
    @JsonSerialize(using = StringTcClassTribSerializer.class)
    @Schema(description = "Código de Classificação Tributária do Imposto Seletivo")
    private String cClassTribIS;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da Base de Cálculo do Imposto Seletivo")
    private BigDecimal vBCIS;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota do Imposto Seletivo")
    private BigDecimal pIS;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota específica por unidade de medida apropriada")
    private BigDecimal pISEspec;
    
    @JsonSerialize(using = StringUTribSerializer.class)
    @Schema(description = "Unidade de Medida Tributável")
    private String uTrib;
    
    @JsonSerialize(using = BigDecimalTDec1104OpRTCSerializer.class)
    @Schema(description = "Quantidade Tributável" )
    private BigDecimal qTrib;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do Imposto Seletivo")
    private BigDecimal vIS;
    
    @Schema(description = "Memória de Cálculo")
    private String memoriaCalculo;
    
    @JsonIgnore
    public BigDecimal getValorImpostoSeletivo() {
        return getVIS() != null ? getVIS() : ZERO;
    }

}

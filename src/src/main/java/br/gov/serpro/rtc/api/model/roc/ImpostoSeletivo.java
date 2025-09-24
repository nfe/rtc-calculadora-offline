package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1104OpSerializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import br.gov.serpro.rtc.config.serializer.IntegerTCSTSerializer;
import br.gov.serpro.rtc.config.serializer.StringTcClassTribSerializer;
import br.gov.serpro.rtc.config.serializer.StringUTribSerializer;
import br.gov.serpro.rtc.config.xml.IMemoriaCalculo;
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
public class ImpostoSeletivo implements IMemoriaCalculo {

    @JsonSerialize(using = IntegerTCSTSerializer.class)
    @JsonProperty(value = "CSTIS", index = 0)
    @Schema(description = "Código de Situação Tributária do Imposto Seletivo")
    private Integer CSTIS; // aqui consta String no ROC mas na NT consta numérico e não alfanumérico...
    
    @JsonSerialize(using = StringTcClassTribSerializer.class)
    @JsonProperty(value = "cClassTribIS", index = 1)
    @Schema(description = "Código de Classificação Tributária do Imposto Seletivo")
    private String cClassTribIS;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @JsonProperty(value = "vBCIS", index = 2)
    @Schema(description = "Valor da Base de Cálculo do Imposto Seletivo")
    private BigDecimal vBCIS;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @JsonProperty(value = "pIS", index = 3)
    @Schema(description = "Alíquota do Imposto Seletivo")
    private BigDecimal pIS;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @JsonProperty(value = "pISEspec", index = 4)
    @Schema(description = "Alíquota específica por unidade de medida apropriada")
    private BigDecimal pISEspec;
    
    @JsonSerialize(using = StringUTribSerializer.class)
    @JsonProperty(value = "uTrib", index = 5)
    @Schema(description = "Unidade de Medida Tributável")
    private String uTrib;
    
    @JsonSerialize(using = BigDecimalTDec1104OpSerializer.class)
    @JsonProperty(value = "qTrib", index = 6)
    @Schema(description = "Quantidade Tributável" )
    private BigDecimal qTrib;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @JsonProperty(value = "vIS", index = 7)
    @Schema(description = "Valor do Imposto Seletivo")
    private BigDecimal vIS;
    
    @Schema(description = "Memória de Cálculo")
    private String memoriaCalculo;
    
    @JsonIgnore
    public BigDecimal getValorImpostoSeletivo() {
        return getVIS() != null ? getVIS() : ZERO;
    }

}

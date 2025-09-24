package br.gov.serpro.rtc.api.model.roc;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.config.xml.IMemoriaCalculo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Classe abstrata que define os métodos para manipulação de informações de
 * impostos calculados (notadamente CBS e IBS). Inclui alíquota, diferimento,
 * devolução de tributos, redução de alíquota e valor do tributo.
 * </p>
 *
 * <p>
 * Utilizado para permitir a serialização e deserialização de objetos
 * relacionados a impostos.
 * </p>
 * 
 * <p>
 * Subclasses devem implementat os métodos
 * {@link br.gov.serpro.rtc.api.model.roc.GrupoImpostoCalculado#getAliquota()}
 * e
 * {@link br.gov.serpro.rtc.api.model.roc.GrupoImpostoCalculado#getValorImposto()}
 * para para configurar a serialização e deserialização desses campos.
 * </p>
 * 
 * Exemplo de uso:
 * 
 * <pre>
 * &#64;JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
 * &#64;Schema(description = "Alíquota da CBS")
 * &#64;JsonProperty(value = "pCBS", index = 0) <-- o atributo referente a aliquota deve ter a posicao 0 na configuração
 * &#64;Override
 * public BigDecimal getAliquota() {
 *     return requireNonNullElse(super.getAliquota(), ZERO);
 * }
 * 
 * &#64;JsonSerialize(using = BigDecimalTDec1302Serializer.class)
 * &#64;Schema(description = "Valor da CBS")
 * &#64;JsonProperty(value = "vCBS", index = 4) <-- o atributo referente ao valor do imposto deve ter a posicao 4 na configuração
 * &#64;Override
 * public BigDecimal getValorImposto() {
 *     return requireNonNullElse(super.getAliquota(), ZERO);
 * }
 * </pre>
 * 
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class GrupoImpostoCalculado implements IMemoriaCalculo {

    @JsonIgnore
    private BigDecimal aliquota;

    @Schema(description = "Grupo de Informações do Diferimento")
    @JsonProperty(value = "gDif", index = 1)
    private Diferimento gDif;

    @Schema(description = "Grupo de Informações da devolução de tributos")
    @JsonProperty(value = "gDevTrib", index = 2)
    private DevolucaoTributos gDevTrib;

    @Schema(description = "Grupo de Informações da redução da alíquota")
    @JsonProperty(value = "gRed", index = 3)
    private ReducaoAliquota gRed;

    @JsonIgnore
    private BigDecimal valorImposto;

    @Schema(description = "Memória de Cálculo")
    private String memoriaCalculo;
    
    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }
    
    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto  = valorImposto;
    }

    @JsonIgnore
    public BigDecimal getVDif() {
        return getGDif() != null ? getGDif().getVDif() : ZERO;
    }

    @JsonIgnore
    public BigDecimal getVDevTrib() {
        return getGDevTrib() != null ? getGDevTrib().getVDevTrib() : ZERO;
    }

}

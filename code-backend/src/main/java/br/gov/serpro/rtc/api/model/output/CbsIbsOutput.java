/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.gov.serpro.rtc.api.model.roc.Diferimento;
import br.gov.serpro.rtc.api.model.roc.ReducaoAliquota;
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
public final class CbsIbsOutput {

    @Schema(name = "aliquota", description = "Alíquota", example = "0.75")
    private BigDecimal aliquota;

    //@JsonIgnore
    @Schema(name = "baseCalculo", description = "Base de cálculo", example = "0.75")
    private BigDecimal baseCalculo;

    //@JsonIgnore
    @Schema(name = "quantidade", description = "Quantidade", example = "0.75")
    private BigDecimal quantidade;

    @Schema(name = "tributoCalculado", description = "Valor do tributo calculado", example = "0.75")
    private BigDecimal tributoCalculado;

    @Schema(name = "tributoDevido", description = "Valor do tributo devido", example = "0.75")
    private BigDecimal tributoDevido;

    @Schema(name = "grupoReducao", description = "Grupo de redução")
    private ReducaoAliquota grupoReducao;

    @Schema(name = "tributacaoRegular", description = "Grupo de desoneração")
    private TributacaoRegularOutput tributacaoRegular;

    @Schema(name = "grupoDiferimento", description = "Grupo de diferimento")
    private Diferimento grupoDiferimento;

    @Schema(name = "grupoMonofasia", description = "Grupo de monofasia")
    private GrupoMonofasiaOutput grupoMonofasia;

    @Schema(name = "memoriaCalculo", description = "Memória de cálculo", example = "Operação de consumo")
    private String memoriaCalculo;

}
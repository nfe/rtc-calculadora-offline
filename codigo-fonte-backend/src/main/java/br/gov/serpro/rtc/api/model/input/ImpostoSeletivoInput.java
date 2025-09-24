/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public final class ImpostoSeletivoInput {

    @NotNull
    @Pattern(regexp = "\\d+", message = "Informar somente dígitos")
    @Size(min = 3, max = 3)
    @Schema(name = "cst", description = "Código da situação tributária", example = "000")
    private String cst;

    @NotNull
    @Pattern(regexp = "\\d+", message = "Informar somente dígitos")
    @Size(min = 6, max = 6)
    @Schema(name = "cClassTrib", description = "Código da classificação tributária", example = "000000")
    @JsonProperty("cClassTrib")
    private String cClassTrib;

    @NotNull
    @PositiveOrZero
    @Schema(name = "baseCalculo", description = "Base de cálculo do imposto", example = "200.00")
    private BigDecimal baseCalculo;

    @NotNull
    @PositiveOrZero
    @Schema(name = "quantidade", description = "Quantidade", example = "1")
    private BigDecimal quantidade;

    @NotEmpty
    @Schema(name = "unidade", description = "Unidade de medida", example = "LT")
    private String unidade;

    @NotNull
    @PositiveOrZero
    @Schema(name = "impostoInformado", description = "Imposto Seletivo informado pelo contribuinte", example = "12.00")
    private BigDecimal impostoInformado;

    @JsonIgnore
    public BigDecimal getValorImpostoSeletivoInformado() {
        return impostoInformado != null ? impostoInformado : ZERO;
    }

}
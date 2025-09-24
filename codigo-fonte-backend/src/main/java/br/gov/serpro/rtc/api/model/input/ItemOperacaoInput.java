/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public final class ItemOperacaoInput {

    @NotNull
    @Min(1)
    @Max(9999999)
    @EqualsAndHashCode.Include
    @Schema(name = "numero", description = "Número do Item", example = "1")
    private Integer numero;

    // @Pattern(regexp = "\\d+", message = "Informar somente dígitos")
    // @Size(min = 8, max = 8)
    @Schema(name = "ncm", description = "Código NCM", example = "24021000")
    private String ncm;

    // @Pattern(regexp = "\\d+", message = "Informar somente dígitos")
    // @Size(min = 9, max = 9)
    @Schema(name = "nbs", description = "Código NBS", example = "109052100")
    private String nbs;
    
    @NotNull
    @Pattern(regexp = "\\d+", message = "Informar somente dígitos")
    @Size(min = 3, max = 3)
    @Schema(name = "cst", description = "Código de situação tributária", example = "000")
    private String cst;

    @NotNull
    @Pattern(regexp = "\\d+", message = "Informar somente dígitos")
    @Size(min = 6, max = 6)
    @Schema(name = "cClassTrib", description = "Código de classificação tributária", example = "000001")
    @JsonProperty("cClassTrib")
    private String cClassTrib;

    @NotNull
    @PositiveOrZero
    @Schema(name = "baseCalculo", description = "Base de cálculo do imposto", example = "200.00")
    private BigDecimal baseCalculo;

    // @NotNull
    // @PositiveOrZero
    @Schema(name = "quantidade", description = "Quantidade", example = "1")
    private BigDecimal quantidade;
    
    // @NotNull
    @Schema(name = "unidade", description = "Unidade de medida", example = "LT")
    private String unidade;

    @Valid // para validar os campos dentro do objeto impostoSeletivo
    @Schema(name = "impostoSeletivo", description = "Informações do Imposto Seletivo")
    private ImpostoSeletivoInput impostoSeletivo;

    @Valid // para validar os campos dentro do objeto desoneração
    @Schema(name = "tributacaoRegular", description = "Informações sobre tributação regular")
    private TributacaoRegularInput tributacaoRegular;

}
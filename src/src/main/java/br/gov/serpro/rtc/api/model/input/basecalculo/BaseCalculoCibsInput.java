/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input.basecalculo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public final class BaseCalculoCibsInput {

//    @NotNull
    // Integra Base de Calculo
    @PositiveOrZero
    @Schema(name = "valorFornecimento", description = "Valor do Fornecimento", example = "105.00")
    private BigDecimal valorFornecimento;

    @PositiveOrZero
    @Schema(name = "ajusteValorOperacao", description = "Ajuste do Valor da Operação", example = "5.00")
    private BigDecimal ajusteValorOperacao;

    @PositiveOrZero
    @Schema(name = "juros", description = "Juros", example = "5.00")
    private BigDecimal juros;

    @PositiveOrZero
    @Schema(name = "multas", description = "Multas", example = "5.00")
    private BigDecimal multas;

    @PositiveOrZero
    @Schema(name = "acrescimos", description = "Acrescimos", example = "5.00")
    private BigDecimal acrescimos;

    @PositiveOrZero
    @Schema(name = "encargos", description = "Encargos", example = "5.00")
    private BigDecimal encargos;

    @PositiveOrZero
    @Schema(name = "descontosCondicionais", description = "Descontos Condicionais", example = "5.00")
    private BigDecimal descontosCondicionais;

    @PositiveOrZero
    @Schema(name = "fretePorDentro", description = "Frete por Dentro", example = "5.00")
    private BigDecimal fretePorDentro;

    @PositiveOrZero
    @Schema(name = "outrosTributos", description = "Outros Tributos", example = "5.00")
    private BigDecimal outrosTributos;

    @PositiveOrZero
    @Schema(name = "impostoSeletivo", description = "Imposto Seletivo", example = "5.00")
    private BigDecimal impostoSeletivo;

    @PositiveOrZero
    @Schema(name = "demaisImportancias", description = "Demais Importancias", example = "5.00")
    private BigDecimal demaisImportancias;

    // Nao integra Base de Calculo
//    @Schema(name = "ipi", description = "IPI", example = "5.00")
//    private BigDecimal ipi;

//    @Schema(name = "descontosIncondicionais", description = "Descontos Incondicionais", example = "5.00")
//    private BigDecimal descontosIncondicionais;

    @PositiveOrZero
    @Schema(name = "icms", description = "ICMS", example = "5.00")
    private BigDecimal icms;

    @PositiveOrZero
    @Schema(name = "iss", description = "ISS", example = "5.00")
    private BigDecimal iss;

    @PositiveOrZero
    @Schema(name = "pis", description = "PIS", example = "5.00")
    private BigDecimal pis;

    @PositiveOrZero
    @Schema(name = "cofins", description = "COFINS", example = "5.00")
    private BigDecimal cofins;

}

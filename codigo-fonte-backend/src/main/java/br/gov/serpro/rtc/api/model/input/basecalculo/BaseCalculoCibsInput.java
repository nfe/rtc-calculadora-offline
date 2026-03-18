/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input.basecalculo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.SerializationVisibility;

@Getter
@Setter
@NoArgsConstructor
public final class BaseCalculoCibsInput implements SerializationVisibility {

    @NotNull
    @Positive
    @Schema(name = "anoFatoGerador", description = "Ano do Fato Gerador", example = "2026")
    private Integer anoFatoGerador;

    // Integra Base de Calculo
    @PositiveOrZero
    @Schema(name = "valorBem", description = "Valor do Bem (antes dos descontos/ajustes)", example = "105.00", defaultValue = "0.00")
    private BigDecimal valorBem = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "ajusteAcrescimos", description = "Ajuste decorrentes de acréscimos", example = "5.00", defaultValue = "0.00")
    private BigDecimal ajusteAcrescimos = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "juros", description = "Juros", example = "5.00", defaultValue = "0.00")
    private BigDecimal juros = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "multas", description = "Multas", example = "5.00", defaultValue = "0.00")
    private BigDecimal multas = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "encargos", description = "Encargos", example = "5.00", defaultValue = "0.00")
    private BigDecimal encargos = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "frete", description = "Frete cobrado como parte do valor da operação", example = "5.00", defaultValue = "0.00")
    private BigDecimal frete = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "impostoSeletivo", description = "Imposto Seletivo", example = "5.00", defaultValue = "0.00")
    private BigDecimal impostoSeletivo = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "outrosTributos", description = "Outros tributos (exceto CBS e IBS) e preços públicos, inclusive tarifas", example = "5.00", defaultValue = "0.00")
    private BigDecimal outrosTributos = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "demaisImportancias", description = "Demais importâncias cobradas ou recebidas como parte do valor da operação, inclusive seguros e taxas", example = "5.00", defaultValue = "0.00")
    private BigDecimal demaisImportancias = BigDecimal.ZERO;

    // Nao integra Base de Calculo
    @PositiveOrZero
    @Schema(name = "icms", description = "ICMS", example = "5.00", defaultValue = "0.00")
    private BigDecimal icms = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "iss", description = "ISS", example = "5.00", defaultValue = "0.00")
    private BigDecimal iss = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "pis", description = "PIS", example = "5.00", defaultValue = "0.00")
    private BigDecimal pis = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "pisImportacao", description = "PIS Importação", example = "5.00", defaultValue = "0.00")
    private BigDecimal pisImportacao = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "cofins", description = "COFINS", example = "5.00", defaultValue = "0.00")
    private BigDecimal cofins = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "cofinsImportacao", description = "COFINS Importação", example = "5.00", defaultValue = "0.00")
    private BigDecimal cofinsImportacao = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "cosip", description = "COSIP", example = "5.00", defaultValue = "0.00")
    private BigDecimal cosip = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "ipi", description = "IPI", example = "5.00", defaultValue = "0.00")
    private BigDecimal ipi = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "descontoIncondicional", description = "Desconto Incondicional", example = "5.00", defaultValue = "0.00")
    private BigDecimal descontoIncondicional = BigDecimal.ZERO;

}

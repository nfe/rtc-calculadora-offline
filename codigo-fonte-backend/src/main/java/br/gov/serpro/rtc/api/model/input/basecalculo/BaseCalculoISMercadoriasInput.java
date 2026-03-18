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
public final class BaseCalculoISMercadoriasInput implements SerializationVisibility {

    @NotNull
    @Positive
    @Schema(name = "anoFatoGerador", description = "Ano do Fato Gerador", example = "2027")
    private Integer anoFatoGerador;

    // Integra Base de Calculo
    @PositiveOrZero
    @Schema(name = "valorBem", description = "Valor do bem (antes dos descontos/ajustes)", example = "105.00", defaultValue = "0.00")
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
    @Schema(name = "freteCobrado", description = "Frete cobrado como parte do valor da operação", example = "5.00", defaultValue = "0.00")
    private BigDecimal freteCobrado = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "outrosTributos", description = "Tributos (exceto CBS, IBS e o próprio IS) e preços públicos, inclusive tarifas", example = "5.00", defaultValue = "0.00")
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
    @Schema(name = "cosip", description = "COSIP", example = "5.00", defaultValue = "0.00")
    private BigDecimal cosip = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "ipi", description = "IPI", example = "5.00", defaultValue = "0.00")
    private BigDecimal ipi = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "descontoIncondicional", description = "Desconto Incondicional", example = "5.00", defaultValue = "0.00")
    private BigDecimal descontoIncondicional = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "bonificacao", description = "Bonificações que constem do respectivo documento fiscal e não dependam de evento posterior, nos termos § 2º do Art. 417", example = "5.00", defaultValue = "0.00")
    private BigDecimal bonificacao = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "devolucaoVendas", description = "Devoluções de bens vendidos nos termos do Art. 418", example = "5.00", defaultValue = "0.00")
    private BigDecimal devolucaoVendas = BigDecimal.ZERO;

}

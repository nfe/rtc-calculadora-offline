/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input.nfse;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NfseBaseCalculoInput implements SerializationVisibility {

    @NotNull
    @Positive
    @Schema(name = "anoFatoGerador", description = "Ano do Fato Gerador", example = "2026")
    private Integer anoFatoGerador;
    
    @NotNull
    @Positive
    @Schema(name = "valorServico", description = "Valor do Serviço", example = "105.00")
    private BigDecimal valorServico;

    @PositiveOrZero
    @Schema(name = "descontoIncondicional", description = "Desconto Incondicional", example = "5.00", defaultValue = "0.00")
    private BigDecimal descontoIncondicional = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "vCalcReeRepRes", description = "Valor monetário (R$) total relativo ao fornecimento próprio de bens materiais ou relacionados a operações de terceiros, objeto de reembolso, repasse ou ressarcimento pelo recebedor, já tributados e aqui referenciados e que não integram da base de cálculo (BC) do ISSQN, do IBS e da CBS.", example = "5.00", defaultValue = "0.00")
    private BigDecimal vCalcReeRepRes = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "vCalcDedRedIBSCBS", description = "Valor monetário (R$) total relativo aos valores de dedução e redução da Base de Cálculo do IBS e da CBS referentes às operações de locação, cessão onerosa ou arrendamento de bens imóveis, e serviços médicos.", example = "5.00", defaultValue = "0.00")
    private BigDecimal vCalcDedRedIBSCBS = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "iss", description = "Imposto sobre Serviços de Qualquer Natureza", example = "5.00", defaultValue = "0.00")
    private BigDecimal iss = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "pis", description = "Programa de Integração Social", example = "5.00", defaultValue = "0.00")
    private BigDecimal pis = BigDecimal.ZERO;

    @PositiveOrZero
    @Schema(name = "cofins", description = "Contribuição para o Financiamento da Seguridade Social", example = "5.00", defaultValue = "0.00")
    private BigDecimal cofins = BigDecimal.ZERO;

}

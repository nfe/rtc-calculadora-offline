/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.pedagio;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public final class TributoPedagioOutput {

    @Schema(name = "aliquota", description = "Alíquota", example = "0.5")
    private BigDecimal aliquota;

    @Schema(name = "aliquotaEfetiva", description = "Alíquota efetiva", example = "0.5")
    private BigDecimal aliquotaEfetiva;

    @Schema(name = "tributoCalculado", description = "Tributo calculado", example = "0.75")
    private BigDecimal tributoCalculado;

    @Schema(name = "memoriaCalculo", description = "Descrição do processo de cálculo", example = "Texto descritivo")
    private String memoriaCalculo;
    
}
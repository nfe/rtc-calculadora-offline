/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.dadosabertos;

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
public class NcmDadosAbertosOutput {

    @Schema(name = "tributadoPeloImpostoSeletivo", description = "Tributado pelo Imposto Seletivo", example = "")
    private final boolean tributadoPeloImpostoSeletivo;

    @Schema(name = "aliquotaAdValorem", description = "Alíquota ad valorem do Imposto Seletivo", example = "")
    private final BigDecimal aliquotaAdValorem;

    @Schema(name = "aliquotaAdRem", description = "Alíquota ad rem do Imposto Seletivo", example = "")
    private final BigDecimal aliquotaAdRem;

    @Schema(name = "capitulo", description = "Capítulo da NCM", example = "")
    private final String capitulo;

    @Schema(name = "posicao", description = "Posição da NCM", example = "")
    private final String posicao;

    @Schema(name = "subposicao", description = "Subposição da NCM", example = "")
    private final String subposicao;

    @Schema(name = "item", description = "Item da NCM", example = "")
    private final String item;

    @Schema(name = "subitem", description = "Subitem da NCM", example = "")
    private final String subitem;

}

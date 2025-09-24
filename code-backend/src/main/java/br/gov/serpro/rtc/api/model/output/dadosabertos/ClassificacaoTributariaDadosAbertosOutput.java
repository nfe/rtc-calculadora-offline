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
public class ClassificacaoTributariaDadosAbertosOutput {

    @Schema(name = "codigo", description = "Código da classificação tributária", example = "000001")
    private String codigo;

    @Schema(name = "descricao", description = "Descrição da classificação tributária", example = "")
    private String descricao;

    @Schema(name = "tipoAliquota", description = "Tipo de alíquota da classificação tributária", example = "")
    private String tipoAliquota;

    @Schema(name = "nomenclatura", description = "Nomenclatura da classificação tributária", example = "")
    private String nomenclatura;

    @Schema(name = "descricaoTratamentoTributario", description = "Descrição do tratamento tributário da classificação tributária", example = "")
    private String descricaoTratamentoTributario;

    @Schema(name = "incompativelComSuspensao", description = "", example = "")
    private boolean incompativelComSuspensao;

    @Schema(name = "exigeGrupoDesoneracao", description = "", example = "")
    private boolean exigeGrupoDesoneracao;

    @Schema(name = "possuiPercentualReducao", description = "", example = "")
    private boolean possuiPercentualReducao;

    @Schema(name = "indicaApropriacaoCreditoAdquirenteCbs", description = "", example = "")
    private boolean indicaApropriacaoCreditoAdquirenteCbs;

    @Schema(name = "indicaApropriacaoCreditoAdquirenteIbs", description = "", example = "")
    private boolean indicaApropriacaoCreditoAdquirenteIbs;

    @Schema(name = "indicaCreditoPresumidoFornecedor", description = "", example = "")
    private boolean indicaCreditoPresumidoFornecedor;

    @Schema(name = "indicaCreditoPresumidoAdquirente", description = "", example = "")
    private boolean indicaCreditoPresumidoAdquirente;

    @Schema(name = "creditoOperacaoAntecedente", description = "", example = "")
    private String creditoOperacaoAntecedente;

    @Schema(name = "percentualReducaoCbs", description = "", example = "")
    private BigDecimal percentualReducaoCbs;

    @Schema(name = "percentualReducaoIbsUf", description = "", example = "")
    private BigDecimal percentualReducaoIbsUf;

    @Schema(name = "percentualReducaoIbsMun", description = "", example = "")
    private BigDecimal percentualReducaoIbsMun;

}
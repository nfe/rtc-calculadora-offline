/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.dadosabertos;

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
public class FundamentacaoClassificacaoDadosAbertosOutput {

    @Schema(name = "codigoClassificacaoTributaria", description = "Código da classificação tributária vinculada à fundamentacao legal", example = "000001")
    private String codigoClassificacaoTributaria;

    @Schema(name = "descricaoClassificacaoTributaria", description = "Descrição da classificação tributária vinculada à fundamentacao legal", example = "000001")
    private String descricaoClassificacaoTributaria;

    @Schema(name = "codigoSituacaoTributaria", description = "Código da situação tributária vinculada à fundamentacao legal", example = "000001")
    private String codigoSituacaoTributaria;

    @Schema(name = "descricaoSituacaoTributaria", description = "Descrição da situação tributária vinculada à fundamentacao legal", example = "000001")
    private String descricaoSituacaoTributaria;

    @Schema(name = "conjuntoTributo", description = "Tributo(s) vinculado(s) à fundamentacao legal", example = "CBS e IBS")
    private String conjuntoTributo;

    @Schema(name = "texto", description = "Texto da fundamentação legal", example = "")
    private String texto;

    @Schema(name = "textoCurto", description = "Texto curto da fundamentação legal", example = "")
    private String textoCurto;

    @Schema(name = "referenciaNormativa", description = "Referência normativa da fundamentação legal", example = "")
    private String referenciaNormativa;

}
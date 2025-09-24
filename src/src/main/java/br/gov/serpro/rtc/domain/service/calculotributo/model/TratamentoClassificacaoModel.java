/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo.model;

import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public final class TratamentoClassificacaoModel {

    private final TratamentoClassificacao tratamentoClassificacaoCbsIbs;
    private final TratamentoClassificacao tratamentoClassificacaoImpostoSeletivo;
    private final TratamentoClassificacao tratamentoClassificacaoCbsIbsDesoneracao;
    private final AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo;
    private final Boolean temDesoneracao;

}

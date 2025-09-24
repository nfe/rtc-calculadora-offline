/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo.model;

import java.time.OffsetDateTime;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public final class OperacaoModel {

    private final OffsetDateTime data;
    private final Long codigoMunicipio;
    private final Long codigoUf;
    private final String ncm;
    private final String nbs;
    private final ItemOperacaoInput item;
    private final TratamentoClassificacaoModel tratamentoClassificacao;

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public final class AliquotaImpostoSeletivoModel {

    private final BigDecimal aliquotaAdValorem;
    private final BigDecimal aliquotaAdRem;

}

package br.gov.serpro.rtc.domain.model.dto;

import java.math.BigDecimal;

import br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoDefinidaException;

public record AliquotaResultadoDTO(BigDecimal valorReferencia, BigDecimal valorPadrao,
        FormaAplicacaoEnum formaAplicacao) {

    public BigDecimal getValorAplicavel() {
        if (formaAplicacao == null) {
            return valorReferencia;
        } else {
            if (valorPadrao != null) {
                return switch (formaAplicacao) {
                case SUBSTITUICAO -> valorPadrao;
                case ACRESCIMO -> valorReferencia.add(valorPadrao);
                case DECRESCIMO -> valorReferencia.subtract(valorPadrao);
                default -> throw new FormaAplicacaoNaoDefinidaException();
                };
            }
            throw new RuntimeException(
                    "Valor de alíquota padrão não definida para forma de aplicação " + formaAplicacao.name());
        }
    }
}
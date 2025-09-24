/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class PercentualReducaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 7464628294957727271L;
    private static final String MESSAGE = "Percentual de redução não encontrado para classificação tributária com id %s e data %s";

    public PercentualReducaoNaoEncontradoException(Long id, LocalDate data) {
        super(String.format(MESSAGE, id, data));
    }

}
/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class NcmNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 2582687430572466747L;
    private static final String MESSAGE = "NCM de código %s não encontrada para a data %s";

    public NcmNaoEncontradaException(String ncm, LocalDate data) {
        super(String.format(MESSAGE, ncm, data));
    }

}
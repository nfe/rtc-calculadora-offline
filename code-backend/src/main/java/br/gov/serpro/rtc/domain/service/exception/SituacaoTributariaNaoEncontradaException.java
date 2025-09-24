/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class SituacaoTributariaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 202504161234567893L;
    private static final String MESSAGE = "Situação tributária (CST) não encontrada para código %s e data %s (%s)";

    public SituacaoTributariaNaoEncontradaException(String codigo, String tributo, LocalDate data) {
        super(String.format(MESSAGE, codigo, data, tributo));
    }

}

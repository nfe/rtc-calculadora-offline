/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class ClassificacaoTributariaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 202504161234567892L;
    private static final String MESSAGE = "Classificação tributária não encontrada para código %s e data %s (%s)";

    public ClassificacaoTributariaNaoEncontradaException(String codigo, String tributo, LocalDate data) {
        super(String.format(MESSAGE, codigo, data, tributo));
    }

}

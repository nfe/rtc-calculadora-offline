/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class FundamentacaoClassificacaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 2582681234562466747L;
    private static final String MESSAGE = "Fundamentação legal não encontrada para classificação tributária de id %s e data %s";

    public FundamentacaoClassificacaoNaoEncontradaException(Long id, LocalDate data) {
        super(String.format(MESSAGE, id, data));
    }

}
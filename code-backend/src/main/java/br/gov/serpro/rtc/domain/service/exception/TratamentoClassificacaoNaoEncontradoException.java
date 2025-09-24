/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class TratamentoClassificacaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 7812934713047267339L;
    private static final String MESSAGE = "Tratamento tributário não encontrado para classificação tributária com id %s e data %s";

    public TratamentoClassificacaoNaoEncontradoException(Long id, LocalDate data) {
        super(String.format(MESSAGE, id, data));
    }

}
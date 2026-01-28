/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class TratamentoTributarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 7812934713047267339L;
    private static final String MESSAGE = "Tratamento tributário com id %s não encontrado";

    public TratamentoTributarioNaoEncontradoException(Long id) {
        super(String.format(MESSAGE, id));
    }

}
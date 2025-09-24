/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class UfNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 202504161234567891L;
    private static final String MESSAGE = "UF de sigla %s não encontrada";

    public UfNaoEncontradaException(String siglaUf) {
        super(String.format(MESSAGE, siglaUf));
    }

}
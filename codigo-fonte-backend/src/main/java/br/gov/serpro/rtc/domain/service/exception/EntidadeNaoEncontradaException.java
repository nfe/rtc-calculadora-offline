/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public abstract class EntidadeNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public abstract class ValidacaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected ValidacaoException(String mensagem) {
        super(mensagem);
    }

}

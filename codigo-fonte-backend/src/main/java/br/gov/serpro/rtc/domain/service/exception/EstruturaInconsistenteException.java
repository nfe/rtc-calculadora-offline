/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public abstract class EstruturaInconsistenteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected EstruturaInconsistenteException(String mensagem) {
        super(mensagem);
    }

}

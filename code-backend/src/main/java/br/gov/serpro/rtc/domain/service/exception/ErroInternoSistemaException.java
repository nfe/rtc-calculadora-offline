/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ErroInternoSistemaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ErroInternoSistemaException(String mensagem) {
        super(mensagem);
    }

    public ErroInternoSistemaException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}

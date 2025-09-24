/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class CaptchaException extends ValidacaoException {

    private static final long serialVersionUID = 529876772972113L;

    public CaptchaException(String mensagem) {
        super(mensagem);
    }

}

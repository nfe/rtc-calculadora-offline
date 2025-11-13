/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ErroGenericoValidacaoException extends ValidacaoException {

    private static final long serialVersionUID = 529876772972777L;

    public ErroGenericoValidacaoException(String mensagem) {
        super(mensagem);
    }

}

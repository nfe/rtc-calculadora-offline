/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class TributacaoRegularInformadaIndevidamenteException extends ValidacaoException {

    private static final long serialVersionUID = 529876772976113L;
    private static final String MESSAGE = "Dados da tributação regular informados indevidamente para cClassTrib %s e CST %s";

    public TributacaoRegularInformadaIndevidamenteException(String cClassTrib, String cst) {
        super(String.format(MESSAGE, cClassTrib, cst));
    }

}

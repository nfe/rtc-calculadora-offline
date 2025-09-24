/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class DesoneracaoNaoInformadaException extends ValidacaoException {

    private static final long serialVersionUID = 523456725876113L;
    private static final String MESSAGE = "Dados da desoneração devem ser informados para cClassTrib %s e CST %s";

    public DesoneracaoNaoInformadaException(String cClassTrib, String cst) {
        super(String.format(MESSAGE, cClassTrib, cst));
    }

}

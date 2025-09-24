/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class NcmCompletoNaoInformadoException extends ValidacaoException {

    private static final long serialVersionUID = 6773977945809079927L;
    private static final String MESSAGE = "NCM completo não informado";

    public NcmCompletoNaoInformadoException() {
        super(MESSAGE);
    }

}

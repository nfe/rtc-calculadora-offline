/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class NbsCompletoNaoInformadoException extends ValidacaoException {

    private static final long serialVersionUID = 6773977945809079927L;
    private static final String MESSAGE = "NBS completo não informado";

    public NbsCompletoNaoInformadoException() {
        super(MESSAGE);
    }

}

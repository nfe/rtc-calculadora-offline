/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class NcmNbsSimultaneasException extends ValidacaoException {

    private static final long serialVersionUID = 6773977945809079927L;
    private static final String MESSAGE = "Não é permitido informar NCM e NBS simultaneamente para um item";

    public NcmNbsSimultaneasException() {
        super(MESSAGE);
    }

}

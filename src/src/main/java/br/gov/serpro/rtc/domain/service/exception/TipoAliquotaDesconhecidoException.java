/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class TipoAliquotaDesconhecidoException extends ValidacaoException {

    private static final long serialVersionUID = 1234567890123456789L;
    private static final String MESSAGE = "Tipo de alíquota (%s) desconhecido para cClassTrib %s e CST %s";

    public TipoAliquotaDesconhecidoException(String tipoAliquota, String cClassTrib, String cst) {
        super(String.format(MESSAGE, tipoAliquota, cClassTrib, cst));
    }

}

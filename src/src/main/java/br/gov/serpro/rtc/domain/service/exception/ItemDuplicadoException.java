/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ItemDuplicadoException extends ValidacaoException {

    private static final long serialVersionUID = 5292642729761124984L;
    private static final String MESSAGE = "Existe item duplicado: %s";

    public ItemDuplicadoException(String item) {
        super(String.format(MESSAGE, item));
    }

}

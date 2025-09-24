/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class IncompatibilidadeSuspensaoException extends ValidacaoException {

    private static final long serialVersionUID = 529236725876113L;
    private static final String MESSAGE = "Classificação tributária (cClassTrib) %s e situação tributária (CST) %s incompatíveis com suspensão";

    public IncompatibilidadeSuspensaoException(String cClassTrib, String cst) {
        super(String.format(MESSAGE, cClassTrib, cst));
    }

}

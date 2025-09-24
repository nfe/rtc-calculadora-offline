/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ClassificacaoTributariaNaoVinculadaSituacaoTributariaException extends ValidacaoException {

    private static final long serialVersionUID = 5292642729761124983L;
    private static final String MESSAGE = "Classificação tributária (cClassTrib) de código %s não está vinculada à situação tributária (CST) de código %s (%s)";

    public ClassificacaoTributariaNaoVinculadaSituacaoTributariaException(String cClassTrib, String cst, String tributo) {
        super(String.format(MESSAGE, cClassTrib, cst, tributo));
    }

}

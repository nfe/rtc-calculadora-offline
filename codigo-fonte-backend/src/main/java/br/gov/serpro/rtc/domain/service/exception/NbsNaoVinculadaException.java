/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class NbsNaoVinculadaException extends ValidacaoException {

    private static final long serialVersionUID = 1746374859333389372L;
    private static final String MESSAGE = "NBS de código %s não vinculada à Classificação Tributária de código %s (%s)";

    public NbsNaoVinculadaException(String nbs, String cClassTrib, String tributo) {
        super(String.format(MESSAGE, nbs, cClassTrib, tributo));
    }

}
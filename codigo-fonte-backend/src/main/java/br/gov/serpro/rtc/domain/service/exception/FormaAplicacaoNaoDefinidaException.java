/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class FormaAplicacaoNaoDefinidaException extends ErroInternoSistemaException {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "Forma de aplicação da alíquota própria não definida";

    public FormaAplicacaoNaoDefinidaException() {
        super(MESSAGE);
    }

}

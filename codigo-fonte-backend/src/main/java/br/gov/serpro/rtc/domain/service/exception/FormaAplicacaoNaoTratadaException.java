/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class FormaAplicacaoNaoTratadaException extends ErroInternoSistemaException {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "Forma de aplicação da alíquota própria não tratada";

    public FormaAplicacaoNaoTratadaException() {
        super(MESSAGE);
    }

}

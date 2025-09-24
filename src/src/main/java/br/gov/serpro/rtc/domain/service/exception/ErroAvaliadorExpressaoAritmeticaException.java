/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ErroAvaliadorExpressaoAritmeticaException extends ValidacaoException {

    private static final long serialVersionUID = 529876772976113L;
    private static final String MESSAGE = "Erro na avaliação de expressão aritmética (%s)";

    public ErroAvaliadorExpressaoAritmeticaException(String erro) {
        super(String.format(MESSAGE, erro));
    }

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ErroFaltaImplementacaoException extends ValidacaoException {

    private static final long serialVersionUID = 529876772972113L;
    private static final String MESSAGE = "A classificação tributária %s ainda não possui tratamento tributário implementado na Calculadora. Essa funcionalidade está prevista no roadmap e será disponibilizada em versão futura.";

    public ErroFaltaImplementacaoException(String erro) {
        super(String.format(MESSAGE, erro));
    }

}

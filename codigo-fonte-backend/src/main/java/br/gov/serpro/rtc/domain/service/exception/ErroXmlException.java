/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class ErroXmlException extends ValidacaoException {

    private static final long serialVersionUID = 529876772972113L;
    private static final String MESSAGE = "Erro na validação do XML: %s";

    public ErroXmlException(String erro) {
        super(String.format(MESSAGE, erro));
    }

}

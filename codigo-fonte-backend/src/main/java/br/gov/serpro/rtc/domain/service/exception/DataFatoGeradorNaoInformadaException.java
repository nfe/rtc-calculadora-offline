/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class DataFatoGeradorNaoInformadaException extends CampoInvalidoException {

    private static final long serialVersionUID = 529876772972777L;

    public DataFatoGeradorNaoInformadaException() {
        super("A data do fato gerador não foi informada.");
    }

}

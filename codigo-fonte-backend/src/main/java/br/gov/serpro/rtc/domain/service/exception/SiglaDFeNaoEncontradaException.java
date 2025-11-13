package br.gov.serpro.rtc.domain.service.exception;

public class SiglaDFeNaoEncontradaException extends ValidacaoException {

    private static final long serialVersionUID = 6773977945809079928L;
    private static final String MESSAGE = "Sigla DFe inválida: %s";

    public SiglaDFeNaoEncontradaException(String sigla) {
        super(String.format(MESSAGE, sigla));
    }
}
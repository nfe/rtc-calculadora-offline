/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class MunicipioNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 7958129873160537945L;
    private static final String MESSAGE = "Município de código %d não encontrado";

    public MunicipioNaoEncontradoException(Long id) {
        super(String.format(MESSAGE, id));
    }

}
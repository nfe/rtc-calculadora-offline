/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class MunicipioNaoPertencenteException extends ValidacaoException {

    private static final long serialVersionUID = 396099297144289032L;
    private static final String MESSAGE = "Município de código %d não pertence à UF de sigla %s";

    public MunicipioNaoPertencenteException(Long idMunicipio, String siglaUF) {
        super(String.format(MESSAGE, idMunicipio, siglaUF));
    }

}
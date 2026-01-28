/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class AliquotaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 202504161234567894L;

    private static final String MESSAGE = "Alíquota de referencia ou padrão não encontrada para o tributo com id %s, UF %s, município %s e data %s";

    public AliquotaNaoEncontradaException(long idTributo, Long codigoUf, Long municipio, LocalDate data) {
        super(String.format(MESSAGE, idTributo, codigoUf, municipio, data));
    }
}

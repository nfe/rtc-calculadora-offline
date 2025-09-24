/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class AliquotaReferenciaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 202504161234567894L;

    private static final String MESSAGE = "Alíquota de referência não encontrada para o tributo com id %s e data %s";

    public AliquotaReferenciaNaoEncontradaException(Long idTributo, LocalDate data) {
        super(String.format(MESSAGE, idTributo, data));
    }
}

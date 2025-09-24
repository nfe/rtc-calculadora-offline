/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class TributoSituacaoTributariaNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 2384764729111732363L;
    private static final String MESSAGE = "Tributo com id s% não encontrado para situação tributária com id %s e data %s";

    public TributoSituacaoTributariaNaoEncontradoException(Long idTributo, Long idSituacaoTributaria, LocalDate data) {
        super(String.format(MESSAGE, idTributo, idSituacaoTributaria, data));
    }

}
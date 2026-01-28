/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class ClassificacaoTributariaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 202504161234567892L;
    private static final String MESSAGE1 = "Classificação tributária não encontrada para código %s e data %s (%s)";
    private static final String MESSAGE2 = "Classificação tributária não encontrada para id %s";

    public ClassificacaoTributariaNaoEncontradaException(String codigo, String tributo, LocalDate data) {
        super(String.format(MESSAGE1, codigo, data, tributo));
    }
    
    public ClassificacaoTributariaNaoEncontradaException(Long id) {
        super(String.format(MESSAGE2, id));
    }

}

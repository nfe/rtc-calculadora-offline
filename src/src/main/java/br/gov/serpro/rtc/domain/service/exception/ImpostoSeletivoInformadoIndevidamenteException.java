/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class ImpostoSeletivoInformadoIndevidamenteException extends ValidacaoException {

    private static final long serialVersionUID = 529236772976113L;
    private static final String MESSAGE = "Dados do Imposto Seletivo informados indevidamente para NCM %s e data %s";

    public ImpostoSeletivoInformadoIndevidamenteException(String ncm, LocalDate data) {
        super(String.format(MESSAGE, ncm, data));
    }

}

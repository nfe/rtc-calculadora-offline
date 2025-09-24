/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

import java.time.LocalDate;

public class ImpostoSeletivoNaoInformadoException extends ValidacaoException {

    private static final long serialVersionUID = 529236725876113L;
    private static final String MESSAGE = "Dados do Imposto Seletivo devem ser informados para NCM %s e data %s";

    public ImpostoSeletivoNaoInformadoException(String ncm, LocalDate data) {
        super(String.format(MESSAGE, ncm, data));
    }

}

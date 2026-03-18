/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class AliquotaNegativaException extends ValidacaoException {

    private static final long serialVersionUID = 202601151234567890L;

    private static final String DEFAULT_MESSAGE = "Alíquota não pode ter valor negativo";
    private static final String MESSAGE_REFERENCIA = "Alíquota de referência não pode ter valor negativo";
    private static final String MESSAGE_PADRAO = "Alíquota padrão não pode ter valor negativo";
    private static final String MESSAGE_APLICAVEL = "Alíquota aplicável resultante não pode ter valor negativo";

    public AliquotaNegativaException() {
        super(DEFAULT_MESSAGE);
    }
    
    public AliquotaNegativaException(String tipoAliquota) {
        super(getMessage(tipoAliquota));
    }
    
    private static String getMessage(String tipo) {
        return switch (tipo) {
            case "referencia" -> MESSAGE_REFERENCIA;
            case "padrao" -> MESSAGE_PADRAO;
            case "aplicavel" -> MESSAGE_APLICAVEL;
            default -> DEFAULT_MESSAGE;
        };
    }
}

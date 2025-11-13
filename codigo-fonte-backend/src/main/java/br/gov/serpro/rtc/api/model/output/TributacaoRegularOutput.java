/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class TributacaoRegularOutput {

    private String cst;
    private String cClassTrib;
    private BigDecimal baseCalculo;
    private BigDecimal aliquotaEfetiva;
    private BigDecimal tributoDevido;
 
}

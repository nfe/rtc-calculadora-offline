/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.roc.DiferimentoDomain;
import br.gov.serpro.rtc.api.model.roc.ReducaoAliquotaDomain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public final class CbsIbsOutput {

    private BigDecimal aliquota;
    private BigDecimal baseCalculo;
    private BigDecimal quantidade;
    private BigDecimal tributoCalculado;
    private BigDecimal tributoDevido;
    private ReducaoAliquotaDomain grupoReducao;
    private TributacaoRegularOutput tributacaoRegular;
    private DiferimentoDomain grupoDiferimento;
    private GrupoMonofasiaOutput grupoMonofasia;
    private String memoriaCalculo;

}
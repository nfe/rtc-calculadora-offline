/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class GrupoEtapaMonofasiaOutput {
    
    private BigDecimal quantidade;
    private BigDecimal aliquotaAdRem;
    private BigDecimal valor;
    
    public static GrupoEtapaMonofasiaOutput merge(GrupoEtapaMonofasiaOutput g1, GrupoEtapaMonofasiaOutput g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        
        final var quantidade = requireNonNullElse(g1.getQuantidade(), requireNonNull(g2.getQuantidade()));
        final var aliquotaAdRem = requireNonNullElse(g1.getAliquotaAdRem(), requireNonNull(g2.getAliquotaAdRem()));
        final var valor = requireNonNullElse(g1.getValor(), ZERO).add(requireNonNullElse(g2.getValor(), ZERO));
        return GrupoEtapaMonofasiaOutput.builder()
                .quantidade(quantidade)
                .aliquotaAdRem(aliquotaAdRem)
                .valor(valor)
                .build();
    }

}

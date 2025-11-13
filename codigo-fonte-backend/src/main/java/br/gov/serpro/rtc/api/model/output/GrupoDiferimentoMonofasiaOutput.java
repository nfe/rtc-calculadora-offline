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
public class GrupoDiferimentoMonofasiaOutput {
    
    private BigDecimal valorDiferimento;
    private BigDecimal percentualDiferimento;
    
    public static GrupoDiferimentoMonofasiaOutput merge(GrupoDiferimentoMonofasiaOutput g1, GrupoDiferimentoMonofasiaOutput g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        
        final var valorDiferimento = requireNonNullElse(g1.getValorDiferimento(), ZERO).add(requireNonNullElse(g2.getValorDiferimento(), ZERO));
        
        // teoricamente, ambos os percentuais devem ser iguais ou pelo menos um deles deve ser nao nulo
        final var percentualDiferimento = requireNonNullElse(g1.getPercentualDiferimento(), requireNonNull(g2.getPercentualDiferimento()));
        
        return GrupoDiferimentoMonofasiaOutput.builder()
                .percentualDiferimento(percentualDiferimento)
                .valorDiferimento(valorDiferimento)
                .build();
    }
}

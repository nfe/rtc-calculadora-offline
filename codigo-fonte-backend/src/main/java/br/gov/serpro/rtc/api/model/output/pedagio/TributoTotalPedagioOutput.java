/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.pedagio;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public final class TributoTotalPedagioOutput {

    @Schema(name = "baseCalculo", description = "Base de cálculo do imposto", example = "200.00")
    private final BigDecimal baseCalculo;
    
    @Schema(name = "valorApurado", description = "Valor de imposto apurado", example = "0.75")
    private final BigDecimal valorApurado;
    
    @Schema(name = "valorDevido", description = "Valor de imposto devido", example = "0.75")
    private final BigDecimal valorDevido;

    @Schema(name = "valorTributo", description = "Valor do tributo", example = "0.75")
    private final BigDecimal valorTributo;
    
    @Schema(name = "totalMontanteDesonerado", description = "Total do montante desonerado", example = "10.22")
    private final BigDecimal valorMontanteDesonerado;

    /**
     * Totaliza o CBS.
     * 
     * @param trechosPedagio
     * @param totalBaseCalculo
     * @return
     */
    public static TributoTotalPedagioOutput totalizaCbs(List<TrechoPedagioOutput> trechosPedagio,
            BigDecimal totalBaseCalculo) {
        return getValorTotalIVA(trechosPedagio, totalBaseCalculo, functionCbs(), functionTributoCalculado());
    }

    /**
     * Totaliza o IBS Estadual.
     * 
     * @param trechosPedagio
     * @param totalBaseCalculo
     * @return
     */
    public static TributoTotalPedagioOutput totalizaIbsEstadual(List<TrechoPedagioOutput> trechosPedagio,
            BigDecimal totalBaseCalculo) {
        return getValorTotalIVA(trechosPedagio, totalBaseCalculo, functionIbsEstadual(), functionTributoCalculado());
    }

    /**
     * Totaliza o IBS Municipal.
     * 
     * @param trechosPedagio
     * @param totalBaseCalculo
     * @return
     */
    public static TributoTotalPedagioOutput totalizaIbsMunicipal(List<TrechoPedagioOutput> trechosPedagio,
            BigDecimal totalBaseCalculo) {
        return getValorTotalIVA(trechosPedagio, totalBaseCalculo, functionIbsMunicipal(), functionTributoCalculado());
    }
    
    /**
     * Calcula o total de um tributo do IVA (IBS ou CBS).
     * 
     * @param trechosPedagio
     * @param totalBaseCalculo
     * @param functionTributoATotalizar
     * @param functionTributoCalculado
     * @param functionMontanteDesonerado
     * @return
     */
    private static TributoTotalPedagioOutput getValorTotalIVA(List<TrechoPedagioOutput> trechosPedagio, BigDecimal totalBaseCalculo,
            Function<? super TrechoPedagioOutput, TributoPedagioOutput> functionTributoATotalizar,
            Function<? super TributoPedagioOutput, BigDecimal> functionTributoCalculado) {
        final BigDecimal totalTributo = totalizaIVA(trechosPedagio, functionTributoATotalizar, functionTributoCalculado).setScale(2, RoundingMode.HALF_UP);

        return TributoTotalPedagioOutput
            .builder()
            .baseCalculo(totalBaseCalculo)
            .valorApurado(totalTributo)
            .valorTributo(totalTributo)
            .valorDevido(totalTributo)
            .build();
    }

    /**
     * Totaliza o valor de um atributo dos itens para o tributo.
     * @param trechosPedagio
     * @param functionTributoATotalizar
     * @param functionAtributoATotalizar
     * @return
     */
    private static BigDecimal totalizaIVA(List<TrechoPedagioOutput> trechosPedagio,
            Function<? super TrechoPedagioOutput, TributoPedagioOutput> functionTributoATotalizar,
            Function<? super TributoPedagioOutput, BigDecimal> functionAtributoATotalizar) {
        return trechosPedagio.stream()
                .map(functionTributoATotalizar)
                .map(functionAtributoATotalizar)
                .reduce(ZERO, BigDecimal::add);
    }

    /**
     * Function para mapear os valores de CBS de um {@link TrechoPedagioOutput}
     * 
     * @return
     */
    private static Function<? super TrechoPedagioOutput, TributoPedagioOutput> functionCbs() {
        return TrechoPedagioOutput::getCbs;
    }

    /**
     * Function para mapear os valores de IBS Estadual de um
     * {@link TrechoPedagioOutput}
     * 
     * @return
     */
    private static Function<? super TrechoPedagioOutput, TributoPedagioOutput> functionIbsEstadual() {
        return TrechoPedagioOutput::getIbsEstadual;
    }

    /**
     * Function para mapear os valores de IBS Municipal de um
     * {@link TrechoPedagioOutput}
     * 
     * @return
     */
    private static Function<? super TrechoPedagioOutput, TributoPedagioOutput> functionIbsMunicipal() {
        return TrechoPedagioOutput::getIbsMunicipal;
    }

    /**
     * Function para mapear o atributo <code>tributoCalculado</code> do
     * {@link TributoPedagioOutput}
     * 
     * @return
     */
    private static Function<? super TributoPedagioOutput, BigDecimal> functionTributoCalculado() {
        return TributoPedagioOutput::getTributoCalculado;
    }

}

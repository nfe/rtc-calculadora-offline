/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.pedagio;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public final class TrechoPedagioOutput implements Comparable<TrechoPedagioOutput>{

    @EqualsAndHashCode.Include
    @Schema(name = "numero", description = "Número do Item", example = "1")
    private Integer numero;

    @Schema(name = "municipio", description = "Código do Município (tabela IBGE)", example = "4314902")
    private Long municipio;

    @Schema(name = "uf", description = "Sigla da UF", example = "RS")
    private String uf;

    @Schema(name = "baseCalculo", description = "Base de cálculo do imposto", example = "200.00")
    private BigDecimal baseCalculo;

    @Schema(name = "extensaoTrecho", description = "Extensao do trecho", example = "20")
    private BigDecimal extensaoTrecho;

    @Schema(name = "extensaoTotal", description = "Extensao total", example = "50")
    private BigDecimal extensaoTotal;

    @Schema(name = "cbs", description = "Informações do CBS")
    private TributoPedagioOutput cbs;

    @Schema(name = "ibsEstadual", description = "Informações do IBS Estadual")
    private TributoPedagioOutput ibsEstadual;

    @Schema(name = "ibsMunicipal", description = "Informações do IBS Municipal")
    private TributoPedagioOutput ibsMunicipal;

    @Override
    public int compareTo(TrechoPedagioOutput o) {
        return this.getNumero() - o.getNumero();
    }

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.pedagio;

import static br.gov.serpro.rtc.api.model.output.pedagio.TributoTotalPedagioOutput.totalizaCbs;
import static br.gov.serpro.rtc.api.model.output.pedagio.TributoTotalPedagioOutput.totalizaIbsEstadual;
import static br.gov.serpro.rtc.api.model.output.pedagio.TributoTotalPedagioOutput.totalizaIbsMunicipal;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(Include.NON_NULL)
public final class TotalPedagioOutput {

        @Schema(name = "cbsTotal", description = "Total de CBS apurado")
        private final TributoTotalPedagioOutput cbsTotal;

        @Schema(name = "ibsEstadualTotal", description = "Total de IBS Estadual apurado")
        private final TributoTotalPedagioOutput ibsEstadualTotal;

        @Schema(name = "ibsMunicipalTotal", description = "Total de IBS Municipal apurado")
        private final TributoTotalPedagioOutput ibsMunicipalTotal;

        public static TotalPedagioOutput getTotal(List<TrechoPedagioOutput> trechosPedagio) {
                final BigDecimal totalBaseCalculo = trechosPedagio.get(0).getBaseCalculo();
                return TotalPedagioOutput
                                .builder()
                                .cbsTotal(totalizaCbs(trechosPedagio, totalBaseCalculo))
                                .ibsEstadualTotal(totalizaIbsEstadual(trechosPedagio, totalBaseCalculo))
                                .ibsMunicipalTotal(totalizaIbsMunicipal(trechosPedagio, totalBaseCalculo))
                                .build();
        }

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.pedagio;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public final class PedagioOutput {

    @Schema(name = "dataHoraEmissao", description = "Data e hora de emissão do documento no formato UTC", example = "2027-01-01T09:50:05-03:00")
    private OffsetDateTime dataHoraEmissao;

    @Schema(name = "municipioOrigem", description = "Código do Município (tabela IBGE)", example = "4314902")
    private Long municipioOrigem;

    @Schema(name = "ufMunicipioOrigem", description = "Sigla da UF", example = "RS")
    private String ufMunicipioOrigem;

    @Size(min = 3, max = 3)
    @Schema(name = "cst", description = "Código de situação tributária", example = "000")
    private String cst;

    @Size(min = 6, max = 6)
    @Schema(name = "cClassTrib", description = "Código de classificação tributária", example = "000001")
    @JsonProperty("cClassTrib")
    private String cClassTrib;

    @Schema(name = "baseCalculo", description = "Base de cálculo", example = "200.00")
    private BigDecimal baseCalculo;

    @Schema(name = "extensaoTotal", description = "Extensão total (km)", example = "200.00")
    private BigDecimal extensaoTotal;

    @Schema(name = "trechos", description = "Trechos do pedágio")
    private List<TrechoPedagioOutput> trechos;

    @Schema(name = "total", description = "Total de tributo calculado")
    private TotalPedagioOutput total;

}

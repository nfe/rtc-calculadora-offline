/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input.pedagio;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.gov.serpro.rtc.core.util.StreamUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class PedagioInput {

    @NotNull
    @Schema(name = "dataHoraEmissao", description = "Data e hora de emissão do documento no formato UTC", example = "2027-01-01T09:50:05-03:00")
    private OffsetDateTime dataHoraEmissao;

    @NotNull
    @Min(0)
    @Max(9999999)
    @Schema(name = "codigoMunicipioOrigem", description = "Código do Município (tabela IBGE)", example = "4314902")
    private Long codigoMunicipioOrigem;

    @NotNull
    @Size(min = 2, max = 2)
    @Schema(name = "ufMunicipioOrigem", description = "Sigla da UF", example = "RS")
    private String ufMunicipioOrigem;

    @NotNull
    @Size(min = 3, max = 3)
    @Schema(name = "cst", description = "Código de situação tributária", example = "000")
    private String cst;

    @NotNull
    @Size(min = 6, max = 6)
    @Schema(name = "cClassTrib", description = "Código de classificação tributária", example = "000002")
    @JsonProperty("cClassTrib")
    private String cClassTrib;

    @NotNull
    @Schema(name = "baseCalculo", description = "Base de cálculo do imposto", example = "200.00")
    private BigDecimal baseCalculo;

    @Valid
    @NotEmpty
    @Schema(name = "trechos", description = "Trechos do pedágio")
    private List<TrechoPedagioInput> trechos;

    @JsonIgnore
    public BigDecimal getExtensaoTotal() {
        return StreamUtils.nullSafe(trechos)
                .map(TrechoPedagioInput::getExtensao)
                .filter(Objects::nonNull)
                .reduce(ZERO, BigDecimal::add);
    }
}

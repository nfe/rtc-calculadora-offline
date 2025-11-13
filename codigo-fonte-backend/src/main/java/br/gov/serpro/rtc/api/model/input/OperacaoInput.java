/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
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
public final class OperacaoInput implements SerializationVisibility {
    @NotNull
    @Schema(name = "id", description = "Identificador do ROC", example = "6194602ea71cbf9431c236de4409d920")
    private String id;

    @NotNull
    @Schema(name = "versao", description = "Versão do ROC", example = "0.0.1")
    private String versao;

    @NotNull
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @Schema(name = "dataHoraEmissao", description = "Data e hora de emissão do documento no formato UTC", example = "2026-01-01T09:50:05-03:00")
    private OffsetDateTime dataHoraEmissao;

    @NotNull
    @Min(0)
    @Max(9999999)
    @Schema(name = "municipio", description = "Código do Município (tabela IBGE)", example = "4314902")
    private Long municipio;

    @Size(min = 2, max = 2)
    @Schema(name = "uf", description = "Sigla da UF", example = "RS")
    private String uf;

    @Valid
    @NotEmpty
    @Schema(name = "itens", description = "Itens da Operação")
    private List<ItemOperacaoInput> itens;
}

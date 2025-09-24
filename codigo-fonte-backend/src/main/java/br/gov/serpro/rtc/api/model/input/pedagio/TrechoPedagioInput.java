/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.input.pedagio;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public final class TrechoPedagioInput {

    @NotNull
    @EqualsAndHashCode.Include
    @Schema(name = "numero", description = "Número do Trecho", example = "1")
    private Integer numero;
     
    @NotNull
    @Min(0)
    @Max(9999999)
    @Schema(name = "municipio", description = "Código do Município (tabela IBGE)", example = "4314902")
    private Long municipio;

    @NotNull
    @Size(min = 2, max = 2)
    @Schema(name = "uf", description = "Sigla da UF", example = "RS")
    private String uf;
    
    @NotNull
    @Schema(name = "extensao", description = "Extensão do trecho", example = "10")
    private BigDecimal extensao;

}
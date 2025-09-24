/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class TributacaoRegularOutput {

    @Schema(name = "cst", description = "Código da situação tributária", example = "000")
    private String cst;

    @JsonProperty("cClassTrib")
    @Schema(name = "cClassTrib", description = "Código da classificação tributária", example = "000001")
    private String cClassTrib;
    
    @Schema(name = "baseCalculo", description = "Base de cálculo", example = "200")
    private BigDecimal baseCalculo;

    @Schema(name = "aliquotaEfetiva", description = "Alíquota efetiva", example = "0.5")
    private BigDecimal aliquotaEfetiva;

    @Schema(name = "tributoDevido", description = "Tributo devido", example = "0.5")
    private BigDecimal tributoDevido;
 
}

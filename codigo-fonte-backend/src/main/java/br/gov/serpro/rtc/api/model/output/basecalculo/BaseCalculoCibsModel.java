/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.model.output.basecalculo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.SerializationVisibility;

@Value
@Builder
public class BaseCalculoCibsModel implements SerializationVisibility {

    @Schema(name = "baseCalculo", description = "Base de cálculo da operação", example = "100.55")
    private final BigDecimal baseCalculo;

}

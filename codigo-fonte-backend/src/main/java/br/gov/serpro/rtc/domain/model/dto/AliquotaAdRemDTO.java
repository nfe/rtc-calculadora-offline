/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.dto;

import java.math.BigDecimal;

public record AliquotaAdRemDTO(BigDecimal valor, String unidadeMedida) {}
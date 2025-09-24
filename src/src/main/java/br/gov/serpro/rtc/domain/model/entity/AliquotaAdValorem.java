/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "ALIQUOTA_AD_VALOREM")
public class AliquotaAdValorem {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "AADV_ID")
    private Long id;

    @NotNull
    @Column(name = "AADV_VALOR")
    private BigDecimal valor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AADV_TBTO_ID")
    private Tributo tributo;

    @NotNull
    @Column(name = "AADV_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "AADV_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
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
@Table(name = "ALIQUOTA_AD_REM")
public class AliquotaAdRem {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "AARE_ID")
    private Long id;

    @NotNull
    @Column(name = "AARE_VALOR")
    private BigDecimal valor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AARE_UNMD_ID")
    private UnidadeMedida unidadeMedida;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AARE_TBTO_ID")
    private Tributo tributo;

    @NotNull
    @Column(name = "AARE_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "AARE_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
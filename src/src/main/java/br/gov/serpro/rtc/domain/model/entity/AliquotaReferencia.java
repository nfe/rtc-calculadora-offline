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
@Table(name = "ALIQUOTA_REFERENCIA")
public class AliquotaReferencia {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ALRE_ID")
    private Long id;

    @NotNull
    @Column(name = "ALRE_VALOR")
    private BigDecimal valor;

    @NotNull
    @Column(name = "ALRE_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "ALRE_FIM_VIGENCIA")
    private LocalDate fimVigencia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ALRE_TBTO_ID")
    private Tributo tributo;

}
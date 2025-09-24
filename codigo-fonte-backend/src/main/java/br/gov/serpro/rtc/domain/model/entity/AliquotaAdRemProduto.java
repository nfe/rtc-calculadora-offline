/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

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
@Table(name = "ALIQUOTA_AD_REM_PRODUTO")
public class AliquotaAdRemProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "AARP_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AARP_NCM_CD")
    private Ncm ncm;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AARP_AARE_ID")
    private AliquotaAdRem aliquotaAdRem;

    @NotNull
    @Column(name = "AARP_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "AARP_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
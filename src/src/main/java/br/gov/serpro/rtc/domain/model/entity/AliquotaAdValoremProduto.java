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
@Table(name = "ALIQUOTA_AD_VALOREM_PRODUTO")
public class AliquotaAdValoremProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "AAVP_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AAVP_NCM_CD")
    private Ncm ncm;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AAVP_AADV_ID")
    private AliquotaAdValorem aliquotaAdValorem;

    @NotNull
    @Column(name = "AAVP_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "AAVP_FIM_VIGENCIA")
    private LocalDate fimVigencia;



}
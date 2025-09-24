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
@Table(name = "EXCECAO_AD_VALOREM_PRODUTO")
public class ExcecaoAdValoremProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "EAVP_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EAVP_NCM_CD")
    private Ncm ncm;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EAVP_AAVP_ID")
    private AliquotaAdValoremProduto aliquotaAdValoremProduto;

    @NotNull
    @Column(name = "EAVP_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "EAVP_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
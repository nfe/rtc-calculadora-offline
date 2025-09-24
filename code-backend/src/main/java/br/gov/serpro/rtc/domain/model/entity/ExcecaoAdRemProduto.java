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
@Table(name = "EXCECAO_AD_REM_PRODUTO")
public class ExcecaoAdRemProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "EARP_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EARP_NCM_CD")
    private Ncm ncm;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EARP_AARP_ID")
    private AliquotaAdRemProduto aliquotaAdRemProduto;

    @NotNull
    @Column(name = "EARP_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "EARP_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
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
@Table(name = "EXCECAO_AD_VALOREM_SERVICO")
public class ExcecaoAdValoremServico {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "EAVS_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EAVS_NBS_CD")
    private Nbs nbs;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EAVS_AAVS_ID")
    private AliquotaAdValoremServico aliquotaAdValoremServico;

    @NotNull
    @Column(name = "EAVS_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "EAVS_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
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
@Table(name = "TRIBUTO_SITUACAO_TRIBUTARIA")
public class TributoSituacaoTributaria {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TRST_ID")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "TRST_SITR_ID")
    private SituacaoTributaria situacaoTributaria;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "TRST_TBTO_ID")
    private Tributo tributo;

    @NotNull
    @Column(name = "TRST_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TRST_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

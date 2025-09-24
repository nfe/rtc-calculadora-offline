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
@Table(name = "FUNDAMENTACAO_CLASSIFICACAO")
public class FundamentacaoClassificacao {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "FDCL_ID")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "FDCL_CLTR_ID")
    private ClassificacaoTributaria classificacaoTributaria;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "FDCL_FDLG_ID")
    private FundamentacaoLegal fundamentacaoLegal;

    @NotNull
    @Column(name = "FDCL_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "FDCL_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

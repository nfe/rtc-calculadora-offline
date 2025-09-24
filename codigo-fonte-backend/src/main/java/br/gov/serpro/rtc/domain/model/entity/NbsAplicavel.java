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
@Table(name = "NBS_APLICAVEL")
public class NbsAplicavel {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "NBSA_ID")
    private Long id;

    @NotNull
    @Column(name = "NBSA_NBS_CD")
    private String nbs;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "NBSA_CLTR_ID")
    private ClassificacaoTributaria classificacaoTributaria;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "NBSA_ANXO_ID")
    private Anexo anexo;

    @NotNull
    @Column(name = "NBSA_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "NBSA_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

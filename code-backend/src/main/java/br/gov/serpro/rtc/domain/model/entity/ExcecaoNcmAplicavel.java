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
@Table(name = "EXCECAO_NCM_APLICAVEL")
public class ExcecaoNcmAplicavel {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ENCM_ID")
    private Long id;

    @NotNull
    @Column(name = "ENCM_NCM_CD")
    private String ncm;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ENCM_NCMA_ID")
    private NcmAplicavel ncmAplicavel;

    @NotNull
    @Column(name = "ENCM_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "ENCM_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

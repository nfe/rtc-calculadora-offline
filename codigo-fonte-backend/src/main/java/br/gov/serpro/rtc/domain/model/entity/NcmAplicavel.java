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
@Table(name = "NCM_APLICAVEL")
public class NcmAplicavel {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "NCMA_ID")
    private Long id;

    @NotNull
    @Column(name = "NCMA_NCM_CD")
    private String ncm;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "NCMA_CLTR_ID")
    private ClassificacaoTributaria classificacaoTributaria;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "NCMA_ANXO_ID")
    private Anexo anexo;

    @NotNull
    @Column(name = "NCMA_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "NCMA_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

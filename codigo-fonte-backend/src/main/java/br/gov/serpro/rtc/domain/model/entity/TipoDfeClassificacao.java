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
@Table(name = "TIPO_DFE_CLASSIFICACAO")
public class TipoDfeClassificacao {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TDCL_ID")
    private Long id;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "TDCL_CLTR_ID")
    private ClassificacaoTributaria classificacaoTributaria;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "TDCL_TPDF_ID")
    private TipoDfe tipoDfe;

    @NotNull
    @Column(name = "TDCL_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TDCL_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

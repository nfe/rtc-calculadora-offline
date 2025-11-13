/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "TIPO_DFE")
public class TipoDfe {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TPDF_ID")
    private Long id;
    
    //@NotNull
    @Column(name = "TPDF_TIPO")
    private Integer tipo;

    @NotNull
    @Column(name = "TPDF_SIGLA")
    private String sigla;

    @NotNull
    @Column(name = "TPDF_DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "TPDF_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TPDF_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

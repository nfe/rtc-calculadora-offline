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
@Table(name = "NBS")
public class Nbs {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "NBS_CD")
    private String codigo;

    @NotNull
    @Column(name = "NBS_DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "NBS_LC_116")
    private String lc116;

    @NotNull
    @Column(name = "NBS_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "NBS_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

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
@Table(name = "NCM")
public class Ncm {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "NCM_CD")
    private String codigo;

    @NotNull
    @Column(name = "NCM_DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "NCM_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "NCM_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

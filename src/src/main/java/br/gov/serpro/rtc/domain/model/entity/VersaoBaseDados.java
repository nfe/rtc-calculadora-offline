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
@Table(name = "VERSAO_BASE_DADO")
public class VersaoBaseDados {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "VRBD_ID")
    private Long id;

    @NotNull
    @Column(name = "VRBD_DATA", nullable = false)
    private LocalDate data;

    @NotNull
    @Column(name = "VRBD_DESCRICAO", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "VRBD_VERSAO_BASE_DADO", nullable = false)
    private String numeroVersao;

}

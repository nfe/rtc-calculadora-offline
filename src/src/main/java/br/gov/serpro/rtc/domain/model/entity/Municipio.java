/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

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
@Table(name = "MUNICIPIO")
public class Municipio {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "MUNI_CD")
    private Long codigo;

    @NotNull
    @Column(name = "MUNI_NOME")
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "MUNI_UF_CD")
    private Uf uf;

}

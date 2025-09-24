/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

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
@Table(name = "UF")
public class Uf {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "UF_CD")
    private Long codigo;

    @NotNull
    @Column(name = "UF_SIGLA", nullable = false)
    private String sigla;

    @NotNull
    @Column(name = "UF_NOME", nullable = false)
    private String nome;

}

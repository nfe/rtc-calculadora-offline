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
@Table(name = "UNIDADE_MEDIDA")
public class UnidadeMedida {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "UNMD_ID")
    private Long id;
    
    @NotNull
    @Column(name = "UNMD_NOME")
    private String nome;

    @NotNull
    @Column(name = "UNMD_SIGLA")
    private String sigla;

    @NotNull
    @Column(name = "UNMD_DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "UNMD_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "UNMD_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

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
@Table(name = "TOMADOR_ESPECIFICO")
public class TomadorEspecifico {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TMES_ID")
    private Long id;
    
    @NotNull
    @Column(name = "TMES_NOME")
    private String nome;

    @NotNull
    @Column(name = "TMES_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TMES_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}
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
@Table(name = "TRIBUTO")
public class Tributo {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TBTO_ID")
    private Long id;
    
    @NotNull
    @Column(name = "TBTO_NOME")
    private String nome;

    @NotNull
    @Column(name = "TBTO_SIGLA")
    private String sigla;

    @NotNull
    @Column(name = "TBTO_ENTE_FEDERATIVO")
    private String enteFederativo;

    @NotNull
    @Column(name = "TBTO_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TBTO_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

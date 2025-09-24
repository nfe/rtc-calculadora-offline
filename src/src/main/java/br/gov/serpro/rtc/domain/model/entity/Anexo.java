/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "ANEXO")
public class Anexo {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ANXO_ID")
    private Long id;

    //@NotNull
    @Column(name = "ANXO_NUMERO")
    private String numero;

    //@NotNull
    @Column(name = "ANXO_DESCRICAO")
    private String descricao;

    //@NotNull
    @Column(name = "ANXO_NUMERO_ITEM")
    private String numeroItem;

    //@NotNull
    @Column(name = "ANXO_TEXTO_ITEM")
    private String textoItem;

    //@NotNull
    @Column(name = "ANXO_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "ANXO_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

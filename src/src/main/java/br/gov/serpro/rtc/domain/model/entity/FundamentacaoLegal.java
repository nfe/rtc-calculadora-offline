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
@Table(name = "FUNDAMENTACAO_LEGAL")
public class FundamentacaoLegal {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "FDLG_ID")
    private Long id;

    @NotNull
    @Column(name = "FDLG_TEXTO")
    private String texto;

    @NotNull
    @Column(name = "FDLG_TEXTO_CURTO")
    private String textoCurto;

    @NotNull
    @Column(name = "FDLG_REFERENCIA_NORMATIVA")
    private String referenciaNormativa;

    @NotNull
    @Column(name = "FDLG_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "FDLG_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

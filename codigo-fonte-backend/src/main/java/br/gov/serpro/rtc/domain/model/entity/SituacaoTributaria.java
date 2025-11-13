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
@Table(name = "SITUACAO_TRIBUTARIA")
public class SituacaoTributaria {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "SITR_ID")
    private Long id;

    @NotNull
    @Column(name = "SITR_CD")
    private String codigo;

    @NotNull
    @Column(name = "SITR_DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "SITR_IND_GIBSCBS")
    private Boolean inGrupoIbsCbs;

    @NotNull
    @Column(name = "SITR_IND_GIBSCBSMONO")
    private Boolean inGrupoIbsCbsMono;

    @NotNull
    @Column(name = "SITR_IND_GRED")
    private Boolean inGrupoReducao;

    @NotNull
    @Column(name = "SITR_IND_GDIF")
    private Boolean inGrupoDiferimento;

    @NotNull
    @Column(name = "SITR_IND_GTRANSFCRED")
    private Boolean inGrupoTranferenciaCredito;

    @NotNull
    @Column(name = "SITR_IND_GCREDPRESIBSZFM")
    private Boolean inGrupoCreditoPresumidoIbsZfm;

    @NotNull
    @Column(name = "SITR_IND_GAJUSTECOMPET")
    private Boolean inGrupoAjusteCompetencia;

    @NotNull
    @Column(name = "SITR_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "SITR_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

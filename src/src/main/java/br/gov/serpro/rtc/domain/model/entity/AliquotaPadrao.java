/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoDefinidaException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "ALIQUOTA_PADRAO")
public class AliquotaPadrao {
    
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ALPA_ID")
    private Long id;

    @NotNull
    @Column(name = "ALPA_FORMA_APLICACAO")
    @Enumerated(EnumType.STRING)
    private FormaAplicacaoEnum formaAplicacao;

    @Positive
    @NotNull
    @Column(name = "ALPA_VALOR")
    private BigDecimal valor;

    @NotNull
    @Column(name = "ALPA_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "ALPA_FIM_VIGENCIA")
    private LocalDate fimVigencia;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ALPA_ALRE_ID")
    private AliquotaReferencia aliquotaReferencia;

    @ManyToOne
    @JoinColumn(name = "ALPA_MUNI_CD")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "ALPA_UF_CD")
    private Uf uf;

    public BigDecimal getValorAplicavel() {
        switch (getFormaAplicacao()) {
        case SUBSTITUICAO:
            return getValor();
        case ACRESCIMO:
            return aliquotaReferencia.getValor().add(getValor());
        case DECRESCIMO:
            return aliquotaReferencia.getValor().subtract(getValor());
        default:
            throw new FormaAplicacaoNaoDefinidaException();
        }
    }

}

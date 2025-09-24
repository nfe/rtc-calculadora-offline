/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.entity;

import java.time.LocalDate;

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
@Table(name = "TRATAMENTO_CLASSIFICACAO")
public class TratamentoClassificacao {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TRCL_ID")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "TRCL_CLTR_ID")
    private ClassificacaoTributaria classificacaoTributaria;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "TRCL_TRTR_ID")
    private TratamentoTributario tratamentoTributario;

    @NotNull
    @Column(name = "TRCL_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TRCL_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

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
@Table(name = "CLASSIFICACAO_TRIBUTARIA")
public class ClassificacaoTributaria {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "CLTR_ID")
    private Long id;

    @NotNull
    @Column(name = "CLTR_CD")
    private String codigo;

    @NotNull
    @Column(name = "CLTR_DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "CLTR_MEMORIA_CALCULO")
    private String memoriaCalculo;

    @Column(name = "CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS")
    private Boolean inApropriacaoCreditosAdquirentesCBS;

    @Column(name = "CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS")
    private Boolean inApropriacaoCreditosAdquirentesIBS;

    @Column(name = "CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR")
    private Boolean inCreditoPresumidoFornecedor;

    @Column(name = "CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE")
    private Boolean inCreditoPresumidoAdquirente;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "CLTR_SITR_ID")
    private SituacaoTributaria situacaoTributaria;
    
    @ManyToOne
    @JoinColumn(name = "CLTR_TMES_ID")
    private TomadorEspecifico tomadorEspecifico;
    
    @NotNull
    @Column(name = "CLTR_TIPO_ALIQUOTA")
    private String tipoAliquota;

    @Column(name = "CLTR_CREDITO_OPERACAO_ANTECEDENTE")
    private String creditoOperacaoAntecedente;

    @Column(name = "CLTR_NOMENCLATURA")
    private String nomenclatura;

    @NotNull
    @Column(name = "CLTR_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "CLTR_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

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
@Table(name = "TRATAMENTO_TRIBUTARIO")
public class TratamentoTributario {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "TRTR_ID")
    private Long id;
    
    @NotNull
    @Column(name = "TRTR_DESCRICAO")
    private String descricao;

    @Column(name = "TRTR_EXPRESSAO_ALIQUOTA")
    private String expressaoAliquota;

    @Column(name = "TRTR_EXPRESSAO_ALIQUOTA_EFETIVA")
    private String expressaoAliquotaEfetiva;

    @NotNull
    @Column(name = "TRTR_EXPRESSAO_BASE_CALCULO")
    private String expressaoBaseCalculo;

    @NotNull
    @Column(name = "TRTR_EXPRESSAO_TRIBUTO_CALCULADO")
    private String expressaoTributoCalculado;

    @NotNull
    @Column(name = "TRTR_EXPRESSAO_TRIBUTO_DEVIDO")
    private String expressaoTributoDevido;

    @Column(name = "TRTR_EXPRESSAO_PERCENTUAL_DIFERIMENTO")
    private String expressaoPercentualDiferimento;

    @Column(name = "TRTR_EXPRESSAO_VALOR_DIFERIMENTO")
    private String expressaoValorDiferimento;

    @NotNull
    @Column(name = "TRTR_IN_INCOMPATIVEL_COM_SUSPENSAO")
    private boolean inIncompativelComSuspensao;

    @NotNull
    @Column(name = "TRTR_IN_EXIGE_GRUPO_DESONERACAO")
    private boolean inExigeGrupoDesoneracao;

    @NotNull
    @Column(name = "TRTR_IN_POSSUI_PERCENTUAL_REDUCAO")
    private boolean inPossuiPercentualReducao;

    @NotNull
    @Column(name = "TRTR_IN_POSSUI_AJUSTE")
    private boolean inPossuiAjuste;

    @NotNull
    @Column(name = "TRTR_IN_POSSUI_REDUTOR")
    private boolean inPossuiRedutor;

    @NotNull
    @Column(name = "TRTR_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TRTR_FIM_VIGENCIA")
    private LocalDate fimVigencia;

}

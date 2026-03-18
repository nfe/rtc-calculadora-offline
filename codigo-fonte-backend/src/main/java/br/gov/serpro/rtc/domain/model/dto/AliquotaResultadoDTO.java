package br.gov.serpro.rtc.domain.model.dto;

import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.ACRESCIMO;
import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.DECRESCIMO;
import static br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum.SUBSTITUICAO;

import java.math.BigDecimal;

import br.gov.serpro.rtc.domain.model.enumeration.FormaAplicacaoEnum;
import br.gov.serpro.rtc.domain.service.exception.AliquotaNegativaException;
import br.gov.serpro.rtc.domain.service.exception.AliquotaPadraoNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.AliquotaReferenciaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoDefinidaException;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoTratadaException;

public record AliquotaResultadoDTO(BigDecimal valorReferencia, BigDecimal valorPadrao,
        FormaAplicacaoEnum formaAplicacao) {
    
    public AliquotaResultadoDTO {
        if (valorReferencia == null) {
            // aliquota de referencia é obrigatória
            throw new AliquotaReferenciaNaoEncontradaException();
        }
        
        // valida se valor de referência é negativo
        if (valorReferencia.compareTo(BigDecimal.ZERO) < 0) {
            throw new AliquotaNegativaException("referencia");
        }
        
        if (valorPadrao != null) {
            // valida se valor padrão é negativo
            if (valorPadrao.compareTo(BigDecimal.ZERO) < 0) {
                throw new AliquotaNegativaException("padrao");
            }
            if (formaAplicacao == null) {
                // se aliquota padrao está definida, formaAplicacao também deve estar
                throw new FormaAplicacaoNaoDefinidaException();
            }
            
            if (formaAplicacao == ACRESCIMO) {
                valorPadrao = valorReferencia.add(valorPadrao);
            } else if (formaAplicacao == DECRESCIMO) {
                valorPadrao = valorReferencia.subtract(valorPadrao);
            } else if (formaAplicacao == SUBSTITUICAO) {
                // valorPadrao permanece o mesmo
            } else {
                // caso surja uma forma de aplicação nova, nao tratada
                throw new FormaAplicacaoNaoTratadaException();
            }
            
            // valida se valor aplicável (após cálculo) for negativo
            if (valorPadrao.compareTo(BigDecimal.ZERO) < 0) {
                throw new AliquotaNegativaException("aplicavel");
            }
        } else {
            // se aliquota padrao não está definida, formaAplicacao também não deve estar
            if (formaAplicacao != null) {
                throw new AliquotaPadraoNaoEncontradaException();
            }
        }
    }
    
    public BigDecimal valorAplicavel() {
        return valorPadrao != null ? valorPadrao : valorReferencia;
    }

}
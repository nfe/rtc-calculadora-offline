/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.AliquotaReferenciaRepository;
import br.gov.serpro.rtc.domain.service.exception.AliquotaReferenciaNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaReferenciaService {

    private final AliquotaReferenciaRepository repository;
    
    public BigDecimal buscar(Long idTributo, LocalDate data) {
        final var aliquotaReferencia = repository.buscar(idTributo, data);
        if (aliquotaReferencia == null) {
            throw new AliquotaReferenciaNaoEncontradaException(idTributo, data);
        }
        return aliquotaReferencia;
    }

}
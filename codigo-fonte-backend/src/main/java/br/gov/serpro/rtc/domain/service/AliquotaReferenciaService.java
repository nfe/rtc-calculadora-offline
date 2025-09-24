/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.AliquotaReferencia;
import br.gov.serpro.rtc.domain.repository.AliquotaReferenciaRepository;
import br.gov.serpro.rtc.domain.service.exception.AliquotaReferenciaNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaReferenciaService {

    private final AliquotaReferenciaRepository repository;
    
    public AliquotaReferencia buscar(Long idTributo, LocalDate data) {
        return repository.buscar(idTributo, data)
                .orElseThrow(() -> new AliquotaReferenciaNaoEncontradaException(idTributo, data));
    }

}
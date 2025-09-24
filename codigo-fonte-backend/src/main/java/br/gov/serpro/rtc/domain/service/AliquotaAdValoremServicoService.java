/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.AliquotaAdValoremServicoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaAdValoremServicoService {

    private final AliquotaAdValoremServicoRepository repository;

    public BigDecimal buscarAliquotaAdValorem(String nbs, Long idTributo, LocalDate data) {
        return repository.buscarAliquotaAdValorem(nbs, idTributo, data)
                .orElse(null);
    }

}
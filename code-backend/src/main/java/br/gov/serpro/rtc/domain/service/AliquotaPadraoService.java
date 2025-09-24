/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.AliquotaPadrao;
import br.gov.serpro.rtc.domain.model.entity.AliquotaReferencia;
import br.gov.serpro.rtc.domain.repository.AliquotaPadraoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaPadraoService {

    private final AliquotaPadraoRepository repository;

    public AliquotaPadrao buscarAliquotaUniao(AliquotaReferencia aliquotaReferencia, LocalDate data) {
        return repository.buscarAliquotaUniao(aliquotaReferencia, data).orElse(null);
    }

    public AliquotaPadrao buscarAliquotaUf(AliquotaReferencia aliquotaReferencia, Long codigoUf, LocalDate data) {
        return repository.buscarAliquotaUf(aliquotaReferencia, codigoUf, data).orElse(null);
    }

    public AliquotaPadrao buscarAliquotaMunicipio(AliquotaReferencia aliquotaReferencia, Long municipio, LocalDate data) {
        return repository.buscarAliquotaMunicipio(aliquotaReferencia, municipio, data).orElse(null);
    }

}
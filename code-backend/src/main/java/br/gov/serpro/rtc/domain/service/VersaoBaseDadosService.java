/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.VersaoBaseDados;
import br.gov.serpro.rtc.domain.repository.VersaoBaseDadosRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VersaoBaseDadosService {

    private final VersaoBaseDadosRepository versaoRepository;

    @Cacheable(cacheNames = "VersaoBaseDadosService.getUltimaVersao")
    public VersaoBaseDados getUltimaVersao() {
        return versaoRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new IllegalStateException("Nenhuma versão de base de dados encontrada"));
    }

}
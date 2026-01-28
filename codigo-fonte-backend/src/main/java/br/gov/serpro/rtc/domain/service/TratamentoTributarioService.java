/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.TratamentoTributario;
import br.gov.serpro.rtc.domain.repository.TratamentoTributarioRepository;
import br.gov.serpro.rtc.domain.service.exception.TratamentoTributarioNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TratamentoTributarioService {

    private final TratamentoTributarioRepository repository;

    /*
     * Entradas recomendadas na cache: 50
     * Memória estimada: ~90 KB
     */
    @Cacheable(cacheNames = "TratamentoTributarioService.buscar")
    public TratamentoTributario buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new TratamentoTributarioNaoEncontradoException(id));
    }

}
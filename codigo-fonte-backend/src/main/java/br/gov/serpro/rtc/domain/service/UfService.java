/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.Uf;
import br.gov.serpro.rtc.domain.repository.UfRepository;
import br.gov.serpro.rtc.domain.service.exception.UfNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UfService {

    private final UfRepository ufRepository;

    public Uf buscar(String sigla) {
        return ufRepository.consultarPorSigla(sigla)
                .orElseThrow(() -> new UfNaoEncontradaException(sigla));
    }
    
    public List<Uf> consultarTodos() {
        return ufRepository.findAll();
    }

    public void validarUf(String sigla) {
        if (!ufRepository.existeUf(sigla)) {
            throw new UfNaoEncontradaException(sigla);
        }
    }

}
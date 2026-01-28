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

    public Long buscar(String sigla) {
        final var codigo = ufRepository.consultarPorSigla(sigla);
        if (codigo == null) {
            throw new UfNaoEncontradaException(sigla);
        }
        return codigo;
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
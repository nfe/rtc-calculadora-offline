/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.Municipio;
import br.gov.serpro.rtc.domain.repository.MunicipioRepository;
import br.gov.serpro.rtc.domain.service.exception.MunicipioNaoEncontradoException;
import br.gov.serpro.rtc.domain.service.exception.MunicipioNaoPertencenteException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MunicipioService {

    private final MunicipioRepository repository;

    public List<Municipio> consultarTodosPorSiglaUf(String uf) {
        return repository.consultarTodosPorSiglaUf(uf);
    }

    public void validarMunicipio(Long codigoMunicipio, String siglaUf) {
        int resultado = repository.validarMunicipio(codigoMunicipio, siglaUf);
        if (resultado == 1) {
            throw new MunicipioNaoEncontradoException(codigoMunicipio);
        } else if (resultado == 2) {
            throw new MunicipioNaoPertencenteException(codigoMunicipio, siglaUf);
        }
    }

}
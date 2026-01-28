/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.dto.AliquotaResultadoDTO;
import br.gov.serpro.rtc.domain.repository.AliquotaPadraoRepository;
import br.gov.serpro.rtc.domain.service.exception.AliquotaNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaPadraoService {

    private final AliquotaPadraoRepository repository;

    public AliquotaResultadoDTO buscarAliquota(long idTributo, Long codigoUf, Long municipio, LocalDate data) {
        final var a = repository.buscarAliquota(idTributo, codigoUf, municipio, data);
        if (a == null) {
            throw new AliquotaNaoEncontradaException(idTributo, codigoUf, municipio, data);
        }
        return a;
    }

}
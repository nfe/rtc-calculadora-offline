/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.TributoSituacaoTributariaRepository;
import br.gov.serpro.rtc.domain.service.exception.TributoSituacaoTributariaNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TributoSituacaoTributariaService {

    private final TributoSituacaoTributariaRepository repository;

    public Long consultarPorIdSituacaoTributaria(Long idSituacaoTributaria, LocalDate data) {
        Long idTributo = repository.consultarPorIdSituacaoTributaria(idSituacaoTributaria, data);
        if (idTributo == null) {
            throw new TributoSituacaoTributariaNaoEncontradoException(idTributo, idSituacaoTributaria, data);
        }
        return idTributo;
    }

}
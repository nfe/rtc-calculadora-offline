/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.dto.TratamentoClassificacaoDTO;
import br.gov.serpro.rtc.domain.repository.TratamentoClassificacaoRepository;
import br.gov.serpro.rtc.domain.service.exception.TratamentoClassificacaoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TratamentoClassificacaoService {

    private final TratamentoClassificacaoRepository tratamentoClassificacaoRepository;

    public TratamentoClassificacaoDTO buscarTratamentoClassificacao(Long idClassificacaoTributaria, LocalDate data) {
        TratamentoClassificacaoDTO tratamentoClassificacao = tratamentoClassificacaoRepository.buscarTratamentoClassificacao(idClassificacaoTributaria, data);
        if (tratamentoClassificacao == null) {
            throw new TratamentoClassificacaoNaoEncontradoException(idClassificacaoTributaria, data);
        }
        return tratamentoClassificacao;
    }

}
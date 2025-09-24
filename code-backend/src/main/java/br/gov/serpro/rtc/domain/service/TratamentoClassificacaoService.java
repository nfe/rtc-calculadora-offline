/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;
import br.gov.serpro.rtc.domain.repository.TratamentoClassificacaoRepository;
import br.gov.serpro.rtc.domain.service.exception.TratamentoClassificacaoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TratamentoClassificacaoService {

    private final TratamentoClassificacaoRepository tratamentoClassificacaoRepository;

    public TratamentoClassificacao buscarTratamentoClassificacao(Long idClassificacaoTributaria, LocalDate data) {
        TratamentoClassificacao tratamentoClassificacao = tratamentoClassificacaoRepository.buscarTratamentoClassificacao(idClassificacaoTributaria, data);
        if (tratamentoClassificacao == null) {
            throw new TratamentoClassificacaoNaoEncontradoException(idClassificacaoTributaria, data);
        }
        return tratamentoClassificacao;
    }

}
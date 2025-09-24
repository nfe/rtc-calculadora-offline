/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.FundamentacaoClassificacao;
import br.gov.serpro.rtc.domain.repository.FundamentacaoClassificacaoRepository;
import br.gov.serpro.rtc.domain.service.exception.FundamentacaoClassificacaoNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FundamentacaoClassificacaoService {

    private final FundamentacaoClassificacaoRepository repository;

    public FundamentacaoClassificacao buscar(Long idClassificacaoTributaria, LocalDate data) {
        FundamentacaoClassificacao fundamentacaoClassificacao = repository.buscar(idClassificacaoTributaria, data);
        if (fundamentacaoClassificacao == null) {
            throw new FundamentacaoClassificacaoNaoEncontradaException(idClassificacaoTributaria, data);
        }
        return fundamentacaoClassificacao;
    }

    public List<FundamentacaoClassificacao> buscarTodas(LocalDate data) {
        return repository.buscarTodas(data);
    }

}
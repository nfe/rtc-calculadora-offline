/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.TipoDfeClassificacao;
import br.gov.serpro.rtc.domain.repository.TipoDfeClassificacaoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TipoDfeClassificacaoService {

    private final TipoDfeClassificacaoRepository tipoDfeClassificacaoRepository;

    @Cacheable(cacheNames = "TipoDfeClassificacaoService.buscar")
    public List<TipoDfeClassificacao> buscar(Long idClassificacaoTributaria, LocalDate data) {
        return tipoDfeClassificacaoRepository.buscar(idClassificacaoTributaria, data);
    }

}
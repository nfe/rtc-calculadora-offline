/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.PercentualReducaoRepository;
import br.gov.serpro.rtc.domain.service.exception.PercentualReducaoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PercentualReducaoService {

    private final PercentualReducaoRepository percentualReducaoRepository;

    public BigDecimal buscar(Long idClassificacaoTributaria, Long idTributo, LocalDate data) {
        BigDecimal percentualReducao = percentualReducaoRepository.buscar(idClassificacaoTributaria, idTributo, data);
        if (percentualReducao == null) {
            throw new PercentualReducaoNaoEncontradoException(idClassificacaoTributaria, data);
        }
        return percentualReducao;
    }

}
/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.NbsAplicavelRepository;
import br.gov.serpro.rtc.domain.service.exception.NbsCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoVinculadaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NbsAplicavelService {

    private final NbsAplicavelRepository repository;

    @Cacheable(cacheNames = "NbsAplicavelService.validarNbsAplicavel",
            key = "#nbs + ':' + #idClassificacaoTributaria + ':' + #data")
    public boolean validarNbsAplicavel(String nbs, Long idClassificacaoTributaria, String codigoClassificacaoTributaria,
            LocalDate data, String tributos) {
        final boolean possuiNbsCompleto = StringUtils.length(nbs) == 9;
        final boolean temClassificacaoTributaria = repository.tem(idClassificacaoTributaria, data);
        if (temClassificacaoTributaria && !possuiNbsCompleto) {
            throw new NbsCompletoNaoInformadoException();
        }

        if (possuiNbsCompleto) {
            final boolean temNbsAplicavel = repository.temNbsAplicavel(nbs, idClassificacaoTributaria, data);
            final boolean temExcecaoNbsAplicavel = repository.temExcecaoNbsAplicavel(nbs, idClassificacaoTributaria, data);
            final boolean temNbsAplicavelSemExcecao = repository.temNbsAplicavelSemExcecao(nbs, idClassificacaoTributaria, data);

            if (temNbsAplicavel && temExcecaoNbsAplicavel && !temNbsAplicavelSemExcecao) {
                throw new NbsNaoVinculadaException(nbs, codigoClassificacaoTributaria, tributos);
            }

            if (temClassificacaoTributaria && !temNbsAplicavel && !temExcecaoNbsAplicavel && !temNbsAplicavelSemExcecao) {
                throw new NbsNaoVinculadaException(nbs, codigoClassificacaoTributaria, tributos);
            }
        }
        return true;
    }

}
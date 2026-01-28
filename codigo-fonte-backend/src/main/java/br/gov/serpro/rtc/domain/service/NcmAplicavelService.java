/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.NcmAplicavelRepository;
import br.gov.serpro.rtc.domain.service.exception.NcmCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoVinculadaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NcmAplicavelService {

    private final NcmAplicavelRepository repository;

    @Cacheable(cacheNames = "NcmAplicavelService.validarNcmAplicavel",
            key = "#ncm + ':' + #idClassificacaoTributaria + ':' + #data")
    public boolean validarNcmAplicavel(String ncm, Long idClassificacaoTributaria, String codigoClassificacaoTributaria,
            LocalDate data, String tributos) {

        final boolean possuiNcmCompleto = StringUtils.length(ncm) == 8;

        // sob demanda da plataforma
        final boolean temClassificacaoTributaria = repository.tem(idClassificacaoTributaria, data);
        
        if (temClassificacaoTributaria && !possuiNcmCompleto) {
            throw new NcmCompletoNaoInformadoException();
        }

        if (possuiNcmCompleto) {
            final boolean temNcmAplicavel = repository.temNcmAplicavel(ncm, idClassificacaoTributaria, data);
            final boolean temExcecaoNcmAplicavel = repository.temExcecaoNcmAplicavel(ncm, idClassificacaoTributaria, data);
            final boolean temNcmAplicavelSemExcecao = repository.temNcmAplicavelSemExcecao(ncm, idClassificacaoTributaria, data);

            if (temNcmAplicavel && temExcecaoNcmAplicavel && !temNcmAplicavelSemExcecao) {
                throw new NcmNaoVinculadaException(ncm, codigoClassificacaoTributaria, tributos);
            }

            if (temClassificacaoTributaria && !temNcmAplicavel && !temExcecaoNcmAplicavel && !temNcmAplicavelSemExcecao) {
                throw new NcmNaoVinculadaException(ncm, codigoClassificacaoTributaria, tributos);
            }
        }
        return true;
    }

}
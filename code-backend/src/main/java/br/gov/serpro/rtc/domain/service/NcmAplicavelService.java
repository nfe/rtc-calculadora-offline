/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.NcmAplicavelRepository;
import br.gov.serpro.rtc.domain.service.exception.NcmCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoVinculadaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NcmAplicavelService {

    private final NcmAplicavelRepository repository;

    public List<Object[]> temNcmAplicavel(String ncm, Long idClassificacaoTributaria, LocalDate data) {
        return repository.temNcmAplicavel(ncm, idClassificacaoTributaria, data);
    }

    public List<Object[]> temExcecaoNcmAplicavel(String ncm, Long idClassificacaoTributaria, LocalDate data) {
        return repository.temExcecaoNcmAplicavel(ncm, idClassificacaoTributaria, data);
    }

    public List<Object[]> temNcmAplicavelSemExcecao(String ncm, Long idClassificacaoTributaria, LocalDate data) {
        return repository.temNcmAplicavelSemExcecao(ncm, idClassificacaoTributaria, data);
    }

    public Integer tem(String ncm, Long idClassificacaoTributaria, String codigoClassificacaoTributaria,
            LocalDate data) {
        return repository.tem(ncm, idClassificacaoTributaria, data);
    }

    public void validarNcmAplicavel(String ncm, Long idClassificacaoTributaria, String codigoClassificacaoTributaria,
            LocalDate data, String tributos) {

        final boolean possuiNcmCompleto = StringUtils.length(ncm) == 8;

        // sob demanda da plataforma
        final boolean temClassificacaoTributaria = tem(ncm, idClassificacaoTributaria, codigoClassificacaoTributaria,
                data) == 1;
        
        if (temClassificacaoTributaria && !possuiNcmCompleto) {
            throw new NcmCompletoNaoInformadoException();
        }

        if (possuiNcmCompleto) {
            List<Object[]> results = temNcmAplicavel(ncm, idClassificacaoTributaria, data);
            List<Object[]> results1 = temExcecaoNcmAplicavel(ncm, idClassificacaoTributaria, data);
            List<Object[]> results2 = temNcmAplicavelSemExcecao(ncm, idClassificacaoTributaria, data);

            if (!results.isEmpty() && !results1.isEmpty() && results2.isEmpty()) {
                throw new NcmNaoVinculadaException(ncm, codigoClassificacaoTributaria, tributos);
            }

            if (temClassificacaoTributaria && results.isEmpty() && results1.isEmpty() && results2.isEmpty()) {
                throw new NcmNaoVinculadaException(ncm, codigoClassificacaoTributaria, tributos);
            }
        }
    }

}
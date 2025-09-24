/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.NbsAplicavelRepository;
import br.gov.serpro.rtc.domain.service.exception.NbsCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoVinculadaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NbsAplicavelService {

    private final NbsAplicavelRepository repository;

    public List<Object[]> temNbsAplicavel(String nbs, Long idClassificacaoTributaria, LocalDate data) {
        return repository.temNbsAplicavel(nbs, idClassificacaoTributaria, data);
    }

    public List<Object[]> temExcecaoNbsAplicavel(String nbs, Long idClassificacaoTributaria, LocalDate data) {
        return repository.temExcecaoNbsAplicavel(nbs, idClassificacaoTributaria, data);
    }

    public List<Object[]> temNbsAplicavelSemExcecao(String nbs, Long idClassificacaoTributaria, LocalDate data) {
        return repository.temNbsAplicavelSemExcecao(nbs, idClassificacaoTributaria, data);
    }

    public Integer tem(String nbs, Long idClassificacaoTributaria, String codigoClassificacaoTributaria,
            LocalDate data) {
        return repository.tem(nbs, idClassificacaoTributaria, data);
    }

    public void validarNbsAplicavel(String nbs, Long idClassificacaoTributaria, String codigoClassificacaoTributaria,
            LocalDate data, String tributos) {
        final boolean possuiNbsCompleto = StringUtils.length(nbs) == 9;
        final boolean temClassificacaoTributaria = tem(nbs, idClassificacaoTributaria, codigoClassificacaoTributaria,
                data) == 1;
        if (temClassificacaoTributaria && !possuiNbsCompleto) {
            throw new NbsCompletoNaoInformadoException();
        }

        if (possuiNbsCompleto) {
            List<Object[]> results = temNbsAplicavel(nbs, idClassificacaoTributaria, data);
            List<Object[]> results1 = temExcecaoNbsAplicavel(nbs, idClassificacaoTributaria, data);
            List<Object[]> results2 = temNbsAplicavelSemExcecao(nbs, idClassificacaoTributaria, data);

            if (!results.isEmpty() && !results1.isEmpty() && results2.isEmpty()) {
                throw new NbsNaoVinculadaException(nbs, codigoClassificacaoTributaria, tributos);
            }

            if (temClassificacaoTributaria && results.isEmpty() && results1.isEmpty() && results2.isEmpty()) {
                throw new NbsNaoVinculadaException(nbs, codigoClassificacaoTributaria, tributos);
            }
        }
    }

}
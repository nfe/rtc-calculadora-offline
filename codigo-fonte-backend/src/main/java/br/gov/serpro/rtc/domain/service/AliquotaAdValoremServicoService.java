/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.AliquotaAdValoremServicoRepository;
import br.gov.serpro.rtc.domain.service.exception.ErroGenericoValidacaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AliquotaAdValoremServicoService {

    private final AliquotaAdValoremServicoRepository repository;

    public BigDecimal buscarAliquotaAdValorem(String nbs, Long idTributo, Long idClassificacaoTributaria,
            LocalDate data) {
        try {
            return repository.buscarAliquotaAdValorem(nbs, idTributo, idClassificacaoTributaria, data);
        } catch (IncorrectResultSizeDataAccessException e) {
            final var msg = String.format("Múltiplos valores encontrados para a consulta de alíquota ad valorem "
                    + "para o NBS %s, o tributo %d e classificação tributária %d em %s", nbs, idTributo, idClassificacaoTributaria, data);
            log.error(msg, e);
            throw new ErroGenericoValidacaoException(
                    "Consulta retornou múltiplos valores quando apenas um era esperado.");
        }
    }

    public BigDecimal buscarAliquotaAdValoremPorClassificacaoTributaria(Long idTributo, Long idClassificacaoTributaria,
            LocalDate data) {
        try {
            return repository.buscarAliquotaAdValoremPorClassificacaoTributaria(idTributo, idClassificacaoTributaria,
                    data);
        } catch (IncorrectResultSizeDataAccessException e) {
            final var msg = String.format("Múltiplos valores encontrados para a consulta de alíquota ad valorem "
                    + "para o tributo %d e classificação tributária %d em %s", idTributo, idClassificacaoTributaria, data);
            log.error(msg, e);
            throw new ErroGenericoValidacaoException(
                    "Consulta retornou múltiplos valores quando apenas um era esperado.");
        }
    }

}
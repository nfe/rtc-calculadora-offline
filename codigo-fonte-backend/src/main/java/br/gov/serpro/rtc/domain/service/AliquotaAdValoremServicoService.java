/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.AliquotaAdValoremServicoRepository;
import br.gov.serpro.rtc.domain.service.exception.ErroGenericoValidacaoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaAdValoremServicoService {

    private final AliquotaAdValoremServicoRepository repository;

    public BigDecimal buscarAliquotaAdValorem(String nbs, Long idTributo, Long idClassificacaoTributaria, LocalDate data) {
        List<BigDecimal> aliquotas = repository.buscarAliquotaAdValorem(nbs, idTributo, idClassificacaoTributaria, data);
        if (aliquotas.isEmpty()) {
            // Nenhuma alíquota encontrada
            return null;
        } else if (aliquotas.size() == 1) {
            // Uma única alíquota encontrada
            return aliquotas.get(0);
        } else {
            // Múltiplas alíquotas encontradas
            throw new ErroGenericoValidacaoException("Múltiplas alíquotas encontradas");
        }
    }

    public BigDecimal buscarAliquotaAdValoremPorClassificacaoTributaria(Long idTributo, Long idClassificacaoTributaria,
            LocalDate data) {
        List<BigDecimal> aliquotas = repository.buscarAliquotaAdValoremPorClassificacaoTributaria(idTributo,
                idClassificacaoTributaria, data);
        if (aliquotas.isEmpty()) {
            // Nenhuma alíquota encontrada
            return null;
        } else if (aliquotas.size() == 1) {
            // Uma única alíquota encontrada
            return aliquotas.get(0);
        } else {
            // Múltiplas alíquotas encontradas
            throw new ErroGenericoValidacaoException("Múltiplas alíquotas encontradas");
        }
    }

}
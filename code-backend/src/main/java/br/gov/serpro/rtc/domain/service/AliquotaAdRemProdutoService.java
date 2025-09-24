/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.AliquotaAdRemProdutoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AliquotaAdRemProdutoService {

    private final AliquotaAdRemProdutoRepository repository;

    public BigDecimal buscarAliquotaAdRem(String ncm, Long idTributo, LocalDate data) {
        return repository.buscarAliquotaAdRem(ncm, idTributo, data)
                .orElse(null);
    }

}
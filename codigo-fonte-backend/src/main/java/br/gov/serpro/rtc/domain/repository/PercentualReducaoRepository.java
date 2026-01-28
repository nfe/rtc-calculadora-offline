/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.PercentualReducao;

@Repository
public interface PercentualReducaoRepository extends JpaRepository<PercentualReducao, Long> {

    /*
     * Entradas recomendadas na cache: 1.600
     * Memória estimada: ~216 KB
     */
    @Query("""
            SELECT valor
            FROM PercentualReducao
            WHERE classificacaoTributaria.id = :idClassificacaoTributaria
            AND tributo.id = :idTributo
            AND (inicioVigencia <= :data AND (fimVigencia IS NULL OR fimVigencia >= :data))
            """)
    @Cacheable("PercentualReducaoRepository.buscar")
    BigDecimal buscar(Long idClassificacaoTributaria, Long idTributo, LocalDate data);

}

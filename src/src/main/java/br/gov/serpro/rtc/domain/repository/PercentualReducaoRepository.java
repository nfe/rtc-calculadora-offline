/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.PercentualReducao;

@Repository
public interface PercentualReducaoRepository extends JpaRepository<PercentualReducao, Long> {

    @Query("""
            FROM PercentualReducao
            WHERE classificacaoTributaria.id = :idClassificacaoTributaria
            AND tributo.id = :idTributo
            AND (inicioVigencia <= :data AND (fimVigencia IS NULL OR fimVigencia >= :data))
            """)
    @Cacheable("PercentualReducaoRepository.buscar")
    PercentualReducao buscar(Long idClassificacaoTributaria, Long idTributo, LocalDate data);

}

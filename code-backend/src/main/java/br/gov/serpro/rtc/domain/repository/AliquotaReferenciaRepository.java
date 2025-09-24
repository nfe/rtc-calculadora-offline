/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.AliquotaReferencia;

@Repository
public interface AliquotaReferenciaRepository extends JpaRepository<AliquotaReferencia, Long> {

    @Query("""
        FROM AliquotaReferencia ar
        WHERE ar.tributo.id = :idTributo
        AND :data BETWEEN ar.inicioVigencia AND COALESCE(ar.fimVigencia, :data)
    """)
    @Cacheable(cacheNames = "AliquotaReferenciaRepository.buscar")
    Optional<AliquotaReferencia> buscar(
        @Param("idTributo") Long idTributo,
        @Param("data") LocalDate data
    );

}

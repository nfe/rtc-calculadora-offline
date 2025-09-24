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

import br.gov.serpro.rtc.domain.model.entity.AliquotaPadrao;
import br.gov.serpro.rtc.domain.model.entity.AliquotaReferencia;

@Repository
public interface AliquotaPadraoRepository extends JpaRepository<AliquotaPadrao, Long> {

    @Query("""
        FROM AliquotaPadrao ap
        WHERE ap.aliquotaReferencia = :aliquotaReferencia
          AND :data BETWEEN ap.inicioVigencia AND COALESCE(ap.fimVigencia, :data)
          AND ap.municipio IS NULL
          AND ap.uf IS NULL
    """)
    @Cacheable(cacheNames = "AliquotaPadraoRepository.buscarAliquotaUniao", key = "#aliquotaReferencia.getId() + ':' + #data")
    Optional<AliquotaPadrao> buscarAliquotaUniao(
        @Param("aliquotaReferencia") AliquotaReferencia aliquotaReferencia,
        @Param("data") LocalDate data
    );

    @Query("""
        FROM AliquotaPadrao ap
        WHERE ap.aliquotaReferencia = :aliquotaReferencia
          AND :data BETWEEN ap.inicioVigencia AND COALESCE(ap.fimVigencia, :data)
          AND ap.municipio IS NULL
          AND ap.uf.codigo = :codigoUf
    """)
    @Cacheable(cacheNames = "AliquotaPadraoRepository.buscarAliquotaUf", key = "#aliquotaReferencia.getId() + ':'+ #codigoUf +':' + #data")
    Optional<AliquotaPadrao> buscarAliquotaUf(
        @Param("aliquotaReferencia") AliquotaReferencia aliquotaReferencia,
        @Param("codigoUf") Long codigoUf,
        @Param("data") LocalDate data
    );

    @Query("""
        FROM AliquotaPadrao ap
        WHERE ap.aliquotaReferencia = :aliquotaReferencia
          AND :data BETWEEN ap.inicioVigencia AND COALESCE(ap.fimVigencia, :data)
          AND ap.municipio.codigo = :municipio
          AND ap.uf IS NULL
    """)
    @Cacheable(cacheNames = "AliquotaPadraoRepository.buscarAliquotaMunicipio", key = "#aliquotaReferencia.getId() + ':'+ #municipio +':' + #data")
    Optional<AliquotaPadrao> buscarAliquotaMunicipio(
        @Param("aliquotaReferencia") AliquotaReferencia aliquotaReferencia,
        @Param("municipio") Long municipio,
        @Param("data") LocalDate data
    );

}

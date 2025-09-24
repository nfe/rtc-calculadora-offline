/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.NbsAplicavel;

@Repository
public interface NbsAplicavelRepository extends JpaRepository<NbsAplicavel, Long> {  

  @Query("""
          SELECT n.nbs, n.classificacaoTributaria.id, n.id
          FROM NbsAplicavel n
          WHERE n.nbs = SUBSTRING(:nbs, 1, LENGTH(n.nbs))
            AND n.classificacaoTributaria.id = :idClassificacaoTributaria
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
      """)
  @Cacheable(cacheNames = "NbsAplicavelRepository.temNbsAplicavel")
  List<Object[]> temNbsAplicavel(
      @Param("nbs") String nbs,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);

  @Query("""
                  SELECT e.nbs, n.classificacaoTributaria.id, e.nbsAplicavel.id
                  FROM ExcecaoNbsAplicavel e
                  JOIN e.nbsAplicavel n
                  WHERE n.nbs = SUBSTRING(:nbs, 1, LENGTH(n.nbs))
                  AND e.nbs = SUBSTRING(:nbs, 1, LENGTH(e.nbs))
                  AND n.classificacaoTributaria.id = :idClassificacaoTributaria
                  AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
      """)
  @Cacheable(cacheNames = "NbsAplicavelRepository.temExcecaoNbsAplicavel")
  List<Object[]> temExcecaoNbsAplicavel(
      @Param("nbs") String nbs,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);

  @Query("""
          SELECT n.nbs, n.classificacaoTributaria.id, n.id
          FROM NbsAplicavel n
          LEFT JOIN ExcecaoNbsAplicavel e ON e.nbsAplicavel.id = n.id
          WHERE n.nbs = SUBSTRING(:nbs, 1, LENGTH(n.nbs))
            AND n.classificacaoTributaria.id = :idClassificacaoTributaria
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
            AND (e.id IS NULL OR :data NOT BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data))
      """)
  @Cacheable(cacheNames = "NbsAplicavelRepository.temNbsAplicavelSemExcecao")
  List<Object[]> temNbsAplicavelSemExcecao(
      @Param("nbs") String nbs,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);

      @Query("""
        SELECT CASE
        WHEN NOT EXISTS (
        SELECT 1
        FROM NbsAplicavel n
        WHERE n.classificacaoTributaria.id = :idClassificacaoTributaria
        ) THEN 0
        ELSE 1
        END
    """)
      @Cacheable(cacheNames = "NbsAplicavelRepository.tem")
Integer tem(
    @Param("nbs") String nbs,
    @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
    @Param("data") LocalDate data);

}

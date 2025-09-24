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

import br.gov.serpro.rtc.domain.model.entity.NcmAplicavel;

@Repository
public interface NcmAplicavelRepository extends JpaRepository<NcmAplicavel, Long> {   

  @Query("""
          SELECT n.ncm, n.classificacaoTributaria.id, n.id
          FROM NcmAplicavel n
          WHERE n.ncm = SUBSTRING(:ncm, 1, LENGTH(n.ncm))
            AND n.classificacaoTributaria.id = :idClassificacaoTributaria
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
      """)
  @Cacheable(cacheNames = "NcmAplicavelRepository.temNcmAplicavel")
  List<Object[]> temNcmAplicavel(
      @Param("ncm") String ncm,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);

  @Query("""
                  SELECT e.ncm, n.classificacaoTributaria.id, e.ncmAplicavel.id
                  FROM ExcecaoNcmAplicavel e
                  JOIN e.ncmAplicavel n
                  WHERE n.ncm = SUBSTRING(:ncm, 1, LENGTH(n.ncm))
                  AND e.ncm = SUBSTRING(:ncm, 1, LENGTH(e.ncm))
                  AND n.classificacaoTributaria.id = :idClassificacaoTributaria
                  AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
      """)
  @Cacheable(cacheNames = "NcmAplicavelRepository.temExcecaoNcmAplicavel")
  List<Object[]> temExcecaoNcmAplicavel(
      @Param("ncm") String ncm,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);

  @Query("""
          SELECT n.ncm, n.classificacaoTributaria.id, n.id
          FROM NcmAplicavel n
          LEFT JOIN ExcecaoNcmAplicavel e ON e.ncmAplicavel.id = n.id
          WHERE n.ncm = SUBSTRING(:ncm, 1, LENGTH(n.ncm))
            AND n.classificacaoTributaria.id = :idClassificacaoTributaria
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
            AND (e.id IS NULL OR :data NOT BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data))
      """)
  @Cacheable(cacheNames = "NcmAplicavelRepository.temNcmAplicavelSemExcecao")
  List<Object[]> temNcmAplicavelSemExcecao(
      @Param("ncm") String ncm,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);

  @Query("""
          SELECT CASE
          WHEN NOT EXISTS (
          SELECT 1
          FROM NcmAplicavel n
          WHERE n.classificacaoTributaria.id = :idClassificacaoTributaria
          ) THEN 0
          WHEN EXISTS (
          SELECT 1
          FROM NcmAplicavel n
          LEFT JOIN ExcecaoNcmAplicavel e ON e.ncmAplicavel.id = n.id
          WHERE n.ncm = SUBSTRING(:ncm, 1, LENGTH(n.ncm))
            AND n.classificacaoTributaria.id = :idClassificacaoTributaria
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
            AND (e.id IS NULL OR :data NOT BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data))
          ) THEN 0
          ELSE 1
          END
      """)
  Integer temRestricao(
      @Param("ncm") String ncm,
      @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
      @Param("data") LocalDate data);


      @Query("""
        SELECT CASE
        WHEN NOT EXISTS (
        SELECT 1
        FROM NcmAplicavel n
        WHERE n.classificacaoTributaria.id = :idClassificacaoTributaria
        ) THEN 0
        ELSE 1
        END
    """)
      @Cacheable(cacheNames = "NcmAplicavelRepository.tem")
Integer tem(
    @Param("ncm") String ncm,
    @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
    @Param("data") LocalDate data);

}

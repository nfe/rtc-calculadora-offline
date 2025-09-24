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

import br.gov.serpro.rtc.domain.model.entity.SituacaoTributaria;

@Repository
public interface SituacaoTributariaRepository extends JpaRepository<SituacaoTributaria, Long> {

	@Query("""
			SELECT t.situacaoTributaria
			FROM TributoSituacaoTributaria t
			WHERE t.tributo.id = :idTributo
			AND t.inicioVigencia <= :data
			AND (t.fimVigencia IS NULL OR t.fimVigencia >= :data)
			""")
	List<SituacaoTributaria> consultarPorIdTributo(
			@Param("idTributo") Long idTributo,
			@Param("data") LocalDate data);

	@Query("""
			SELECT COUNT(t) > 0
			FROM TributoSituacaoTributaria t
			WHERE t.tributo.id = :idTributo
			AND t.situacaoTributaria.codigo = :cst
			AND :data BETWEEN t.inicioVigencia AND COALESCE(t.fimVigencia, :data)
			AND :data BETWEEN t.situacaoTributaria.inicioVigencia AND COALESCE(t.situacaoTributaria.fimVigencia, :data)
			""")
	@Cacheable(cacheNames = "SituacaoTributariaRepository.existeCst")
	boolean existeCst(
			@Param("cst") String cst,
			@Param("idTributo") Long idTributo,
			@Param("data") LocalDate data);

}

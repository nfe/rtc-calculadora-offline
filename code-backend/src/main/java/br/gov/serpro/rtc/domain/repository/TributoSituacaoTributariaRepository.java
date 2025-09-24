/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.TributoSituacaoTributaria;

@Repository
public interface TributoSituacaoTributariaRepository extends JpaRepository<TributoSituacaoTributaria, Long> {

    @Query(value = """
        SELECT t.TRST_TBTO_ID
        FROM TRIBUTO_SITUACAO_TRIBUTARIA t
        JOIN SITUACAO_TRIBUTARIA s ON t.TRST_SITR_ID = s.SITR_ID
        WHERE t.TRST_SITR_ID = :idSituacaoTributaria
          AND :data BETWEEN t.TRST_INICIO_VIGENCIA AND COALESCE(t.TRST_FIM_VIGENCIA, :data)
        LIMIT 1
        """, nativeQuery = true)
    Long consultarPorIdSituacaoTributaria(
            @Param("idSituacaoTributaria") Long idSituacaoTributaria,
            @Param("data") LocalDate data);
}

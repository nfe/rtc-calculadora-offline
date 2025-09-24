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

import br.gov.serpro.rtc.domain.model.entity.ClassificacaoTributaria;

@Repository
public interface ClassificacaoTributariaRepository extends JpaRepository<ClassificacaoTributaria, Long> {

    @Query(value = """
            SELECT *
            FROM CLASSIFICACAO_TRIBUTARIA ct
            JOIN SITUACAO_TRIBUTARIA st ON ct.CLTR_SITR_ID = st.SITR_ID
            JOIN TRIBUTO_SITUACAO_TRIBUTARIA tst ON tst.TRST_SITR_ID = st.SITR_ID
            JOIN TRIBUTO tb ON tst.TRST_TBTO_ID = tb.TBTO_ID
            WHERE ct.CLTR_CD = :codigo
            AND tb.TBTO_ID = 2
            AND :data BETWEEN ct.CLTR_INICIO_VIGENCIA AND COALESCE(ct.CLTR_FIM_VIGENCIA, :data)
            AND :data BETWEEN st.SITR_INICIO_VIGENCIA AND COALESCE(st.SITR_FIM_VIGENCIA, :data)
            AND :data BETWEEN tst.TRST_INICIO_VIGENCIA AND COALESCE(tst.TRST_FIM_VIGENCIA, :data)
            AND :data BETWEEN tb.TBTO_INICIO_VIGENCIA AND COALESCE(tb.TBTO_FIM_VIGENCIA, :data)
            """, nativeQuery = true)
    @Cacheable(cacheNames = "ClassificacaoTributariaRepository.buscarClassificacaoTributariaCbsIbs")
    Optional<ClassificacaoTributaria> buscarClassificacaoTributariaCbsIbs(
            @Param("codigo") String codigo,
            @Param("data") LocalDate data);

    @Query(value = """
            SELECT *
            FROM CLASSIFICACAO_TRIBUTARIA ct
            JOIN SITUACAO_TRIBUTARIA st ON ct.CLTR_SITR_ID = st.SITR_ID
            JOIN TRIBUTO_SITUACAO_TRIBUTARIA tst ON tst.TRST_SITR_ID = st.SITR_ID
            JOIN TRIBUTO tb ON tst.TRST_TBTO_ID = tb.TBTO_ID
            WHERE ct.CLTR_CD = :codigo
            AND tb.TBTO_ID = 1
            AND :data BETWEEN ct.CLTR_INICIO_VIGENCIA AND COALESCE(ct.CLTR_FIM_VIGENCIA, :data)
            AND :data BETWEEN st.SITR_INICIO_VIGENCIA AND COALESCE(st.SITR_FIM_VIGENCIA, :data)
            AND :data BETWEEN tst.TRST_INICIO_VIGENCIA AND COALESCE(tst.TRST_FIM_VIGENCIA, :data)
            AND :data BETWEEN tb.TBTO_INICIO_VIGENCIA AND COALESCE(tb.TBTO_FIM_VIGENCIA, :data)
            """, nativeQuery = true)
    @Cacheable(cacheNames = "ClassificacaoTributariaRepository.buscarClassificacaoTributariaImpostoSeletivo")
    Optional<ClassificacaoTributaria> buscarClassificacaoTributariaImpostoSeletivo(
            @Param("codigo") String codigo,
            @Param("data") LocalDate data);

}

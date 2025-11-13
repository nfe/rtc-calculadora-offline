/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.AliquotaAdValoremServico;

@Repository
public interface AliquotaAdValoremServicoRepository extends JpaRepository<AliquotaAdValoremServico, Long> {

    @Query("""
            SELECT a.aliquotaAdValorem.valor
            FROM AliquotaAdValoremServico a
            WHERE EXISTS (
                SELECT 1
                FROM Nbs n
                WHERE n.codigo = :nbs
            )
            AND a.nbs.codigo = SUBSTRING(:nbs, 1, LENGTH(a.nbs.codigo))
            AND a.aliquotaAdValorem.tributo.id = :idTributo
            AND (:idClassificacaoTributaria IS NULL OR a.aliquotaAdValorem.classificacaoTributaria.id = :idClassificacaoTributaria)
            AND :data BETWEEN a.inicioVigencia AND COALESCE(a.fimVigencia, :data)
            AND NOT EXISTS (
                SELECT 1
                FROM ExcecaoAdValoremServico e
                WHERE e.nbs.codigo = SUBSTRING(:nbs, 1, LENGTH(e.nbs.codigo))
                AND e.aliquotaAdValoremServico.id = a.id
                AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
            )
            AND NOT EXISTS (
                SELECT 1
                FROM ExcecaoAdValoremServico e
                WHERE e.nbs.codigo = SUBSTRING(a.nbs.codigo, 1, LENGTH(e.nbs.codigo))
                AND e.aliquotaAdValoremServico.id = a.id
                AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
            )
            ORDER BY LENGTH(a.nbs.codigo) DESC
            """)
    List<BigDecimal> buscarAliquotaAdValorem(
            @Param("nbs") String nbs,
            @Param("idTributo") Long idTributo,
            @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
            @Param("data") LocalDate data);

    @Query(value = """
            SELECT aadv.AADV_VALOR 
            FROM ALIQUOTA_AD_VALOREM aadv 
            WHERE aadv.AADV_TBTO_ID = :idTributo 
            AND aadv.AADV_CLTR_ID = :idClassificacaoTributaria 
            AND :data BETWEEN aadv.AADV_INICIO_VIGENCIA AND COALESCE(aadv.AADV_FIM_VIGENCIA, :data)
            """, nativeQuery = true)
    List<BigDecimal> buscarAliquotaAdValoremPorClassificacaoTributaria(
            @Param("idTributo") Long idTributo,
            @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
            @Param("data") LocalDate data);

}

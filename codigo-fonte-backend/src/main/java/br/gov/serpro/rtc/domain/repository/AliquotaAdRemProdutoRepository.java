/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.AliquotaAdRemProduto;

@Repository
public interface AliquotaAdRemProdutoRepository extends JpaRepository<AliquotaAdRemProduto, Long> {

    @Query("""
            SELECT a.aliquotaAdRem.valor
            FROM AliquotaAdRemProduto a
            WHERE EXISTS (
                SELECT 1
                FROM Ncm n
                WHERE n.codigo = :ncm
            )
            AND a.aliquotaAdRem.tributo.id = :idTributo
            AND a.ncm.codigo = SUBSTRING(:ncm, 1, LENGTH(a.ncm.codigo))
            AND :data BETWEEN a.inicioVigencia AND COALESCE(a.fimVigencia, :data)
            AND NOT EXISTS (
                SELECT 1
                FROM ExcecaoAdRemProduto e
                WHERE e.ncm.codigo = SUBSTRING(:ncm, 1, LENGTH(e.ncm.codigo))
                AND e.aliquotaAdRemProduto.id = a.id
                AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
            )
            AND NOT EXISTS (
                SELECT 1
                FROM ExcecaoAdRemProduto e
                WHERE e.ncm.codigo = SUBSTRING(a.ncm.codigo, 1, LENGTH(e.ncm.codigo))
                AND e.aliquotaAdRemProduto.id = a.id
                AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
            )
            ORDER BY LENGTH(a.ncm.codigo) DESC
            LIMIT 1
            """)
    @Cacheable(cacheNames = "AliquotaAdRemProdutoRepository.buscarAliquotaAdRem")
    Optional<BigDecimal> buscarAliquotaAdRem(
            @Param("ncm") String ncm,
            @Param("idTributo") Long idTributo,
            @Param("data") LocalDate data);

}

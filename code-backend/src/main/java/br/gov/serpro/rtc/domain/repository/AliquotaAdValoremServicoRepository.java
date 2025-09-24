/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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
            LIMIT 1
            """)
    Optional<BigDecimal> buscarAliquotaAdValorem(
            @Param("nbs") String nbs,
            @Param("idTributo") Long idTributo,
            @Param("data") LocalDate data);

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.dto.AliquotaResultadoDTO;
import br.gov.serpro.rtc.domain.model.entity.AliquotaPadrao;

@Repository
public interface AliquotaPadraoRepository extends JpaRepository<AliquotaPadrao, Long> {

    @Query("""
            SELECT new br.gov.serpro.rtc.domain.model.dto.AliquotaResultadoDTO(
                r.valor, 
                p.valor, 
                p.formaAplicacao
            )
            FROM AliquotaReferencia r
            LEFT JOIN AliquotaPadrao p
                ON p.aliquotaReferencia.id = r.id
                AND (
                    (:municipio IS NULL AND p.municipio IS NULL) OR
                    (:municipio IS NOT NULL AND p.municipio.codigo = :municipio)
                )
                AND (
                    (:codigoUf IS NULL AND p.uf.codigo IS NULL) OR
                    (:codigoUf IS NOT NULL AND p.uf.codigo = :codigoUf)
                )
                AND p.inicioVigencia <= :data
                AND (p.fimVigencia IS NULL OR p.fimVigencia >= :data)
            WHERE r.tributo.id = :idTributo
                AND r.inicioVigencia <= :data
                AND (r.fimVigencia IS NULL OR r.fimVigencia >= :data)
        """)
    @Cacheable(cacheNames = "AliquotaPadraoRepository.buscarAliquota")
    AliquotaResultadoDTO buscarAliquota(
            @Param("idTributo") long idTributo,
            @Param("codigoUf") Long codigoUf, 
            @Param("municipio") Long municipio, 
            @Param("data") LocalDate data);

}

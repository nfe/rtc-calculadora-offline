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

import br.gov.serpro.rtc.domain.model.entity.NbsAplicavel;

@Repository
public interface NbsAplicavelRepository extends JpaRepository<NbsAplicavel, Long> {

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM NbsAplicavel n
                WHERE n.nbs = SUBSTRING(:nbs, 1, LENGTH(n.nbs))
                AND n.classificacaoTributaria.id = :idClassificacaoTributaria
                AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
            )
            """)
    // FIXME essa cache pode ser removida pois o serviço que a utiliza já faz cache
    @Cacheable(cacheNames = "NbsAplicavelRepository.temNbsAplicavel")
    boolean temNbsAplicavel(
            @Param("nbs") String nbs,
            @Param("idClassificacaoTributaria") Long idClassificacaoTributaria, 
            @Param("data") LocalDate data);

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM ExcecaoNbsAplicavel e
                JOIN e.nbsAplicavel n
                WHERE n.nbs = SUBSTRING(:nbs, 1, LENGTH(n.nbs))
                AND e.nbs = SUBSTRING(:nbs, 1, LENGTH(e.nbs))
                AND n.classificacaoTributaria.id = :idClassificacaoTributaria
                AND :data BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data)
            )
            """)
    // FIXME essa cache pode ser removida pois o serviço que a utiliza já faz cache
    @Cacheable(cacheNames = "NbsAplicavelRepository.temExcecaoNbsAplicavel")
    boolean temExcecaoNbsAplicavel(
            @Param("nbs") String nbs,
            @Param("idClassificacaoTributaria") Long idClassificacaoTributaria, 
            @Param("data") LocalDate data);

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM NbsAplicavel n
                LEFT JOIN ExcecaoNbsAplicavel e ON e.nbsAplicavel.id = n.id
                WHERE n.nbs = SUBSTRING(:nbs, 1, LENGTH(n.nbs))
                AND n.classificacaoTributaria.id = :idClassificacaoTributaria
                AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
                AND (e.id IS NULL OR :data NOT BETWEEN e.inicioVigencia AND COALESCE(e.fimVigencia, :data))
            )
            """)
    // FIXME essa cache pode ser removida pois o serviço que a utiliza já faz cache
    @Cacheable(cacheNames = "NbsAplicavelRepository.temNbsAplicavelSemExcecao")
    boolean temNbsAplicavelSemExcecao(
            @Param("nbs") String nbs,
            @Param("idClassificacaoTributaria") Long idClassificacaoTributaria, 
            @Param("data") LocalDate data);

    /*
     * Entradas recomendadas na cache: 400
     * Memória estimada: ~45 KB
     */
    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM NbsAplicavel n
                WHERE n.classificacaoTributaria.id = :idClassificacaoTributaria)
            """)
    // FIXME essa cache pode ser removida pois o serviço que a utiliza já faz cache
    @Cacheable(cacheNames = "NbsAplicavelRepository.tem")
    boolean tem(
        @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
        @Param("data") LocalDate data);

}

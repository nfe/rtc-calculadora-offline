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

import br.gov.serpro.rtc.domain.model.entity.Nbs;

@Repository
public interface NbsRepository extends JpaRepository<Nbs, String> {

    @Query("""
            SELECT n.descricao
            FROM Nbs n
            WHERE n.codigo = SUBSTRING(:codigo, 1, :limite)
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
            """)
    Optional<String> buscarDescricaoNbs(
            @Param("codigo") String codigo,
            @Param("limite") int limite,
            @Param("data") LocalDate data);

    @Query("""
            SELECT COUNT(n) > 0
            FROM Nbs n
            WHERE n.codigo = :codigo
            AND :data BETWEEN n.inicioVigencia AND COALESCE(n.fimVigencia, :data)
            """)
    @Cacheable(cacheNames = "NbsRepository.existeNbs")
    boolean existeNbs(
            @Param("codigo") String codigo,
            @Param("data") LocalDate data);

}

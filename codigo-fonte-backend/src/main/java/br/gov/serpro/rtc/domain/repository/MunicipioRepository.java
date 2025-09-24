/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.serpro.rtc.domain.model.entity.Municipio;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    @Query("""
            SELECT COUNT(m) > 0
            FROM Municipio m
            WHERE m.codigo = :codigo AND LOWER(m.uf.sigla) = LOWER(:uf)
            """)
    boolean existe(
            @Param("codigo") Long codigo,
            @Param("uf") String uf);

    @Query("FROM Municipio m WHERE LOWER(m.uf.sigla) = LOWER(:uf)")
    List<Municipio> consultarTodosPorSiglaUf(
            @Param("uf") String uf);

    @Query("""
            SELECT 
            CASE 
                WHEN COUNT(m) = 0 THEN 1
                WHEN COUNT(m) > 0 AND LOWER(m.uf.sigla) != LOWER(:siglaUf) THEN 2
                ELSE 0
            END
            FROM Municipio m
            WHERE m.codigo = :codigoMunicipio
            """)
    int validarMunicipio(
            @Param("codigoMunicipio") Long codigoMunicipio,
            @Param("siglaUf") String siglaUf);

}

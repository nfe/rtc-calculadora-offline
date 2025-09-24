/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

    @Query("FROM Uf u WHERE LOWER(u.sigla) = LOWER(:sigla)")
    @Cacheable(cacheNames = "UfRepository.consultarPorSigla")
    Optional<Uf> consultarPorSigla(String sigla);

    @Query("SELECT COUNT(u) > 0 FROM Uf u WHERE LOWER(u.sigla) = LOWER(:sigla)")
    @Cacheable(cacheNames = "UfRepository.existeUf")
    boolean existeUf(String sigla);

}

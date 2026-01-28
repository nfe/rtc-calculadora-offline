/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

    @Query("SELECT u.codigo FROM Uf u WHERE u.sigla = UPPER(:sigla)")
    @Cacheable(cacheNames = "UfRepository.consultarPorSigla")
    Long consultarPorSigla(String sigla);

    @Query("SELECT EXISTS (SELECT 1 FROM Uf u WHERE u.sigla = UPPER(:sigla))")
    @Cacheable(cacheNames = "UfRepository.existeUf")
    boolean existeUf(String sigla);

}

/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.VersaoBaseDados;

@Repository
public interface VersaoBaseDadosRepository extends JpaRepository<VersaoBaseDados, Long> {
    Optional<VersaoBaseDados> findTopByOrderByIdDesc();    
}

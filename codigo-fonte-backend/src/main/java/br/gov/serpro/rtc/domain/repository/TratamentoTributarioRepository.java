/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.entity.TratamentoTributario;

@Repository
public interface TratamentoTributarioRepository extends JpaRepository<TratamentoTributario, Long> {
}

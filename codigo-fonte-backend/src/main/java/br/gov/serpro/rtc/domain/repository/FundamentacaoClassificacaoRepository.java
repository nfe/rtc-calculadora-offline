/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.dto.FundamentacaoClassificacaoDTO;
import br.gov.serpro.rtc.domain.model.entity.FundamentacaoClassificacao;

@Repository
public interface FundamentacaoClassificacaoRepository extends JpaRepository<FundamentacaoClassificacao, Long> {

    /*
     * Entradas recomendadas na cache: 400
     * Memória estimada: ~527 KB
     */
    @Query("""
            SELECT new br.gov.serpro.rtc.domain.model.dto.FundamentacaoClassificacaoDTO(
                fc.fundamentacaoLegal.textoCurto,
                fc.classificacaoTributaria.memoriaCalculo)
            FROM FundamentacaoClassificacao fc
            WHERE fc.classificacaoTributaria.id = :idClassificacaoTributaria
                AND :data BETWEEN fc.inicioVigencia AND COALESCE(fc.fimVigencia, :data)
            """)
    @Cacheable("FundamentacaoClassificacaoRepository.buscar")
    FundamentacaoClassificacaoDTO buscar(
            @Param("idClassificacaoTributaria") Long idClassificacaoTributaria,
            @Param("data") LocalDate data);

    @Query("""
            FROM FundamentacaoClassificacao fc
            WHERE :data BETWEEN fc.inicioVigencia AND COALESCE(fc.fimVigencia, :data)
            """)
    List<FundamentacaoClassificacao> buscarTodas(
            @Param("data") LocalDate data);

}

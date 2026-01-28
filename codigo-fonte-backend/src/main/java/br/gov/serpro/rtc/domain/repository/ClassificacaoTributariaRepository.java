/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.repository;

import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.serpro.rtc.domain.model.dto.ClassificacaoTributariaCalculoDTO;
import br.gov.serpro.rtc.domain.model.dto.ClassificacaoTributariaDTO;
import br.gov.serpro.rtc.domain.model.entity.ClassificacaoTributaria;

@Repository
public interface ClassificacaoTributariaRepository extends JpaRepository<ClassificacaoTributaria, Long> {
    
    /*
     * Entradas recomendadas na cache: 1.600
     * Memória estimada: ~384 KB
     */
    @NativeQuery(value = """
            SELECT ct.CLTR_ID as id, ct.CLTR_CD as codigo, ct.CLTR_NOMENCLATURA as nomenclatura, st.SITR_CD as cst
            FROM CLASSIFICACAO_TRIBUTARIA ct
            JOIN SITUACAO_TRIBUTARIA st ON ct.CLTR_SITR_ID = st.SITR_ID
            JOIN TRIBUTO_SITUACAO_TRIBUTARIA tst ON tst.TRST_SITR_ID = st.SITR_ID
            JOIN TRIBUTO tb ON tst.TRST_TBTO_ID = tb.TBTO_ID
            WHERE ct.CLTR_CD = :codigo
            AND tb.TBTO_ID = :idTributo
            AND :data BETWEEN ct.CLTR_INICIO_VIGENCIA AND COALESCE(ct.CLTR_FIM_VIGENCIA, :data)
            AND :data BETWEEN st.SITR_INICIO_VIGENCIA AND COALESCE(st.SITR_FIM_VIGENCIA, :data)
            AND :data BETWEEN tst.TRST_INICIO_VIGENCIA AND COALESCE(tst.TRST_FIM_VIGENCIA, :data)
            AND :data BETWEEN tb.TBTO_INICIO_VIGENCIA AND COALESCE(tb.TBTO_FIM_VIGENCIA, :data)
            """, 
            sqlResultSetMapping = "ClassificacaoTributariaDTOMapping")
    @Cacheable(cacheNames = "ClassificacaoTributariaRepository.buscarClassificacaoTributaria")
    ClassificacaoTributariaDTO buscarClassificacaoTributaria(
            @Param("codigo") String codigo,
            @Param("idTributo") long idTributo,
            @Param("data") LocalDate data);

    @Query(value = """
            SELECT new br.gov.serpro.rtc.domain.model.dto.ClassificacaoTributariaCalculoDTO(
                    ct.id, 
                    ct.codigo, 
                    ct.tipoAliquota, 
                    ct.inGrupoMonofasiaPadrao, 
                    ct.inGrupoMonofasiaReten, 
                    ct.inGrupoMonofasiaRet,
                    ct.inGrupoMonofasiaDiferimento,
                    ct.situacaoTributaria.inGrupoReducao
            )
            FROM ClassificacaoTributaria ct
            WHERE ct.id = :id
            """)
    @Cacheable(cacheNames = "ClassificacaoTributariaRepository.buscarClassificacaoTributariaCalculo")
    ClassificacaoTributariaCalculoDTO buscarClassificacaoTributariaCalculo(
            @Param("id") long id);

}

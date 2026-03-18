-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2026-01-30
-- Autor: José Luiz Limeira
-- =====================================================

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('2026-01-30'),
    'V0029',
    'Nova tabela de Classificações Tributárias - Parte 2: Atualização de cClassTribs existentes.'
);

-------------------------------------------------------------------------------------------------
-- Descrição:
-- Atualiza descrição na tabela SITUACAO_TRIBUTARIA e tipo_dfe na tabela TIPO_DFE_CLASSIFICACAO
-----------------------------------------------------------------------------------------------


UPDATE SITUACAO_TRIBUTARIA SET SITR_DESCRICAO='Tributação em documento específico' WHERE SITR_ID=17;
UPDATE TIPO_DFE_CLASSIFICACAO SET TDCL_TPDF_ID=5 WHERE TDCL_ID=340;


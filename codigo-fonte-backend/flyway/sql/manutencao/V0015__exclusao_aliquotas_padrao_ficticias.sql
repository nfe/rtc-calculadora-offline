-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2025-12-12
-- Autor: Felipe Zschornack
-- =====================================================

-- *****************************************************************************************
-- ************** É OBRIGATÓRIO INCLUIR O REGISTRO NA TABELA VERSAO_BASE_DADO **************
-- *****************************************************************************************

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('2025-12-12'),
    'V0015',
    'Remove as alíquotas padrão fictícias.'
);

-- -----------------------------------------------------------------------------------------------
-- Descrição:
-- 1 - Remove as alíquotas padrão fictícias.
-- -----------------------------------------------------------------------------------------------

DELETE FROM ALIQUOTA_PADRAO WHERE ALPA_ID IN (1, 2);
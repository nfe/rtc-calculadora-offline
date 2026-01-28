-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2025-12-01
-- Autor: Luis Augusto
-- =====================================================

-- *****************************************************************************************
-- ************** É OBRIGATÓRIO INCLUIR O REGISTRO NA TABELA VERSAO_BASE_DADO **************
-- *****************************************************************************************

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('2025-12-01'),
    'V0014',
    'Desativação da monofasia e correção da nomenclatura do 200020.'
);

-- -----------------------------------------------------------------------------------------------
-- Descrição:
-- 1 - Desativação da monofasia.
-- 2 - Correção da nomenclatura do código 200020.
-- -----------------------------------------------------------------------------------------------

UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NBS ou NCM' WHERE CLTR_ID=31;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_IN_POSSUI_AJUSTE=1 WHERE TRTR_ID=23;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_IN_POSSUI_AJUSTE=1 WHERE TRTR_ID=24;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_IN_POSSUI_AJUSTE=1 WHERE TRTR_ID=25;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_IN_POSSUI_AJUSTE=1 WHERE TRTR_ID=26;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_IN_POSSUI_AJUSTE=1 WHERE TRTR_ID=27;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_IN_POSSUI_AJUSTE=1 WHERE TRTR_ID=28;
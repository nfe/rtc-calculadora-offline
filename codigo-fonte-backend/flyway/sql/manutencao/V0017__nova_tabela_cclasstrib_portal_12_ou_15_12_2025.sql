-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2025-12-22
-- Autor: Luis Augusto
-- =====================================================

-- *****************************************************************************************
-- ************** É OBRIGATÓRIO INCLUIR O REGISTRO NA TABELA VERSAO_BASE_DADO **************
-- *****************************************************************************************

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('2025-12-22'),
    'V0017',
    'Nova tabela de classificação tributária do portal da nfe'
);

-- -----------------------------------------------------------------------------------------------
-- Descrição:
-- 1 - Novos documentos fiscais para algumas classificações tributárias.
-- 2 - Ajustes de nomenclatura na tabela CLASSIFICACAO_TRIBUTARIA.
-- 3 - Remoção da divisão por 100 na expressão de alíquota em alguns tratamentos tributários.
-- -----------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
-- Ajustes de nomenclatura na tabela CLASSIFICACAO_TRIBUTARIA
----------------------------------------------------------------------------------------

UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=15;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=16;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=17;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=18;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=19;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NBS' WHERE CLTR_ID=39;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NBS' WHERE CLTR_ID=40;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=41;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_NOMENCLATURA='NCM' WHERE CLTR_ID=42;

----------------------------------------------------------------------------------------------------------------
-- Inclusão de novos registros de acordo com a nova tabela de classificação tributária publicada em 15/12/2025
----------------------------------------------------------------------------------------------------------------

INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(315,61,5,'2026-01-01',NULL);
INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(316,173,2,'2026-01-01',NULL);

--------------------------------------------------------------------------------------------
-- Remoção da divisão por 100 na expressão de alíquota em alguns tratamentos tributários
--------------------------------------------------------------------------------------------

UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='2.08' WHERE TRTR_ID=34;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='0.53' WHERE TRTR_ID=35;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='3.65' WHERE TRTR_ID=36;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='3.65' WHERE TRTR_ID=37;


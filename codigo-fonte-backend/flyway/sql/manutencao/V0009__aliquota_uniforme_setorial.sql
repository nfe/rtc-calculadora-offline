-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2025-09-24
-- Autor: Luis Augusto
-- =====================================================

-- *****************************************************************************************
-- ************** É OBRIGATÓRIO INCLUIR O REGISTRO NA TABELA VERSAO_BASE_DADO **************
-- *****************************************************************************************

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('now'),
    'v0009',
    'Ligação da tabela ALIQUOTA_AD_VALOREM com CLASSIFICACAO_TRIBUTARIA e definição de alíquotas uniformes para as classificações tributárias setoriais, além da inclusão da coluna TRTR_IN_POSSUI_MONOFASIA na tabela TRATAMENTO_TRIBUTARIO.'
);

-- -----------------------------------------------------------------------------------------------
-- Descrição:
-- 1 - Ligação da tabela ALIQUOTA_AD_VALOREM com CLASSIFICACAO_TRIBUTARIA;
-- 2 - Definição de alíquotas uniformes para as classificações tributárias setoriais;
-- 3 - Inclusão da coluna TRTR_IN_POSSUI_MONOFASIA na tabela TRATAMENTO_TRIBUTARIO.
-- -----------------------------------------------------------------------------------------------

-- -----------------------------------------------------------------------------------------------
-- Início da migração
-- -----------------------------------------------------------------------------------------------

ALTER TABLE TRATAMENTO_TRIBUTARIO ADD COLUMN TRTR_IN_POSSUI_MONOFASIA INTEGER NOT NULL DEFAULT 0;

UPDATE TRATAMENTO_TRIBUTARIO
SET TRTR_IN_POSSUI_MONOFASIA = 1
WHERE LOWER(TRTR_DESCRICAO) LIKE '%mono%';

ALTER TABLE ALIQUOTA_AD_VALOREM ADD COLUMN AADV_CLTR_ID INTEGER REFERENCES CLASSIFICACAO_TRIBUTARIA(CLTR_ID);

UPDATE ALIQUOTA_AD_VALOREM
SET AADV_CLTR_ID = NULL;

-----------------------------------------------------------------------------------------
-- PARA CLASSTRIB 010001 - OPERAÇÕES DO FGTS NÃO REALIZADAS PELA CAIXA ECONÔMICA FEDERAL
-----------------------------------------------------------------------------------------

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 2, 5, '2026-01-01', '2026-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 3, 5, '2026-01-01', '2026-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 4, 5, '2026-01-01', '2026-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(1.00, 2, 5, '2027-01-01', '2033-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 3, 5, '2027-01-01', '2033-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 4, 5, '2027-01-01', '2033-12-31');

-----------------------------------------------------------------------------------------
-- PARA CLASSTRIB 010002 - OPERAÇÕES DO SETOR FINANCEIRO
-----------------------------------------------------------------------------------------

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 2, 6, '2026-01-01', '2026-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 3, 6, '2026-01-01', '2026-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 4, 6, '2026-01-01', '2026-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(10.85, 2, 6, '2027-01-01', '2033-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 3, 6, '2027-01-01', '2033-12-31');

INSERT INTO ALIQUOTA_AD_VALOREM (AADV_VALOR, AADV_TBTO_ID, AADV_CLTR_ID, AADV_INICIO_VIGENCIA, AADV_FIM_VIGENCIA) VALUES
(0.00, 4, 6, '2027-01-01', '2033-12-31');






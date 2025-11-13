-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2025-10-28
-- Autor: Luis Augusto
-- =====================================================

-- *****************************************************************************************
-- ************** É OBRIGATÓRIO INCLUIR O REGISTRO NA TABELA VERSAO_BASE_DADO **************
-- *****************************************************************************************

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('now'),
    'v0012',
    'Novos registros na tabela TRATAMENTO_TRIBUTARIO para monofasia.'
);

-- -----------------------------------------------------------------------------------------------
-- Descrição:
-- 1 - Novos registros na tabela TRATAMENTO_TRIBUTARIO para monofasia.
-- -----------------------------------------------------------------------------------------------

-- -----------------------------------------------------------------------------------------------
-- Início da migração
-- -----------------------------------------------------------------------------------------------

UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Regra Geral', FDLG_TEXTO_CURTO='LC 214/2025' WHERE FDLG_ID=1;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='aliquotaAdRemPrincipal', TRTR_EXPRESSAO_ALIQUOTA_EFETIVA='', TRTR_EXPRESSAO_TRIBUTO_CALCULADO='quantidade*aliquotaAdRemPrincipal', TRTR_EXPRESSAO_TRIBUTO_DEVIDO='' WHERE TRTR_ID=23;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='aliquotaAdRemPrincipal', TRTR_EXPRESSAO_ALIQUOTA_EFETIVA='aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_CALCULADO='quantidade*aliquotaAdRemPrincipal', TRTR_EXPRESSAO_TRIBUTO_DEVIDO='(pBio/(1-pBio))*quantidade*aliquotaAdRemSecundaria' WHERE TRTR_ID=24;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='aliquotaAdRemPrincipal', TRTR_EXPRESSAO_ALIQUOTA_EFETIVA='', TRTR_EXPRESSAO_TRIBUTO_CALCULADO='quantidade*aliquotaAdRemPrincipal', TRTR_EXPRESSAO_TRIBUTO_DEVIDO='quantidade*aliquotaAdRemPrincipal*(1-percentualDiferimento)' WHERE TRTR_ID=25;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='(1-pBio)*aliquotaAdRemPrincipal+pBio*aliquotaAdRemSecundaria', TRTR_EXPRESSAO_ALIQUOTA_EFETIVA='aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_CALCULADO='(variacaoPontoPercentual/100)*quantidade*(pBio/(1-pBio))*aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_DEVIDO='quantidade*aliquota' WHERE TRTR_ID=26;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='(1-pBio)*aliquotaAdRemPrincipal+pBio*aliquotaAdRemSecundaria', TRTR_EXPRESSAO_ALIQUOTA_EFETIVA='aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_CALCULADO='(variacaoPontoPercentual/100)*quantidade*(pBio/(1-pBio))*aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_DEVIDO='quantidade*aliquota' WHERE TRTR_ID=27;
UPDATE TRATAMENTO_TRIBUTARIO SET TRTR_EXPRESSAO_ALIQUOTA='(1-pBio)*aliquotaAdRemPrincipal+pBio*aliquotaAdRemSecundaria', TRTR_EXPRESSAO_ALIQUOTA_EFETIVA='aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_CALCULADO='(variacaoPontoPercentual/100)*quantidade*(pBio/(1-pBio))*aliquotaAdRemSecundaria', TRTR_EXPRESSAO_TRIBUTO_DEVIDO='quantidade*aliquota' WHERE TRTR_ID=28;

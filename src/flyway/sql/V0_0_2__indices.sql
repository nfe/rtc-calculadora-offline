-- Índices para UF 
CREATE INDEX idx_uf_sigla ON UF(UF_SIGLA);

-- Índices para MUNICIPIO 
CREATE INDEX idx_municipio_uf_cd ON MUNICIPIO(MUNI_UF_CD);
CREATE INDEX idx_municipio_nome ON MUNICIPIO(MUNI_NOME);

-- Índices para NBS 
CREATE INDEX idx_nbs_codigo ON NBS(NBS_CD);
CREATE INDEX idx_nbs_inicio_vigencia ON NBS(NBS_INICIO_VIGENCIA);
CREATE INDEX idx_nbs_fim_vigencia ON NBS(NBS_FIM_VIGENCIA);

-- Índices para NCM 
CREATE INDEX idx_ncm_codigo ON NCM(NCM_CD);
CREATE INDEX idx_ncm_inicio_vigencia ON NCM(NCM_INICIO_VIGENCIA);
CREATE INDEX idx_ncm_fim_vigencia ON NCM(NCM_FIM_VIGENCIA);

-- Índices para CLASSIFICACAO_TRIBUTARIA 
CREATE INDEX idx_classificacao_tributaria_sitr_id ON CLASSIFICACAO_TRIBUTARIA(CLTR_SITR_ID);
CREATE INDEX idx_classificacao_tributaria_inicio_vigencia ON CLASSIFICACAO_TRIBUTARIA(CLTR_INICIO_VIGENCIA);
CREATE INDEX idx_classificacao_tributaria_fim_vigencia ON CLASSIFICACAO_TRIBUTARIA(CLTR_FIM_VIGENCIA);

-- Índices para TRIBUTO 
CREATE INDEX idx_tributo_nome ON TRIBUTO(TBTO_NOME);
CREATE INDEX idx_tributo_sigla ON TRIBUTO(TBTO_SIGLA);
CREATE INDEX idx_tributo_inicio_vigencia ON TRIBUTO(TBTO_INICIO_VIGENCIA);
CREATE INDEX idx_tributo_fim_vigencia ON TRIBUTO(TBTO_FIM_VIGENCIA);

-- Índices para TRIBUTO_SITUACAO_TRIBUTARIA 
CREATE INDEX idx_tributo_situacao_tributaria_sitr_id ON TRIBUTO_SITUACAO_TRIBUTARIA(TRST_SITR_ID);
CREATE INDEX idx_tributo_situacao_tributaria_tbto_id ON TRIBUTO_SITUACAO_TRIBUTARIA(TRST_TBTO_ID);
CREATE INDEX idx_tributo_situacao_tributaria_inicio_vigencia ON TRIBUTO_SITUACAO_TRIBUTARIA(TRST_INICIO_VIGENCIA);
CREATE INDEX idx_tributo_situacao_tributaria_fim_vigencia ON TRIBUTO_SITUACAO_TRIBUTARIA(TRST_FIM_VIGENCIA);

-- Índices para FUNDAMENTACAO_CLASSIFICACAO 
CREATE INDEX idx_fundamentacao_classificacao_cltr_id ON FUNDAMENTACAO_CLASSIFICACAO(FDCL_CLTR_ID);
CREATE INDEX idx_fundamentacao_classificacao_inicio_vigencia ON FUNDAMENTACAO_CLASSIFICACAO(FDCL_INICIO_VIGENCIA);
CREATE INDEX idx_fundamentacao_classificacao_fim_vigencia ON FUNDAMENTACAO_CLASSIFICACAO(FDCL_FIM_VIGENCIA);

-- Índices para NCM_APLICAVEL 
CREATE INDEX idx_ncm_aplicavel_ncm_cd ON NCM_APLICAVEL(NCMA_NCM_CD);
CREATE INDEX idx_ncm_aplicavel_cltr_id ON NCM_APLICAVEL(NCMA_CLTR_ID);
CREATE INDEX idx_ncm_aplicavel_inicio_vigencia ON NCM_APLICAVEL(NCMA_INICIO_VIGENCIA);
CREATE INDEX idx_ncm_aplicavel_fim_vigencia ON NCM_APLICAVEL(NCMA_FIM_VIGENCIA);

-- Índices para NBS_APLICAVEL 
CREATE INDEX idx_nbs_aplicavel_nbs_cd ON NBS_APLICAVEL(NBSA_NBS_CD);
CREATE INDEX idx_nbs_aplicavel_cltr_id ON NBS_APLICAVEL(NBSA_CLTR_ID);
CREATE INDEX idx_nbs_aplicavel_inicio_vigencia ON NBS_APLICAVEL(NBSA_INICIO_VIGENCIA);
CREATE INDEX idx_nbs_aplicavel_fim_vigencia ON NBS_APLICAVEL(NBSA_FIM_VIGENCIA);

-- Índices para ALIQUOTA_PADRAO 
CREATE INDEX idx_aliquota_padrao_alre_id ON ALIQUOTA_PADRAO(ALPA_ALRE_ID);
CREATE INDEX idx_aliquota_padrao_muni_cd ON ALIQUOTA_PADRAO(ALPA_MUNI_CD);
CREATE INDEX idx_aliquota_padrao_uf_cd ON ALIQUOTA_PADRAO(ALPA_UF_CD);
CREATE INDEX idx_aliquota_padrao_inicio_vigencia ON ALIQUOTA_PADRAO(ALPA_INICIO_VIGENCIA);
CREATE INDEX idx_aliquota_padrao_fim_vigencia ON ALIQUOTA_PADRAO(ALPA_FIM_VIGENCIA);

-- Índices para ALIQUOTA_AD_VALOREM 
CREATE INDEX idx_aliquota_ad_valorem_tbto_id ON ALIQUOTA_AD_VALOREM(AADV_TBTO_ID);
CREATE INDEX idx_aliquota_ad_valorem_inicio_vigencia ON ALIQUOTA_AD_VALOREM(AADV_INICIO_VIGENCIA);
CREATE INDEX idx_aliquota_ad_valorem_fim_vigencia ON ALIQUOTA_AD_VALOREM(AADV_FIM_VIGENCIA);

-- Índices para ALIQUOTA_AD_REM 
CREATE INDEX idx_aliquota_ad_rem_tbto_id ON ALIQUOTA_AD_REM(AARE_TBTO_ID);
CREATE INDEX idx_aliquota_ad_rem_unmd_id ON ALIQUOTA_AD_REM(AARE_UNMD_ID);
CREATE INDEX idx_aliquota_ad_rem_inicio_vigencia ON ALIQUOTA_AD_REM(AARE_INICIO_VIGENCIA);
CREATE INDEX idx_aliquota_ad_rem_fim_vigencia ON ALIQUOTA_AD_REM(AARE_FIM_VIGENCIA);

-- Índices para ALIQUOTA_AD_VALOREM_PRODUTO 
CREATE INDEX idx_aliquota_ad_valorem_produto_ncm_cd ON ALIQUOTA_AD_VALOREM_PRODUTO(AAVP_NCM_CD);
CREATE INDEX idx_aliquota_ad_valorem_produto_aadv_id ON ALIQUOTA_AD_VALOREM_PRODUTO(AAVP_AADV_ID);
CREATE INDEX idx_aliquota_ad_valorem_produto_inicio_vigencia ON ALIQUOTA_AD_VALOREM_PRODUTO(AAVP_INICIO_VIGENCIA);
CREATE INDEX idx_aliquota_ad_valorem_produto_fim_vigencia ON ALIQUOTA_AD_VALOREM_PRODUTO(AAVP_FIM_VIGENCIA);

-- Índices para ALIQUOTA_AD_REM_PRODUTO 
CREATE INDEX idx_aliquota_ad_rem_produto_ncm_cd ON ALIQUOTA_AD_REM_PRODUTO(AARP_NCM_CD);
CREATE INDEX idx_aliquota_ad_rem_produto_aare_id ON ALIQUOTA_AD_REM_PRODUTO(AARP_AARE_ID);
CREATE INDEX idx_aliquota_ad_rem_produto_inicio_vigencia ON ALIQUOTA_AD_REM_PRODUTO(AARP_INICIO_VIGENCIA);
CREATE INDEX idx_aliquota_ad_rem_produto_fim_vigencia ON ALIQUOTA_AD_REM_PRODUTO(AARP_FIM_VIGENCIA);

-- Índices para SITUACAO_TRIBUTARIA 
CREATE INDEX idx_situacao_tributaria_codigo ON SITUACAO_TRIBUTARIA(SITR_CD);
CREATE INDEX idx_situacao_tributaria_inicio_vigencia ON SITUACAO_TRIBUTARIA(SITR_INICIO_VIGENCIA);
CREATE INDEX idx_situacao_tributaria_fim_vigencia ON SITUACAO_TRIBUTARIA(SITR_FIM_VIGENCIA);
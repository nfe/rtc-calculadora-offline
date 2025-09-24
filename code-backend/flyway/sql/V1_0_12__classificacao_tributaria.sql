INSERT INTO CLASSIFICACAO_TRIBUTARIA (
    CLTR_ID,
    CLTR_SITR_ID,
    CLTR_TIPO_ALIQUOTA,
    CLTR_CD,
    CLTR_DESCRICAO,
    CLTR_NOMENCLATURA,
    CLTR_MEMORIA_CALCULO,
    CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS,
    CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS,
    CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR,
    CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE,
    CLTR_TMES_ID,
    CLTR_CREDITO_OPERACAO_ANTECEDENTE,
    CLTR_INICIO_VIGENCIA,
    CLTR_FIM_VIGENCIA
) VALUES
(
    -- CLTR_ID
    1,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000001',
    -- CLTR_DESCRICAO
    'Situações tributadas integralmente pelo IBS e CBS.',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    2,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000002',
    -- CLTR_DESCRICAO
    'Exploração de via',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    3,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000003',
    -- CLTR_DESCRICAO
    'Regime automotivo - projetos incentivados (art. 311)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    4,
    -- CLTR_SITR_ID
    1,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '000004',
    -- CLTR_DESCRICAO
    'Regime automotivo - projetos incentivados (art. 312)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    5,
    -- CLTR_SITR_ID
    2,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '010001',
    -- CLTR_DESCRICAO
    'Operações do FGTS não realizadas pela Caixa Econômica Federal',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    6,
    -- CLTR_SITR_ID
    3,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '010002',
    -- CLTR_DESCRICAO
    'Operações do serviço financeiro',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    7,
    -- CLTR_SITR_ID
    4,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme nacional (referência)',
    -- CLTR_CD
    '011001',
    -- CLTR_DESCRICAO
    'Planos de assistência funerária.',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    8,
    -- CLTR_SITR_ID
    4,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme nacional (referência)',
    -- CLTR_CD
    '011002',
    -- CLTR_DESCRICAO
    'Planos de assistência à saúde',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    9,
    -- CLTR_SITR_ID
    4,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme nacional (referência)',
    -- CLTR_CD
    '011003',
    -- CLTR_DESCRICAO
    'Intermediação de planos de assistência à saúde',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    10,
    -- CLTR_SITR_ID
    5,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme nacional (referência)',
    -- CLTR_CD
    '011004',
    -- CLTR_DESCRICAO
    'Concursos e prognósticos',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    11,
    -- CLTR_SITR_ID
    6,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme nacional (referência)',
    -- CLTR_CD
    '011005',
    -- CLTR_DESCRICAO
    'Planos de assistência à saúde de animais domésticos',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    12,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200001',
    -- CLTR_DESCRICAO
    'Aquisições realizadas entre empresas autorizadas a operar em zonas de processamento de exportação',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    13,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200002',
    -- CLTR_DESCRICAO
    'Fornecimento ou importação para produtor rural não contribuinte ou TAC',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    14,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200003',
    -- CLTR_DESCRICAO
    'Vendas de produtos destinados à alimentação humana (Anexo I)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    15,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200004',
    -- CLTR_DESCRICAO
    'Venda de dispositivos médicos (Anexo XII)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    16,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200005',
    -- CLTR_DESCRICAO
    'Venda de dispositivos médicos adquiridos por órgãos da administração pública (Anexo IV)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    17,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200006',
    -- CLTR_DESCRICAO
    'Situação de emergência de saúde pública reconhecida pelo Poder público (Anexo XII)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    18,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200007',
    -- CLTR_DESCRICAO
    'Fornecimento dos dispositivos de acessibilidade próprios para pessoas com deficiência (Anexo XIII)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    19,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200008',
    -- CLTR_DESCRICAO
    'Fornecimento dos dispositivos de acessibilidade próprios para pessoas com deficiência adquiridos por órgãos da administração pública (Anexo V)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    20,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200009',
    -- CLTR_DESCRICAO
    'Fornecimento de medicamentos (Anexo XIV)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    21,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200010',
    -- CLTR_DESCRICAO
    'Fornecimento dos medicamentos registrados na Anvisa, adquiridos por órgãos da administração pública',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    22,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200011',
    -- CLTR_DESCRICAO
    'Fornecimento das composições para nutrição enteral e parenteral quando adquiridas por órgãos da administração pública (Anexo VI)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    23,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200012',
    -- CLTR_DESCRICAO
    'Situação de emergência de saúde pública reconhecida pelo Poder público (Anexo XIV)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    24,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200013',
    -- CLTR_DESCRICAO
    'Fornecimento de tampões higiênicos, absorventes higiênicos internos ou externos',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    25,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200014',
    -- CLTR_DESCRICAO
    'Fornecimento dos produtos hortícolas, frutas e ovos (Anexo XV)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    26,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200015',
    -- CLTR_DESCRICAO
    'Venda de automóveis de passageiros de fabricação nacional adquiridos por motoristas profissionais ou pessoas com deficiência',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    27,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200016',
    -- CLTR_DESCRICAO
    'Prestação de serviços de pesquisa e desenvolvimento por Instituição Científica, Tecnológica e de Inovação (ICT)',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    28,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200017',
    -- CLTR_DESCRICAO
    'Operações relacionadas ao FGTS',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    29,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200018',
    -- CLTR_DESCRICAO
    'Operações de resseguro e retrocessão',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    30,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200019',
    -- CLTR_DESCRICAO
    'Importador dos serviços financeiros contribuinte',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    31,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200020',
    -- CLTR_DESCRICAO
    'Operação praticada por sociedades cooperativas optantes por regime específico do IBS e CBS',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    32,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200021',
    -- CLTR_DESCRICAO
    'Serviços de transporte público coletivo de passageiros ferroviário e hidroviário',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    33,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200022',
    -- CLTR_DESCRICAO
    'Operação originada fora da ZFM que destine bem material industrializado a contribuinte estabelecido na ZFM',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    34,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200023',
    -- CLTR_DESCRICAO
    'Operação realizada por indústria incentivada que destine bem material intermediário para outra indústria incentivada na ZFM',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    35,
    -- CLTR_SITR_ID
    7,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200024',
    -- CLTR_DESCRICAO
    'Operação originada fora das Áreas de Livre Comércio destinadas a contribuinte estabelecido nas Áreas de Livre Comércio',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de 0%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    36,
    -- CLTR_SITR_ID
    8,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200025',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços de educação relacionados ao Programa Universidade para Todos (Prouni)',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    37,
    -- CLTR_SITR_ID
    9,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200026',
    -- CLTR_DESCRICAO
    'Locação de imóveis localizados nas zonas reabilitadas',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    38,
    -- CLTR_SITR_ID
    10,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200027',
    -- CLTR_DESCRICAO
    'Operações de locação, cessão onerosa e arrendamento de bens imóveis',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    39,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200028',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços de educação (Anexo II)',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    40,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200029',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços de saúde humana (Anexo III)',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    41,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200030',
    -- CLTR_DESCRICAO
    'Venda dos dispositivos médicos  (Anexo IV)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    42,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200031',
    -- CLTR_DESCRICAO
    'Fornecimento dos dispositivos de acessibilidade próprios para pessoas com deficiência (Anexo V)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    43,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200032',
    -- CLTR_DESCRICAO
    'Fornecimento dos medicamentos registrados na Anvisa ou produzidos por farmácias de manipulação, ressalvados os medicamentos sujeitos à alíquota zero',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    44,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200033',
    -- CLTR_DESCRICAO
    'Fornecimento das composições para nutrição enteral e parenteral (Anexo VI)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    45,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200034',
    -- CLTR_DESCRICAO
    'Fornecimento dos alimentos destinados ao consumo humano (Anexo VII)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    46,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200035',
    -- CLTR_DESCRICAO
    'Fornecimento dos produtos de higiene pessoal e limpeza (Anexo VIII)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    47,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200036',
    -- CLTR_DESCRICAO
    'Fornecimento de produtos agropecuários, aquícolas, pesqueiros, florestais e extrativistas vegetais in natura',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    48,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200037',
    -- CLTR_DESCRICAO
    'Fornecimento de serviços ambientais de conservação ou recuperação da vegetação nativa',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    49,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200038',
    -- CLTR_DESCRICAO
    'Fornecimento dos insumos agropecuários e aquícolas (Anexo IX)',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    50,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200039',
    -- CLTR_DESCRICAO
    'Fornecimento dos serviços e o licenciamento ou cessão dos direitos destinados às produções nacionais artísticas (Anexo X)',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    51,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200040',
    -- CLTR_DESCRICAO
    'Fornecimento de serviços de comunicação institucional à administração pública',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    52,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200041',
    -- CLTR_DESCRICAO
    'Fornecimento de serviço de educação desportiva (art. 141. I)',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    53,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200042',
    -- CLTR_DESCRICAO
    'Fornecimento de serviço de educação desportiva (art. 141. II)',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    54,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200043',
    -- CLTR_DESCRICAO
    'Fornecimento à administração pública dos serviços e dos bens relativos à soberania (Anexo XI)',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    55,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200044',
    -- CLTR_DESCRICAO
    'Operações e prestações de serviços de segurança da informação e segurança cibernética desenvolvidos por sociedade que tenha sócio brasileiro (Anexo XI)',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    56,
    -- CLTR_SITR_ID
    11,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200045',
    -- CLTR_DESCRICAO
    'Operações relacionadas a projetos de reabilitação urbana de zonas históricas e de áreas críticas de recuperação e reconversão urbanística',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    57,
    -- CLTR_SITR_ID
    12,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200046',
    -- CLTR_DESCRICAO
    'Operações com bens imóveis',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    58,
    -- CLTR_SITR_ID
    13,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200047',
    -- CLTR_DESCRICAO
    'Bares e Restaurantes',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    59,
    -- CLTR_SITR_ID
    13,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200048',
    -- CLTR_DESCRICAO
    'Hotelaria, Parques de Diversão e Parques Temáticos',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    60,
    -- CLTR_SITR_ID
    13,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200049',
    -- CLTR_DESCRICAO
    'Transporte coletivo de passageiros rodoviário, ferroviário e hidroviário',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    61,
    -- CLTR_SITR_ID
    13,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200450',
    -- CLTR_DESCRICAO
    'Serviços de transporte aéreo regional coletivo de passageiros ou de carga',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    62,
    -- CLTR_SITR_ID
    13,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200051',
    -- CLTR_DESCRICAO
    'Agências de Turismo',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    63,
    -- CLTR_SITR_ID
    14,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '200052',
    -- CLTR_DESCRICAO
    'Prestação de serviços de profissões intelectuais',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%, sendo que esse regime define uma redução de [percentual_reducao]% na alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    64,
    -- CLTR_SITR_ID
    15,
    -- CLTR_TIPO_ALIQUOTA
    'Fixa',
    -- CLTR_CD
    '220001',
    -- CLTR_DESCRICAO
    'Incorporação imobiliária submetida ao regime especial de tributação',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    65,
    -- CLTR_SITR_ID
    15,
    -- CLTR_TIPO_ALIQUOTA
    'Fixa',
    -- CLTR_CD
    '220002',
    -- CLTR_DESCRICAO
    'Incorporação imobiliária submetida ao regime especial de tributação',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    66,
    -- CLTR_SITR_ID
    15,
    -- CLTR_TIPO_ALIQUOTA
    'Fixa',
    -- CLTR_CD
    '220003',
    -- CLTR_DESCRICAO
    'Alienação de imóvel decorrente de parcelamento do solo',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    67,
    -- CLTR_SITR_ID
    15,
    -- CLTR_TIPO_ALIQUOTA
    'Fixa',
    -- CLTR_CD
    '220004',
    -- CLTR_DESCRICAO
    'Operações com bens e com serviços realizadas por Sociedade Anônima do Futebol - SAF',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    68,
    -- CLTR_SITR_ID
    16,
    -- CLTR_TIPO_ALIQUOTA
    'Fixa',
    -- CLTR_CD
    '221001',
    -- CLTR_DESCRICAO
    'Locação, cessão onerosa ou arrendamento de bem imóvel com alíquota sobre a receita bruta',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    69,
    -- CLTR_SITR_ID
    17,
    -- CLTR_TIPO_ALIQUOTA
    'Padrão',
    -- CLTR_CD
    '222001',
    -- CLTR_DESCRICAO
    'Transporte internacional de passageiros, caso os trechos de ida e volta sejam vendidos em conjunto',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota de [aliquota_ad_valorem]%.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    70,
    -- CLTR_SITR_ID
    18,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '400001',
    -- CLTR_DESCRICAO
    'Fornecimento de serviços de transporte público coletivo de passageiros rodoviário e metroviário',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Anulação',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    71,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410001',
    -- CLTR_DESCRICAO
    'Fornecimento de bonificações quando constem no documento fiscal e que não dependam de evento posterior',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    72,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410002',
    -- CLTR_DESCRICAO
    'Transferências entre estabelecimentos pertencentes ao mesmo contribuinte',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    73,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410003',
    -- CLTR_DESCRICAO
    'Doações sem contraprestação em benefício do doador',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    74,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410004',
    -- CLTR_DESCRICAO
    'Exportações de bens e serviços',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    75,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410005',
    -- CLTR_DESCRICAO
    'Fornecimentos realizados pela União, pelos Estados, pelo Distrito Federal e pelos Municípios',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Anulação',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    76,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410006',
    -- CLTR_DESCRICAO
    'Fornecimentos realizados por entidades religiosas e templos de qualquer culto',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Anulação',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    77,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410007',
    -- CLTR_DESCRICAO
    'Fornecimentos realizados por partidos políticos',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Anulação',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    78,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410008',
    -- CLTR_DESCRICAO
    'Fornecimentos de livros, jornais, periódicos e do papel destinado a sua impressão',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    79,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410009',
    -- CLTR_DESCRICAO
    'Fornecimentos de fonogramas e videofonogramas musicais produzidos no Brasil',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    80,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410010',
    -- CLTR_DESCRICAO
    'Fornecimentos de serviço de comunicação nas modalidades de radiodifusão sonora e de sons e imagens de recepção livre e gratuita',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    81,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410011',
    -- CLTR_DESCRICAO
    'Fornecimentos de ouro, quando definido em lei como ativo financeiro ou instrumento cambial',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Anulação',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    82,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410012',
    -- CLTR_DESCRICAO
    'Fornecimento de condomínio edilício não optante pelo regime regular',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    83,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410013',
    -- CLTR_DESCRICAO
    'Exportações de combustíveis',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    84,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410014',
    -- CLTR_DESCRICAO
    'Fornecimento de produtor rural não contribuinte',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    85,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410015',
    -- CLTR_DESCRICAO
    'Fornecimento por transportador autônomo não contribuinte',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    86,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410016',
    -- CLTR_DESCRICAO
    'Fornecimento ou aquisição de resíduos sólidos',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    87,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410017',
    -- CLTR_DESCRICAO
    'Aquisição de bem móvel com crédito presumido sob condição de revenda realizada',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    88,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410018',
    -- CLTR_DESCRICAO
    'Operações relacionadas aos fundos garantidores e executores de políticas públicas',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    89,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410019',
    -- CLTR_DESCRICAO
    'Exclusão da gorjeta na base de cálculo no fornecimento de alimentação',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    90,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410020',
    -- CLTR_DESCRICAO
    'Exclusão do valor de intermediação na base de cálculo no fornecimento de alimentação',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    91,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410021',
    -- CLTR_DESCRICAO
    'Contribuição de que trata o art. 149-A da Constituição Federal',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    92,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410022',
    -- CLTR_DESCRICAO
    'Consolidação da propriedade do bem pelo credor',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    93,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410023',
    -- CLTR_DESCRICAO
    'Alienação de bens móveis ou imóveis que tenham sido objeto de garantia em que o prestador da garantia não seja contribuinte',
    -- CLTR_NOMENCLATURA
    'CIB ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    94,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410024',
    -- CLTR_DESCRICAO
    'Consolidação da propriedade do bem pelo grupo de consórcio',
    -- CLTR_NOMENCLATURA
    'CIB ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    95,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410025',
    -- CLTR_DESCRICAO
    'Alienação de bem que tenha sido objeto de garantia em que o prestador da garantia não seja contribuinte',
    -- CLTR_NOMENCLATURA
    'CIB ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    96,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410026',
    -- CLTR_DESCRICAO
    'Cessão de direitos desportivos de atletas a residente ou domiciliado no exterior',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    97,
    -- CLTR_SITR_ID
    19,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '410999',
    -- CLTR_DESCRICAO
    'Operações não onerosas sem previsão de tributação, não especificadas anteriormente',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    98,
    -- CLTR_SITR_ID
    20,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '510001',
    -- CLTR_DESCRICAO
    'Operações, sujeitas a diferimento, com energia elétrica, relativas à geração, comercialização, distribuição e transmissão',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    99,
    -- CLTR_SITR_ID
    20,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '510002',
    -- CLTR_DESCRICAO
    'Operações, sujeitas a diferimento, com insumos agropecuários e aquícolas destinados a produtor rural contribuinte (Anexo IX)',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    100,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550001',
    -- CLTR_DESCRICAO
    'Exportações de bens materiais',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    101,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550002',
    -- CLTR_DESCRICAO
    'Regime de Trânsito',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    102,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550003',
    -- CLTR_DESCRICAO
    'Regimes de Depósito  (art. 85)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    103,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550004',
    -- CLTR_DESCRICAO
    'Regimes de Depósito (art. 87)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    104,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550005',
    -- CLTR_DESCRICAO
    'Regimes de Depósito (art. 87, Parágrafo único)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    105,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550006',
    -- CLTR_DESCRICAO
    'Regimes de Permanência Temporária',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    106,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550007',
    -- CLTR_DESCRICAO
    'Regimes de Aperfeiçoamento',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    107,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550008',
    -- CLTR_DESCRICAO
    'Importação de bens para o Regime de Repetro-Temporário',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    108,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550009',
    -- CLTR_DESCRICAO
    'GNL-Temporário',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    109,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550010',
    -- CLTR_DESCRICAO
    'Repetro-Permanente',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    110,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550011',
    -- CLTR_DESCRICAO
    'Repetro-Industrialização',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    111,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550012',
    -- CLTR_DESCRICAO
    'Repetro-Nacional',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    112,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550013',
    -- CLTR_DESCRICAO
    'Repetro-Entreposto',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    113,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550014',
    -- CLTR_DESCRICAO
    'Zona de Processamento de Exportação',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    114,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550015',
    -- CLTR_DESCRICAO
    'Regime Tributário para Incentivo à Modernização e à Ampliação da Estrutura Portuária',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    115,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550016',
    -- CLTR_DESCRICAO
    'Regime Especial de Incentivos para o Desenvolvimento da Infraestrutura',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    116,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550017',
    -- CLTR_DESCRICAO
    'Regime Tributário para Incentivo à Atividade Econômica Naval',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    117,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550018',
    -- CLTR_DESCRICAO
    'Desoneração da aquisição de bens de capital',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    118,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550019',
    -- CLTR_DESCRICAO
    'Importação de bem material por indústria incentivada para utilização na ZFM',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    119,
    -- CLTR_SITR_ID
    21,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '550020',
    -- CLTR_DESCRICAO
    'Áreas de livre comércio',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    120,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '620001',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre combustíveis',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    121,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '620002',
    -- CLTR_DESCRICAO
    'Tributação monofásica com responsabilidade pela retenção sobre combustíveis',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    122,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '620003',
    -- CLTR_DESCRICAO
    'Tributação monofásica com tributos retidos por responsabilidade sobre combustíveis',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    123,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '620004',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre mistura de EAC com gasolina A em percentual superior ao obrigatório',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    124,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '620005',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre mistura de EAC com gasolina A em percentual inferior ao obrigatório',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    125,
    -- CLTR_SITR_ID
    22,
    -- CLTR_TIPO_ALIQUOTA
    'Uniforme setorial',
    -- CLTR_CD
    '620006',
    -- CLTR_DESCRICAO
    'Tributação monofásica sobre combustíveis cobrada anteriormente',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de [quantidade] [unidade], com alíquota ad rem de R$ [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    126,
    -- CLTR_SITR_ID
    23,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '800001',
    -- CLTR_DESCRICAO
    'Fusão, cisão ou incorporação',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    127,
    -- CLTR_SITR_ID
    23,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '800002',
    -- CLTR_DESCRICAO
    'Transferência de crédito do associado, inclusive as cooperativas singulares',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    128,
    -- CLTR_SITR_ID
    24,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '810001',
    -- CLTR_DESCRICAO
    'Crédito presumido sobre o valor apurado nos fornecimentos a partir da ZFM',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    1,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    129,
    -- CLTR_SITR_ID
    24,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '810002',
    -- CLTR_DESCRICAO
    'Anulação de Crédito por Saídas Imunes/Isentas',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Anulação',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    130,
    -- CLTR_SITR_ID
    24,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '810003',
    -- CLTR_DESCRICAO
    'Débitos de notas fiscais não processadas na apuração',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    131,
    -- CLTR_SITR_ID
    24,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '810004',
    -- CLTR_DESCRICAO
    'Desenquadramento do Simples Nacional',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    0,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    132,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '820001',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de planos de assistência à saúde',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    133,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '820002',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de planos de assistência funerária',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    134,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '820003',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de planos de assistência à saúde de animais domésticos',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    135,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '820004',
    -- CLTR_DESCRICAO
    'Documento com informações de prestação de serviços de consursos de prognósticos',
    -- CLTR_NOMENCLATURA
    'Não possui',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    136,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '820005',
    -- CLTR_DESCRICAO
    'Documento com informações de alienação de bens imóveis',
    -- CLTR_NOMENCLATURA
    'CIB',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    0,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    0,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    137,
    -- CLTR_SITR_ID
    25,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '820006',
    -- CLTR_DESCRICAO
    'Documento com informações de fornecimento de serviços de exploração de via',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    138,
    -- CLTR_SITR_ID
    26,
    -- CLTR_TIPO_ALIQUOTA
    'Sem alíquota',
    -- CLTR_CD
    '830001',
    -- CLTR_DESCRICAO
    'Documento com exclusão da BC da CBS e do IBS de energia elétrica fornecida pela distribuidora à UC',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], sendo que esta operação não possui alíquota.',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    1,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    1,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    'Manutenção',
    -- CLTR_INICIO_VIGENCIA
    '2026-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    139,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000001',
    -- CLTR_DESCRICAO
    'Primeiro fornecimento a qualquer título de bem',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    140,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000002',
    -- CLTR_DESCRICAO
    'Arrematação em leilão público',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    141,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000003',
    -- CLTR_DESCRICAO
    'Transferência não onerosa de bem produzido',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    142,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000004',
    -- CLTR_DESCRICAO
    'Incorporação do bem ao ativo imobilizado pelo fabricante',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    143,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000005',
    -- CLTR_DESCRICAO
    'Consumo do bem pelo fabricante',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    144,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000006',
    -- CLTR_DESCRICAO
    'Venda direta pelo fabricante ao consumidor final',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    145,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000007',
    -- CLTR_DESCRICAO
    'Venda de produto enviado para terceiro para processamento/industrialização',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    146,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000008',
    -- CLTR_DESCRICAO
    'Venda efetiva de mercadoria remetida para venda fora do estabelecimento',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    147,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000009',
    -- CLTR_DESCRICAO
    'Importação de bens e serviços',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    148,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000010',
    -- CLTR_DESCRICAO
    'Primeiro fornecimento de bem mineral (venda no mercado interno ou exportação)',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    149,
    -- CLTR_SITR_ID
    27,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '000011',
    -- CLTR_DESCRICAO
    'Prognósticos, Apostas e Fantasy Game, classificados na NBS 2018: 1.2508.00.00',
    -- CLTR_NOMENCLATURA
    'NBS',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    150,
    -- CLTR_SITR_ID
    28,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '100001',
    -- CLTR_DESCRICAO
    'Exportações de bens e serviços',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    151,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200001',
    -- CLTR_DESCRICAO
    'Remessa para filial/depósito fechado',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    152,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200002',
    -- CLTR_DESCRICAO
    'Remessa para terceiro para processamento e industrialização',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    153,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200003',
    -- CLTR_DESCRICAO
    'Remessa para adquirente por conta e ordem do fabricante',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    154,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200004',
    -- CLTR_DESCRICAO
    'Remessa para venda fora do estabelecimento',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    155,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200005',
    -- CLTR_DESCRICAO
    'Retorno de mercadoria enviada para processamento/industrialização',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    156,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200006',
    -- CLTR_DESCRICAO
    'Retorno de remessa enviada para venda fora do estabelecimento',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    157,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200007',
    -- CLTR_DESCRICAO
    'Venda de bem ou serviço tributado pelo IS em operação anterior',
    -- CLTR_NOMENCLATURA
    'NBS ou NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    158,
    -- CLTR_SITR_ID
    29,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '200008',
    -- CLTR_DESCRICAO
    'Venda em embalagem não primária (não embalada para consumo imediato) de bebidas e fumo',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    159,
    -- CLTR_SITR_ID
    30,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '300001',
    -- CLTR_DESCRICAO
    'Devolução de bens com documento fiscal emitido pelo adquirente',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    160,
    -- CLTR_SITR_ID
    30,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '300002',
    -- CLTR_DESCRICAO
    'Devolução de bens com documento fiscal emitido pelo fornecedor',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    161,
    -- CLTR_SITR_ID
    31,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '400001',
    -- CLTR_DESCRICAO
    'Fornecimento de bens com o fim específico de exportação',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    162,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '500001',
    -- CLTR_DESCRICAO
    'Venda de gás natural destinado à utilização como insumo em processo industrial e como combustível para fins de transporte, realizada por produtor extrativista',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    163,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '500003',
    -- CLTR_DESCRICAO
    'Venda de gás natural destinado à utilização como insumo em processo industrial e como combustível para fins de transporte, realizada por importador',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    164,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '500004',
    -- CLTR_DESCRICAO
    'Importação de gás natural destinado à utilização como insumo em processo industrial e como combustível para fins de transporte, realizada por importador distribuidor ou varejista',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    165,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '500007',
    -- CLTR_DESCRICAO
    'Venda de veículo nacional com alíquota zero para motorista de táxi com regime diferenciado reconhecido',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    166,
    -- CLTR_SITR_ID
    32,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '500008',
    -- CLTR_DESCRICAO
    'Venda de veículo nacional com alíquota zero para pessoa com deficiência ou transtorno do espectro autista',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    167,
    -- CLTR_SITR_ID
    33,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '600001',
    -- CLTR_DESCRICAO
    'Bagagens de viajantes e de tripulantes submetidas ao regime de tributação especial',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
),
(
    -- CLTR_ID
    168,
    -- CLTR_SITR_ID
    33,
    -- CLTR_TIPO_ALIQUOTA
    'Alíquotas Combinadas (Ad Valorem e Ad Rem)',
    -- CLTR_CD
    '600002',
    -- CLTR_DESCRICAO
    'Remessas internacionais submetidas ao regime de tributação simplificada',
    -- CLTR_NOMENCLATURA
    'NCM',
    -- CLTR_MEMORIA_CALCULO
    'Operação de consumo sujeita à tributação pelo Imposto Seletivo, com enquadramento legal em [norma], tributada conforme [tratamento]. A base de cálculo utilizada é de R$ [base_calculo], com alíquota ad valorem de [aliquota_ad_valorem]%. A alíquota ad rem é de [aliquota_ad_rem].',
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_CBS
    NULL,
    -- CLTR_IN_APROPRIACAO_CREDITOS_ADQUIRENTES_IBS
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_FORNECEDOR
    NULL,
    -- CLTR_IN_CREDITO_PRESUMIDO_ADQUIRENTE
    NULL,
    -- CLTR_TMES_ID
    NULL,
    -- CLTR_CREDITO_OPERACAO_ANTECEDENTE
    NULL,
    -- CLTR_INICIO_VIGENCIA
    '2027-01-01',
    -- CLTR_FIM_VIGENCIA
    NULL
);

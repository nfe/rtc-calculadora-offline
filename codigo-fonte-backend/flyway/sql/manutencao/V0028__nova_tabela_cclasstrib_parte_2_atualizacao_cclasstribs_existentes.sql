

-- =====================================================
-- MIGRAÇÃO DE MANUTENÇÃO
-- Data: 2026-01-30
-- Autor: Equipe
-- =====================================================

-- *****************************************************************************************
-- ************** É OBRIGATÓRIO INCLUIR O REGISTRO NA TABELA VERSAO_BASE_DADO **************
-- *****************************************************************************************

INSERT INTO VERSAO_BASE_DADO (VRBD_DATA, VRBD_VERSAO_BASE_DADO, VRBD_DESCRICAO) VALUES
(
    datetime('2026-01-30'),
    'V0028',
    'Nova tabela de Classificações Tributárias - Parte 2: Atualização de cClassTribs existentes.'
);

-------------------------------------------------------------------------------------------------
-- Descrição:
-- Atualiza classificações tributárias já existentes na tabela CLASSIFICACAO_TRIBUTARIA e popula as tabelas relacionadas.
-----------------------------------------------------------------------------------------------

UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=5;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=6;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Serviços de transporte de bens até as zonas de processamento de exportação e bens exportados a partir das zonas de processamento de exportação', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=12;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='1' WHERE CLTR_ID=14;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Fornecimento de dispositivos médicos (Anexo XII)', CLTR_ANEXO='12', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=15;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Fornecimento de dispositivos médicos para órgãos da administração pública e entidades de saúde imunes (Anexo IV)', CLTR_ANEXO='4', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=16;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='12' WHERE CLTR_ID=17;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='13' WHERE CLTR_ID=18;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='5' WHERE CLTR_ID=19;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Fornecimento dos medicamentos registrados na Anvisa', CLTR_ANEXO='', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=20;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=21;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='6', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=22;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Situação de emergência de saúde pública reconhecida pelo Poder público', CLTR_ANEXO='12', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=23;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='15' WHERE CLTR_ID=25;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2025-01-23' WHERE CLTR_ID=27;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=28;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=30;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='2' WHERE CLTR_ID=39;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='3' WHERE CLTR_ID=40;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='4' WHERE CLTR_ID=41;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='5' WHERE CLTR_ID=42;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-26' WHERE CLTR_ID=43;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='6' WHERE CLTR_ID=44;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='7' WHERE CLTR_ID=45;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='8' WHERE CLTR_ID=46;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='9' WHERE CLTR_ID=49;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Fornecimento dos bens e serviços relacionados com produções nacionais artísticas, culturais, de eventos, jornalísticas e audiovisuais (Anexo X)', CLTR_ANEXO='10', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=50;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Fornecimento de serviço de gestão e exploração do desporto (art. 141. II)', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=53;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='11', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=54;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='11', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=55;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Fornecimentos realizados por partidos políticos, entidades sindicais e instituições de educação e de assistência social', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=76;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DESCRICAO='Operações, sujeitas a diferimento, com energia elétrica, relativas à importação, geração, comercialização, distribuição e transmissão', CLTR_ANEXO='90281', CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=97;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_ANEXO='9' WHERE CLTR_ID=98;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=132;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=133;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=134;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=135;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=136;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=137;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=169;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=174;
UPDATE CLASSIFICACAO_TRIBUTARIA SET CLTR_DATA_ATUALIZACAO='2026-01-23' WHERE CLTR_ID=175;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 212. As operações relacionadas ao Fundo de Garantia do Tempo de Serviço (FGTS) são sujeitas à incidência do IBS e da CBS, por alíquotas nacionalmente uniformes, calculadas nos termos do inciso II do § 1º do art. 10 da Emenda Constitucional nº 132, de 20 de dezembro de 2023.\n§ 3º Ficam sujeitas:\nII - no caso das operações previstas nos incisos II e III do § 2º deste artigo, às alíquotas do IBS e da CBS que serão fixadas de modo que a soma das alíquotas corresponda:    (Redação dada pela Lei Complementar nº 227, de 2026)\na) em 2027 a 1,0% (um inteiro por cento);\nb) em 2028 a 1,0% (um inteiro por cento); \nc) em 2029 a 1,2% (um inteiro e dois décimos por cento); \nd) em 2030 a 1,4% (um inteiro e quatro décimos por cento);  \ne) em 2031 a 1,6% (um inteiro e seis décimos por cento); \nf) em 2032 a 1,8% (um inteiro e oito décimos por cento); e\ng) a partir de 2033, a 3,0% (três inteiros por cento).' WHERE FDLG_ID=5;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 233. De 2027 a 2033, a soma das alíquotas do IBS e da CBS incidentes sobre os serviços financeiros de que trata o art. 189 desta Lei Complementar, calculada nos termos do inciso II do § 1º do art. 10 da Emenda Constitucional nº 132, de 20 de dezembro de 2023, corresponderá:\nI - em 2027 e 2028, a 10,85% (dez inteiros e oitenta e cinco centésimos por cento); \nII - em 2029, a 11,00% (onze por cento);  \nIII - em 2030, a 11,15% (onze inteiros e quinze centésimos por cento);  \nIV - em 2031, a 11,30% (onze inteiros e trinta centésimos por cento);   \nV - em 2032, a 11,50% (onze inteiros e cinquenta centésimos por cento); e \nVI - em 2033, a 12,50% (doze inteiros e cinquenta centésimos por cento).' WHERE FDLG_ID=6;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 146. São reduzidas a zero as alíquotas do IBS e da CBS sobre o fornecimento dos medicamentos registrados na Anvisa, desde que destinados, de acordo com o registro sanitário, a:\nI – doenças raras;\nII – doenças negligenciadas;\nIII – oncologia;\nIV – diabetes;\nV – HIV/aids e outras infecções sexualmente transmissíveis (IST);\nVI – doenças cardiovasculares; e\nVII – Programa Farmácia Popular do Brasil ou equivalente' WHERE FDLG_ID=20;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 146.\n§ 1º São também reduzidas a zero as alíquotas do IBS e da CBS sobre o fornecimento de medicamentos registrados na Anvisa quando:\nI – adquiridos por órgãos da administração pública direta, por autarquias e por fundações públicas;\nII – adquiridos por entidades de saúde imunes ao IBS e à CBS que possuam Cebas por comprovarem a prestação de serviços ao SUS, nos termos dos arts. 9º a 11 da Lei Complementar nº 187, de 16 de dezembro de 2021;', FDLG_TEXTO_CURTO='Art. 146, § 1º, I e II' WHERE FDLG_ID=21;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 146.\n§ 2º A redução de alíquotas de que trata o caput deste artigo aplica-se também ao fornecimento de composições para nutrição enteral e parenteral, composições especiais e fórmulas nutricionais destinadas às pessoas com erros inatos do metabolismo relacionadas no Anexo VI desta Lei Complementar, com a especificação das respectivas classificações da NCM/ SH, quando adquiridas por órgãos e entidades mencionados nos incisos I e II do § 1º deste artigo.' WHERE FDLG_ID=22;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 146.\n§ 4º Em caso de emergência de saúde pública reconhecida pelo Poder Legislativo federal, estadual, distrital ou municipal competente, ato conjunto do Ministro da Fazenda, do Ministério da Saúde e do CGIBS poderá ser editado, a qualquer momento, tão somente para incluir medicamentos e linhas de cuidado não contemplados na redução de alíquota a que se refere este artigo, limitada a vigência do benefício ao período da respectiva emergência de saúde pública' WHERE FDLG_ID=23;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 156. São reduzidas a zero as alíquotas do IBS e da CBS incidentes sobre a prestação de serviços de pesquisa e desenvolvimento por Instituição Científica, Tecnológica e de Inovação (ICT) sem fins lucrativos, bem como por fundações de apoio credenciadas na forma da lei, para:\nI - a administração pública direta, autarquias e fundações públicas; ou \nII - contribuinte sujeito ao regime regular do IBS e da CBS.' WHERE FDLG_ID=27;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 212. As operações relacionadas ao Fundo de Garantia do Tempo de Serviço (FGTS) são sujeitas à incidência do IBS e da CBS, por alíquotas nacionalmente uniformes, calculadas nos termos do inciso II do § 1º do art. 10 da Emenda Constitucional nº 132, de 20 de dezembro de 2023.  \n § 2º As operações relacionadas ao FGTS são aquelas necessárias à aplicação da Lei nº 8.036, de 11 de maio de 1990, realizadas: \n I – pelo agente operador do FGTS;\n § 3º Ficam sujeitas:\nI - no caso das operações previstas no inciso I do § 2º deste artigo, à alíquota zero do IBS e da CBS;' WHERE FDLG_ID=28;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 142. Ficam reduzidas em 60% (sessenta por cento) as alíquotas do IBS e da CBS sobre: \nII – fornecimento de serviços de segurança da informação e segurança cibernética desenvolvidos por sociedade que tenha sócio brasileiro com o mínimo de 20% (vinte por cento) do seu capital social, relacionados no Anexo XI desta Lei Complementar, com a especificação das respectivas classificações da NBS.' WHERE FDLG_ID=55;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 28. Nas operações com energia elétrica ou com direitos a ela relacionados, o recolhimento do IBS e da CBS relativo a importação, geração, comercialização, distribuição e transmissão será realizado exclusivamente:\n§ 1º O recolhimento do IBS e da CBS incidentes nas operações com energia elétrica, ou com direitos a ela relacionados, relativas a importação, geração, comercialização, distribuição e transmissão ocorrerá somente no fornecimento:\nI - para consumo; ou\nII - para contribuinte não sujeito ao regime regular do IBS e da CBS.' WHERE FDLG_ID=97;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 80. Para fins do disposto no art. 79 desta Lei Complementar, considera-se exportação de serviço ou de bem imaterial, inclusive direitos, o fornecimento para residente ou domiciliado no exterior e consumo no exterior.\nII - o fornecimento dos seguintes bens e serviços, desde que vinculados direta e exclusivamente à exportação de bens materiais ou associados à entrega no exterior de bens materiais:\na) intermediação na distribuição de mercadorias no exterior (comissão de agente);\nb) seguro de cargas;\nc) despacho aduaneiro;\nd) armazenagem de mercadorias;\ne) transporte rodoviário, ferroviário, aéreo, aquaviário ou multimodal de cargas;\nf) manuseio de cargas;\ng) manuseio de contêineres;\nh) unitização ou desunitização de cargas;\ni) consolidação ou desconsolidação documental de cargas;\nj) agenciamento de transporte de cargas;\nk) remessas expressas;\nl) pesagem e medição de cargas;\nm) refrigeração de cargas;\nn) arrendamento mercantil operacional ou locação de contêineres;\no) instalação e montagem de mercadorias exportadas; e\np) treinamento para uso de mercadorias exportadas.' WHERE FDLG_ID=169;
UPDATE FUNDAMENTACAO_LEGAL SET FDLG_TEXTO='Art. 10. Considera-se ocorrido o fato gerador do IBS e da CBS no momento do fornecimento nas operações com bens ou com serviços, ainda que de execução continuada ou fracionada.\n§ 3º Nas operações de execução continuada ou fracionada, considera-se ocorrido o fato gerador na primeira entre as seguintes ocorrências:\nI - quando se torna exigível a parte da contraprestação correspondente a cada pagamento; ou\nII - pagamento da obrigação decorrente do fornecimento.' WHERE FDLG_ID=175;
DELETE FROM TIPO_DFE_CLASSIFICACAO WHERE TDCL_ID=23;
DELETE FROM TIPO_DFE_CLASSIFICACAO WHERE TDCL_ID=102;
DELETE FROM TIPO_DFE_CLASSIFICACAO WHERE TDCL_ID=103;
INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(340,12,4,'2026-01-01',NULL);
INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(341,30,10,'2026-01-01',NULL);
INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(342,54,12,'2026-01-01',NULL);
INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(343,55,12,'2026-01-01',NULL);
INSERT INTO TIPO_DFE_CLASSIFICACAO(TDCL_ID,TDCL_CLTR_ID,TDCL_TPDF_ID,TDCL_INICIO_VIGENCIA,TDCL_FIM_VIGENCIA) VALUES(344,169,2,'2026-01-01',NULL);

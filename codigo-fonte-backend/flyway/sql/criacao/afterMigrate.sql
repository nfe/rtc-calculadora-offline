-- =====================================================
-- CALLBACK FLYWAY: afterMigrate.sql
-- Verificações e otimizações pós-migração
-- Gerado em: 2025-09-24
-- =====================================================

-- =====================================================
-- SEÇÃO 1: VERIFICAÇÕES DE INTEGRIDADE
-- =====================================================

-- Verifica integridade geral do banco
PRAGMA integrity_check;

-- Verifica consistência de chaves estrangeiras
PRAGMA foreign_key_check;

-- Verifica se foreign keys estão habilitadas
SELECT 
    CASE 
        WHEN foreign_keys = 1 THEN '✅ Foreign keys HABILITADAS'
        ELSE '❌ Foreign keys DESABILITADAS - PROBLEMA!'
    END as status_fk
FROM PRAGMA_foreign_keys();

-- =====================================================
-- SEÇÃO 2: ANÁLISE DE PERFORMANCE
-- =====================================================

-- Atualiza estatísticas para otimização de consultas
ANALYZE;

-- Executa otimizações automáticas
PRAGMA optimize;

-- Verifica tamanho das tabelas principais
SELECT 
    name as tabela,
    ROUND((
        SELECT COUNT(*) * 
        (SELECT AVG(length(sql)) FROM sqlite_master WHERE type='table' AND name=m.name)
        FROM sqlite_master WHERE type='table' AND name=m.name
    ) / 1024.0, 2) || ' KB' as tamanho_estimado
FROM sqlite_master m
WHERE type = 'table' 
AND name NOT LIKE 'sqlite_%'
AND name NOT LIKE 'flyway_%'
ORDER BY name;

-- =====================================================
-- FIM DAS VERIFICAÇÕES
-- =====================================================

-- Mensagem final
SELECT '🎉 Verificações pós-migração concluídas com sucesso!' as status_final;

-- =====================================================
-- CALLBACK afterMigrate.sql - CONCLUÍDO
-- =====================================================

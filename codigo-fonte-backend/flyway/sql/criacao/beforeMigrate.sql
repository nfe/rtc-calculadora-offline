-- =====================================================
-- CALLBACK FLYWAY: beforeMigrate.sql
-- PRAGMA statements executados ANTES de cada migração
-- Gerado em: 2025-09-24
-- =====================================================

-- Configurações essenciais de encoding
PRAGMA encoding = 'UTF-8';
PRAGMA foreign_keys = ON;

-- Configurações de performance
PRAGMA journal_mode = WAL;
PRAGMA synchronous = NORMAL;
PRAGMA temp_store = MEMORY;
PRAGMA cache_size = 10000;
PRAGMA page_size = 4096;
PRAGMA mmap_size = 268435456; -- 256MB

-- Configurações de integridade
PRAGMA recursive_triggers = ON;
PRAGMA case_sensitive_like = OFF;
PRAGMA secure_delete = OFF;
PRAGMA trusted_schema = ON;

-- =====================================================
-- PRAGMA statements configurados com sucesso
-- =====================================================

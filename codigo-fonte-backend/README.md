# Script de Cópia Otimizada - copy_src_optimized.py

Este script Python automatiza o processo de preparação de código fonte para distribuição offline de projetos Spring Boot, copiando e configurando arquivos dos repositórios originais para estruturas de projeto customizadas.

## 📋 Funcionalidades

- **Cópia automatizada** de código fonte com exclusões inteligentes
- **Gerenciamento de configurações** YML específicas para ambiente offline
- **Processamento multi-projeto** (Backend e Split Payment)
- **Limpeza automática** de arquivos desnecessários
- **Substituição de configurações** originais por versões customizadas

## 🏗️ Estrutura de Projetos

### Projetos Processados:
1. **Backend (api-regime-geral)** - API principal da calculadora
2. **Split Payment** - API de pagamento simplificado

### Diretórios de Origem:
```
../repositorios/backend/src/       → ../api-regime-geral/src/
../repositorios/splitpayment/src/  → ../splitpayment/src/
```

### Configurações Customizadas:
```
./yml/api-regime-geral/    → Configurações para backend
./yml/splitpayment/        → Configurações para split payment
./xml/api-regime-geral/    → POM.xml do backend
./xml/splitpayment/        → POM.xml do split payment
```

## 🔧 Como Funciona

### Etapa 1: Cópia do Código Fonte
- Copia recursivamente todos os arquivos dos repositórios
- Aplica exclusões configuradas (ex: configurações de segurança online)
- Preserva estrutura de diretórios

### Etapa 2: Limpeza de Configurações
- Remove **TODOS** os arquivos `application*.yml` originais
- Limpa diretórios: `src/main/resources/` e `src/test/resources/`

### Etapa 3: Aplicação de Configurações Customizadas
- Copia automaticamente arquivos das pastas `yml_dir`
- **Inteligência automática** de destino:
  - Arquivos com "test" no nome → `src/test/resources/`
  - Outros arquivos → `src/main/resources/`

### Etapa 4: Arquivos Adicionais
- Copia arquivos Maven (`.mvn`, `mvnw`, `flyway`)
- Aplica POM.xml customizado
- Copia scripts auxiliares

### Etapa 5: Limpeza Final
- Remove arquivos específicos desnecessários
- Limpa metadados não utilizados

## 🚀 Uso

### Execução Básica:
```bash
cd scripts/
python3 copy_src_optimized.py
```

### Pré-requisitos:
- Python 3.x
- Repositórios baixados em `../repositorios/`
- Configurações personalizadas nas pastas `yml/` e `xml/`

## 📁 Estrutura de Arquivos

```
scripts/
├── copy_src_optimized.py          # Script principal
├── README.md                      # Esta documentação
├── yml/
│   ├── api-regime-geral/
│   │   ├── application-offline.yml
│   │   └── application-testes.yml
│   └── splitpayment/
│       └── application-offline.yml
└── xml/
    ├── api-regime-geral/
    │   └── pom.xml
    └── splitpayment/
        └── pom.xml
```

## ⚙️ Configurações por Projeto

### Backend (api-regime-geral)
- **Exclusões**: Configurações HTTP, OpenAPI e Security
- **Configurações**: Aplicações offline e de teste
- **Arquivos especiais**: Flyway para migração de banco

### Split Payment
- **Configurações**: Apenas aplicação offline
- **Estrutura**: Simplificada sem exclusões específicas

## 🔍 Padrões de Exclusão

```python
exclude_patterns = [
    "main/**/config/http",      # Configurações HTTP online
    "main/**/config/openapi",   # Documentação API online
    "main/**/config/security"   # Configurações de segurança online
]
```

## 📝 Logs e Monitoramento

O script fornece logs detalhados de todas as operações:

```
=== Processando BACKEND ===

1. Copiando ../repositorios/backend/src para ../api-regime-geral/src (excluindo padrões)
2. Removendo todos os arquivos application*.yml originais dos diretórios de recursos
3. Auto-copiando todos os arquivos YML de ./yml/api-regime-geral
4. Copiando arquivos e pastas adicionais
5. Removendo outros arquivos desnecessários

=== Processamento do BACKEND concluído ===
```

## 🎯 Vantagens da Abordagem

### Automatização Completa
- **Sem configuração manual** de cada arquivo
- **Detecção automática** de destino para configurações
- **Processo repetível** e confiável

### Flexibilidade
- **Fácil adição** de novos arquivos YML
- **Configuração centralizada** por projeto
- **Exclusões configuráveis** por padrão

### Manutenibilidade
- **Código limpo** com funções especializadas
- **Comentários em português** para clareza
- **Estrutura modular** para extensibilidade

## 🔧 Customização

### Adicionar Novo Projeto:
1. Crie função `get_novo_projeto_config()`
2. Adicione chamada em `main()`
3. Configure pastas `yml/` e `xml/` correspondentes

### Modificar Exclusões:
```python
'exclude_patterns': [
    "main/**/config/novo_padrao",
    "test/**/config/exclusao"
]
```

### Adicionar Mapeamentos:
```python
'additional_mapping': {
    "origem/arquivo": "destino/arquivo",
    "origem/pasta/": "destino/pasta/"
}
```

## 📋 Resultado Final

Após execução, os projetos estarão prontos para:
- **Compilação offline** com Maven
- **Execução em ambiente isolado**
- **Distribuição como pacote completo**

## 🐛 Solução de Problemas

### Arquivo não encontrado:
- Verifique se repositórios estão em `../repositorios/`
- Confirme estrutura de pastas `yml/` e `xml/`

### Configurações não aplicadas:
- Verifique nomes dos arquivos YML
- Confirme que arquivos têm extensão `.yml`

### Exclusões não funcionando:
- Verifique padrões glob em `exclude_patterns`
- Teste padrões com caracteres curinga `**` e `*`

---

**Desenvolvido para**: Pipeline de Distribuição Offline da Calculadora  
**Versão**: Otimizada com tradução PT-BR  
**Compatibilidade**: Python 3.x, Windows/Linux
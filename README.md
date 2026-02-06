# Calculadora de Tributos da Receita Federal

A Receita Federal do Brasil lançou a versão beta de sua Calculadora de Tributos oficial para os novos impostos sobre o consumo (CBS, IBS e Imposto Seletivo), como parte da reforma tributária do país. Esta ferramenta de código aberto visa padronizar os cálculos e promover uma relação mais cooperativa entre os contribuintes e a autoridade fiscal.

A calculadora está disponível tanto como um simulador online de fácil utilização quanto como um componente local que pode ser integrado aos sistemas ERP das empresas por meio de uma API. Ela também possui um "Assistente de Emissão" para ajudar a gerar corretamente as informações fiscais nas notas fiscais eletrônicas. Esta iniciativa está alinhada aos princípios modernos de administração tributária automatizada, fornecendo um motor de cálculo oficial transparente e auditável para o novo sistema tributário.

[Link para a calculadora offline](https://piloto-cbs.tributos.gov.br/servico/calculadora-consumo/calculadora/calculadora-offline)

## O que este script faz?

Este é um **robô automatizado** que verifica diariamente se saiu uma atualização da Calculadora da Reforma Tributária e, quando sai, automaticamente baixa, organiza e publica a nova versão no GitHub — tudo sem precisar de intervenção humana! 🤖✨

## Como funciona? (passo a passo simples)

### 1. **Agendamento Automático** ⏰
- O robô **roda sozinho todos os dias à meia-noite** (horário UTC)
- Também pode ser acionado **manualmente** quando necessário

### 2. **Obtenção da URL de Download** 🔗
- Consulta a API oficial para obter a URL de download mais recente:
  ```
  https://piloto-cbs.tributos.gov.br/servico/calculadora-consumo/api/calculadora/download/url?platform=default
  ```
- A URL é obtida dinamicamente, garantindo que sempre seja usada a versão mais atual

### 3. **Download do Arquivo** 📥
- Baixa o arquivo compactado (`calculadora.zip`) usando a URL obtida da API

### 4. **Verificação de Nova Versão** 🔍
- Calcula o hash MD5 do arquivo `codigo-fonte-backend.zip` baixado
- Compara com o hash da versão anterior (armazenado em `codigo-fonte-backend.md5`)
- Se os hashes forem diferentes, significa que há uma nova versão disponível

### 5. **Atualização Automática** 💾
*(Só acontece se houver nova versão)*
- Descompacta o código-fonte completo na pasta `codigo-fonte-backend`
- Atualiza o arquivo de hash MD5 (codigo-fonte-backend.md5)
- Salva os arquivos na pasta (codigo-fonte-backend)
- Cria um registro (commit) com a data da atualização
- Envia tudo para o GitHub

### 6. **Publicação de Versão** 🎉
*(Só acontece se houver nova versão)*
- Cria uma nova "release" (versão publicada)
- Anexa o arquivo `codigo-fonte-backend.zip` do código-fonte
- Marca como versão "latest" (mais recente)

## 🔗 Links Úteis

- [Calculadora Offline - Portal Oficial](https://piloto-cbs.tributos.gov.br/servico/calculadora-consumo/calculadora/calculadora-offline)
- [API de Download](https://piloto-cbs.tributos.gov.br/servico/calculadora-consumo/api/calculadora/download/url?platform=default)

## 📋 Como Executar Manualmente

1. Acesse a aba **Actions** do repositório
2. Selecione o workflow **"Download and Update Código Fonte da Calculadora da Reforma Tributária"**
3. Clique em **"Run workflow"**
4. Selecione a branch `main` e confirme

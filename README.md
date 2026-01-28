# Calculadora de Tributos da Receita Federal

A Receita Federal do Brasil lançou a versão beta de sua Calculadora de Tributos oficial para os novos impostos sobre o consumo (CBS, IBS e Imposto Seletivo), como parte da reforma tributária do país. Esta ferramenta de código aberto visa padronizar os cálculos e promover uma relação mais cooperativa entre os contribuintes e a autoridade fiscal.

A calculadora está disponível tanto como um simulador online de fácil utilização quanto como um componente local que pode ser integrado aos sistemas ERP das empresas por meio de uma API. Ela também possui um "Assistente de Emissão" para ajudar a gerar corretamente as informações fiscais nas notas fiscais eletrônicas. Esta iniciativa está alinhada aos princípios modernos de administração tributária automatizada, fornecendo um motor de cálculo oficial transparente e auditável para o novo sistema tributário.

## O que este script faz?

Este é um **robô automatizado** que verifica diariamente se saiu uma atualização da Calculadora da Reforma Tributária e, quando sai, automaticamente baixa, organiza e publica a nova versão no GitHub — tudo sem precisar de intervenção humana! 🤖✨

## Como funciona? (passo a passo simples)

### 1. **Agendamento Automático** ⏰
- O robô **roda sozinho todos os dias à meia-noite** (horário UTC)
- Também pode ser acionado **manualmente** quando necessário

### 2. **Download do Arquivo** 📥
- Baixa um arquivo compactado (`.zip`) do servidor da SERPRO com a versão mais recente da calculadora
- Atualmente usamos a URL https://storagegw.estaleiro.serpro.gov.br/rtccalc-repo-hom/calculadora.zip
- Sempre que houver atualizanção na URL precisamos mudar o script

### 3. **Atualização Automática** 💾
*(Só acontece se houver nova versão)*
- Descompacta o código-fonte completo
- Salva os arquivos no repositório
- Cria um registro (commit) com a data da atualização
- Envia tudo para o GitHub

### 5. **Publicação de Versão** 🎉
*(Só acontece se houver nova versão)*
- Cria uma nova "release" (versão publicada)
- Anexa o arquivo `.zip` do código-fonte
- Marca como versão "latest" (mais recente)

[Link para a calculadora offline](https://piloto-cbs.tributos.gov.br/servico/calculadora-consumo/calculadora/calculadora-offline)

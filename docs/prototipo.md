# Protótipo: imagem Regime Geral 1.7.2 + benchmark Nginx × Envoy

NFE-214 (parte da NFE-202). Tudo rodou local (host claudinho, 16 CPUs, Docker 29.7, Compose projeto `nfe-214-devops-*`), nada foi publicado. Dados de teste **SINTÉTICOS** (exemplos de `docs/exemplos.md` do repositório oficial da Receita, MIT).

Não oficial. Sem vínculo com a Receita Federal ou o Serpro.

## 1. Imagem

`regime-geral/Dockerfile`: fonte = `ReceitaFederal/calculadora-rtc-regime-regular`, commit `39363aa8` (tag `1.7.2`). Banco `V0073` gerado pelo Flyway do próprio repositório.

Build: Temurin 25 JDK → JRE mínimo (`jdeps` + `jlink`) → `distroless/cc-debian13:nonroot`. Bases fixadas por digest. Maven fixado por sha256 (o repositório não traz o `maven-wrapper.jar`).

```bash
docker buildx build --platform linux/amd64,linux/arm64 -t regime-geral:1.7.2-V0073 regime-geral   # multi-arch; use --load com uma plataforma só
docker run --read-only --tmpfs /tmp --cap-drop ALL --security-opt no-new-privileges -p 8080:8080 -p 9000:9000 regime-geral:1.7.2-V0073
curl localhost:9000/health   # {"status":"UP",...}
```

| Requisito | Resultado |
|---|---|
| Não root | `USER nonroot` (uid 65532). Confirmado em `docker inspect`. |
| Sem shell | distroless: sem `sh`, sem `curl`. |
| `--read-only` | Funciona com `--tmpfs /tmp` (o Tomcat grava lá). A lib nativa do SQLite é extraída no build e usada via `org.sqlite.lib.path`, por isso o `tmpfs` pode ser `noexec` (padrão do Docker). |
| `--cap-drop ALL` | Funciona. |
| JVM como PID 1 | `ENTRYPOINT ["java","-jar",...]`. SIGTERM → encerra com código 143 (padrão da JVM) dentro do `docker stop`. |
| Health | `HEALTHCHECK` com a classe `Healthcheck` (JRE embutido) em `:9000/health`. Estado `healthy` confirmado. |
| Log ECS | `LOGGING_STRUCTURED_FORMAT_CONSOLE=ecs` → JSON ECS em stdout. Confirmado. |
| Shutdown | `SERVER_SHUTDOWN=graceful`. |
| Portas | 8080 (API, `/api`), 9000 (gerência: `/health`, `/metrics`). |
| Contorno de CVE | `-XX:-UseBASE64Intrinsics` mantido em `JAVA_TOOL_OPTIONS` (igual ao `start.sh` do pacote Serpro). |
| Multi-arch | `amd64` nativo e `arm64` (emulado com QEMU) constroem e passam no mesmo teste de contrato. |

### Medidas

| Medida | Regime Geral (este PR) | Serpro `calculadora` (pacote 20261119) |
|---|---|---|
| Tamanho comprimido | 138 MB | 333 MB |
| Tamanho em disco (soma das camadas) | ~201 MB | 912 MB (`docker image ls`) |
| Conteúdo | 1 API | 3 APIs + Nginx + front-end + JDK completo |
| Subida até `/health` = UP (amd64, 3 execuções) | 5,5 s (5,48 / 5,49 / 5,58) | ~11 s (3 JVMs + Nginx via `start.sh`, até a 1ª resposta da API) |
| Subida `arm64` emulado (QEMU) | 78 s (não representativo) | n/a |
| Memória em repouso (20 s após subir) | 787 MiB | não medida |
| Memória sob carga (10 s, 32 conexões) | 1,49 GiB | não medida |

A memória é alta porque a JVM não tem limite de heap: `MaxRAMPercentage=75` sobre 60 GB do host, e os caches Caffeine do `application-offline.yml` pré-alocam. Em produção, defina o limite de memória do container (`-m`) e ajuste `MaxRAMPercentage`. **Não testado** com `-m` baixo.

## 2. Teste de contrato

`tests/contract.sh <url-base> <pasta-de-saída>` envia requisições de `docs/exemplos.md` e confere o comportamento documentado:

| Verificação | amd64 | arm64 |
|---|---|---|
| Observabilidade: 200, 4 objetos, item 4 `INCONSISTENCIA_ENTRADA` `REG-011` | ok | ok |
| Observabilidade: item 3 `vCBS` 42.24 e `vIBS` 0.48 | ok | ok |
| `POST /regime-geral` com a operação de 4 itens → 422 | ok | ok |
| `POST /regime-geral` com 3 itens válidos → 200 | ok | ok |
| Base unificada: `baseRG` 870, `baseIS` 840, `baseSN` 1060 | ok | ok |
| Consultas dados-abertos (class-trib, fundamentações, ncm, alíquota-uf) → 200 | ok | ok |

Os mesmos 6 testes também passam na imagem Serpro. Não cobertos: geração/validação de XML (`/xml/*`) e Simples Nacional (outro repositório, outra etapa).

## 3. Equivalência com a imagem Serpro

Oráculo: pacote `20261119-212613` (`api-regime-geral 1.7.2-0d9b30c1`), rootfs `calculadora.tar.gz` em `FROM scratch` (igual a `nfe/dfetech-taxes-ibs-cbs-api/calculadora/Dockerfile`), iniciado com `bash start.sh`. `tests/equivalence.sh <novo> <oráculo>` compara status e JSON. Ignora `timestamp`, `traceId` e a ordem de arrays de strings (ex.: `avisos`).

| Requisição | Status (novo / Serpro) | Corpo |
|---|---|---|
| `POST /observabilidade/regime-geral` (4 itens) | 200 / 200 | igual |
| `POST /regime-geral` (4 itens) | 422 / 422 | igual |
| `POST /regime-geral` (3 itens) | 200 / 200 | igual |
| `POST /base-calculo/base-calculo-unificada` | 200 / 200 | igual |
| `GET dados-abertos/classificacoes-tributarias/cbs-ibs/class-trib/200028` | 200 / 200 | igual |
| `GET dados-abertos/fundamentacoes-legais` | 200 / 200 | igual |
| `GET dados-abertos/ncm` | 200 / 200 | igual |
| `GET dados-abertos/aliquota-uf` | 200 / 200 | igual |

Resultado: **8 de 8 iguais** (amd64 e arm64) depois da correção abaixo.

Diferenças encontradas e tratadas:

| Diferença | Causa | Ação |
|---|---|---|
| `memoriaCalculo` com `R$ 100.00` (Serpro: `R$ 100,00`) | O JRE do `jlink` sem `jdk.localedata` cai no locale raiz. | Corrigido: `jdk.localedata` + `--include-locales=en,pt` no `jlink`. |
| Ordem dos itens em `avisos` e `timestamp` | Ordem não determinística e relógio. | Normalizados no comparador. Não é diferença funcional. |

Arredondamento: não apareceu diferença. O pacote Serpro atual já é 1.7.2 (a diferença esperada de arredondamento é de versões anteriores). **Limite:** só 8 requisições, todas de 2027 em diante, com poucos itens. A equivalência total exige um corpus maior (ex.: o conjunto de testes do repositório oficial) antes de publicar. O banco não foi comparado byte a byte; só por meio das consultas acima.

## 4. Benchmark Nginx × Envoy (2 réplicas da API)

`bench/run.sh`: 2 réplicas da imagem acima (`--read-only`, `cap_drop ALL`) atrás do proxy, balanceamento round-robin. Carga com `oha` 1.17.0 (digest fixado), 64 conexões, 20 s por cenário, 5 s de aquecimento. Proxies: `nginx:1.28-alpine` e `envoyproxy/envoy:distroless-v1.36.2` (digests no `bench/compose.yaml`). 2 execuções completas (a tabela mostra a 1ª; a 2ª diferiu menos de 3%). Resultado bruto: `bench/results.json`.

| Medida | Nginx 1.28-alpine | Envoy distroless v1.36.2 |
|---|---|---|
| Imagem, comprimida | 26 MB | 37 MB |
| Imagem, em disco | 94,7 MB | 143 MB |
| Subida até 1ª resposta (proxy, API já no ar) | 352 ms (343–352) | 463 ms (406–463) |
| Memória em repouso | 33,1 MiB | 15,2 MiB |
| Memória sob carga | 35,7 MiB | 19,2 MiB |
| POST `/regime-geral`: RPS | 2 850 | 2 877 |
| POST: p50 / p99 | 16,2 ms / 111,7 ms | 16,7 ms / 101,1 ms |
| GET `dados-abertos/ncm` (resposta leve): RPS | 18 070 | 11 256 |
| GET: p50 / p99 | 3,2 ms / 10,4 ms | 5,5 ms / 9,4 ms |

Leitura: com carga real de cálculo (POST) o gargalo é a API, e os dois empatam (RPS e p50 dentro de 1%, p99 do Envoy ~10% menor, dentro do ruído de 2 execuções). Em resposta leve o Nginx entrega ~60% mais RPS e tem menor p50. O Envoy usa menos da metade da memória. Imagem menor e subida mais rápida: Nginx.

**Decisão do desempate: Nginx.** Não há ganho claro do Envoy no caminho de cálculo, e o Nginx já é o proxy do pacote oficial. O Envoy só compensa se precisarmos de recursos dele (mTLS, observabilidade L7, healthcheck ativo); fica para outra etapa se houver esse requisito.

Limites: proxy e cargas no mesmo host de 16 CPUs (a carga disputa CPU com as APIs). Sem TLS. Sem teste de falha de réplica. Config do Nginx sem healthcheck ativo (versão open source). Uma única máquina.

## 5. Pendências para a publicação (fora deste PR)

- Corpus de equivalência maior, incluindo o Simples Nacional.
- Limite de memória/heap definido por perfil de uso.
- CI de build multi-arch com cosign/SBOM (item do parecer da NFE-203). Este PR não adiciona workflow.
- A imagem é "não oficial": manter LICENSE/NOTICE do PR de controles do repositório.

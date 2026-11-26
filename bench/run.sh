#!/usr/bin/env bash
# Nginx x Envoy in front of 2 API replicas. Local only. Usage: bench/run.sh [image]  (default rg:1.7.2-local)
# Needs: docker, jq. Load generator: oha (pinned digest). Results: bench/results.json
set -uo pipefail
cd "$(dirname "$0")"; export RG_IMAGE=${1:-rg:1.7.2-local}; P=nfe-214-devops-bench; PORT=18090
OHA=ghcr.io/hatoo/oha@sha256:9e8cd7468794d692acda410de42738aa1d2fa0268a5c56d1d58581874ee2b0eb
trap 'docker compose -p $P down -v --remove-orphans >/dev/null 2>&1' EXIT
ms() { echo $(( $(date +%s%N) / 1000000 )); }
oha() { docker run --rm --network host -v "$PWD/../tests/fixtures:/f:ro" $OHA --no-tui -z "${DUR:-20}s" -c 64 --output-format json "$@"; }
echo '[]' > results.json
for proxy in nginx envoy; do
  docker compose -p $P down -v --remove-orphans >/dev/null 2>&1
  docker compose -p $P up -d api1 api2 --wait >/dev/null 2>&1
  cid=$(docker compose -p $P --profile $proxy ps -q $proxy 2>/dev/null)
  t0=$(ms); docker compose -p $P --profile $proxy up -d $proxy >/dev/null 2>&1
  for _ in $(seq 400); do curl -fs "localhost:$PORT/api/calculadora/dados-abertos/ncm?ncm=22030000&data=2027-01-01" >/dev/null && break; sleep 0.05; done
  start_ms=$(( $(ms) - t0 )); cid=$(docker compose -p $P --profile $proxy ps -q $proxy)
  sleep 10
  mem_idle=$(docker stats --no-stream --format '{{.MemUsage}}' $cid | cut -d/ -f1)
  # warm-up, then measure
  DUR=5 oha -m POST -H 'Content-Type: application/json' -D /f/operacao-valida.json "http://localhost:$PORT/api/calculadora/regime-geral" >/dev/null
  post=$(oha -m POST -H 'Content-Type: application/json' -D /f/operacao-valida.json "http://localhost:$PORT/api/calculadora/regime-geral")
  get=$(oha "http://localhost:$PORT/api/calculadora/dados-abertos/ncm?ncm=22030000&data=2027-01-01")
  mem_load=$(docker stats --no-stream --format '{{.MemUsage}}' $cid | cut -d/ -f1)
  size=$(docker image inspect $(docker inspect $cid --format '{{.Image}}') --format '{{.Size}}')
  jq --arg p $proxy --arg mi "$mem_idle" --arg ml "$mem_load" --argjson st $start_ms --argjson sz $size --argjson post "$post" --argjson get "$get" \
    '. + [{proxy:$p, image_bytes:$sz, start_ms:$st, mem_idle:$mi, mem_load:$ml,
      post:{rps:$post.summary.requestsPerSec, p50_ms:($post.latencyPercentiles.p50*1000), p99_ms:($post.latencyPercentiles.p99*1000), success:$post.summary.successRate},
      get:{rps:$get.summary.requestsPerSec, p50_ms:($get.latencyPercentiles.p50*1000), p99_ms:($get.latencyPercentiles.p99*1000), success:$get.summary.successRate}}]' results.json > r.tmp && mv r.tmp results.json
done
jq -c '.[]' results.json

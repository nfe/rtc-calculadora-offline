#!/usr/bin/env bash
# Contract test: SYNTHETIC requests from the official docs/exemplos.md (Receita Federal, MIT).
# Usage: tests/contract.sh <base-url> <out-dir>   e.g. http://localhost:8080 out/rg
# Saves each response (status + body) in <out-dir> for the equivalence diff, and asserts the documented behaviour.
set -uo pipefail
B=${1:?base url}; OUT=${2:?out dir}; F=$(dirname "$0")/fixtures; mkdir -p "$OUT"; fail=0
A=$B/api/calculadora
call() { # name method path [data-file]
  local n=$1 m=$2 p=$3 d=${4:-}
  curl -s -o "$OUT/$n.body" -w '%{http_code}' -X "$m" "$A$p" ${d:+-H 'Content-Type: application/json' -d @"$d"} > "$OUT/$n.status"
}
check() { if eval "$2"; then echo "ok   $1"; else echo "FAIL $1"; fail=1; fi; }
call obs POST /observabilidade/regime-geral "$F/operacao.json"
call rg-422 POST /regime-geral "$F/operacao.json"
call rg-ok POST /regime-geral "$F/operacao-valida.json"
call base POST /base-calculo/base-calculo-unificada "$F/base.json"
D=/dados-abertos
call q-classtrib GET "$D/classificacoes-tributarias/cbs-ibs/class-trib/200028?data=2027-01-01"
call q-fund GET "$D/fundamentacoes-legais?data=2027-01-01"
call q-ncm GET "$D/ncm?ncm=22030000&data=2027-01-01"
call q-uf GET "$D/aliquota-uf?codigoUf=43&data=2026-06-01"
st() { cat "$OUT/$1.status"; }
check "observabilidade 200 with 4 objetos, item 4 INCONSISTENCIA_ENTRADA REG-011" \
  "[ \$(st obs) = 200 ] && jq -e '(.objetos|length)==4 and .objetos[3].estadoItem==\"INCONSISTENCIA_ENTRADA\" and .objetos[3].erros[0].code==\"REG-011\"' $OUT/obs.body >/dev/null"
check "observabilidade item 3 vCBS 42.24 / vIBS 0.48" \
  "jq -e '.objetos[2].tribCalc.IBSCBS.gIBSCBS|.gCBS.vCBS==\"42.24\" and .vIBS==\"0.48\"' $OUT/obs.body >/dev/null"
check "regime-geral rejects the 4-item operation with 422" "[ \$(st rg-422) = 422 ]"
check "regime-geral accepts the 3-item operation with 200" "[ \$(st rg-ok) = 200 ]"
check "base unificada: baseRG 870, baseIS 840, baseSN 1060" \
  "[ \$(st base) = 200 ] && jq -e '.baseRG==870 and .baseIS==840 and .baseSN==1060' $OUT/base.body >/dev/null"
check "consultas dados-abertos 200 (class-trib, fundamentacoes, ncm, aliquota-uf)" \
  "[ \"\$(st q-classtrib)\$(st q-fund)\$(st q-ncm)\$(st q-uf)\" = 200200200200 ]"
exit $fail

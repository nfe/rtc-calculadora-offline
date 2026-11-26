#!/usr/bin/env bash
# Compare the outputs saved by tests/contract.sh for two images (new build vs Serpro oracle).
# Usage: tests/equivalence.sh <out-dir-new> <out-dir-oracle>
# Ignored on purpose: "timestamp" and "traceId" (change per call) and the order of string arrays (e.g. avisos).
set -uo pipefail
N=${1:?}; O=${2:?}; rc=0
norm() { jq -S 'walk(if type=="array" and all(.[]; type=="string") then sort else . end) | del(.. | .timestamp?, .traceId?)' "$1" 2>/dev/null || cat "$1"; }
for s in "$N"/*.status; do
  n=$(basename "$s" .status)
  if [ "$(cat "$s")" != "$(cat "$O/$n.status")" ]; then echo "DIFF $n status: $(cat "$s") vs $(cat "$O/$n.status")"; rc=1; continue; fi
  if diff <(norm "$N/$n.body") <(norm "$O/$n.body") >/dev/null; then echo "same $n (HTTP $(cat "$s"))"; else echo "DIFF $n body"; diff <(norm "$N/$n.body") <(norm "$O/$n.body") | head -10; rc=1; fi
done
exit $rc

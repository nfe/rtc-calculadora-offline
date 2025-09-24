rm -rf ./calculadora/db/*

mkdir -p ./calculadora/db

./mvnw -Dflyway.configFiles=./flyway/flyway.conf flyway:migrate || true
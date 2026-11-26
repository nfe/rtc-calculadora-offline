# syntax=docker/dockerfile:1.7
# Regime Geral 1.7.2, built from the Receita Federal MIT source (commit of tag 1.7.2).
# Build: docker buildx build --platform linux/amd64,linux/arm64 -f regime-geral/Dockerfile regime-geral
ARG SRC_COMMIT=39363aa84103c6d58f7e8e76acb2f592691ec382
ARG MAVEN_VERSION=3.9.11

FROM eclipse-temurin:25-jdk@sha256:ce2ef9d2d67a68de5e196d4de9d743327c44a10b57fe02ec39f572c652cacfd3 AS build
ARG SRC_COMMIT
ARG MAVEN_VERSION
# The repo has no Maven wrapper jar: use a Maven pinned by checksum.
ADD --checksum=sha256:4b7195b6a4f5c81af4c0212677a32ee8143643401bc6e1e8412e6b06ea82beac \
    https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/${MAVEN_VERSION}/apache-maven-${MAVEN_VERSION}-bin.tar.gz /tmp/maven.tgz
RUN tar -xzf /tmp/maven.tgz -C /opt && ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/local/bin/mvn
ADD https://github.com/ReceitaFederal/calculadora-rtc-regime-regular.git#${SRC_COMMIT} /src
WORKDIR /src
# Database from the project's own Flyway migrations (V0073), then the JAR.
RUN mkdir -p calculadora/db && mvn -q flyway:migrate -Dflyway.configFiles=./flyway/flyway-pro.conf --batch-mode \
 && mvn -q package -DskipTests --batch-mode \
 && java -Djarmode=tools -jar target/api-regime-geral.jar extract --layers --destination /out
# sqlite-jdbc native lib extracted now, so the runtime needs no writable/executable /tmp.
RUN mkdir /native && cd /native && jar xf /out/dependencies/lib/sqlite-jdbc-*.jar org/sqlite/native/Linux/$(uname -m)/libsqlitejdbc.so \
 && mv org/sqlite/native/Linux/$(uname -m)/libsqlitejdbc.so . && rm -rf org
COPY Healthcheck.java /hc/
RUN javac -d /hc /hc/Healthcheck.java
# Minimal JRE; java.net.http is for the healthcheck; jdk.localedata keeps pt-BR number formatting (memoriaCalculo).
RUN MODS=$(jdeps --ignore-missing-deps --print-module-deps --multi-release 25 --recursive -cp "/out/dependencies/lib/*:/out/application" /out/application/api-regime-geral.jar 2>/dev/null || true) \
 && jlink --add-modules "${MODS},java.net.http,java.sql,java.naming,java.management,java.instrument,jdk.unsupported,jdk.crypto.ec,java.desktop,java.security.jgss,java.xml.crypto,jdk.localedata" --include-locales=en,pt \
      --strip-debug --no-man-pages --no-header-files --compress zip-6 --output /jre

FROM gcr.io/distroless/cc-debian13:nonroot@sha256:29ee70762214fe8190cea04ef1c0a84a8365879e047ce8f911ae5a5ab2355fa2
ARG SRC_COMMIT
LABEL org.opencontainers.image.title="regime-geral" \
      org.opencontainers.image.version="1.7.2-V0073" \
      org.opencontainers.image.source="https://github.com/ReceitaFederal/calculadora-rtc-regime-regular" \
      org.opencontainers.image.revision="${SRC_COMMIT}" \
      org.opencontainers.image.licenses="MIT" \
      org.opencontainers.image.description="Unofficial build. No link with Receita Federal or Serpro."
COPY --from=build /jre /opt/jre
COPY --from=build /hc/Healthcheck.class /app/
COPY --from=build /native/libsqlitejdbc.so /app/native/libsqlitejdbc.so
WORKDIR /app
COPY --from=build /out/dependencies/ ./
COPY --from=build /out/spring-boot-loader/ ./
COPY --from=build /out/snapshot-dependencies/ ./
COPY --from=build /out/application/ ./
COPY --from=build /src/calculadora/db/calculadora-pro.db /app/calculadora/db/calculadora-pro.db
# -XX:-UseBASE64Intrinsics: OpenJDK CVE workaround used by the official package; keep until the base image fixes it.
# Run with --read-only --tmpfs /tmp (Tomcat needs a writable tmp; noexec is fine, the sqlite lib is already in /app/native).
ENV JAVA_TOOL_OPTIONS="-XX:-UseBASE64Intrinsics -XX:MaxRAMPercentage=75 -Dorg.sqlite.lib.path=/app/native -Dorg.sqlite.lib.name=libsqlitejdbc.so" \
    SPRING_PROFILES_ACTIVE=offline \
    MANAGEMENT_SERVER_PORT=9000 \
    SERVER_SHUTDOWN=graceful \
    LOGGING_STRUCTURED_FORMAT_CONSOLE=ecs
EXPOSE 8080 9000
USER nonroot
HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 CMD ["/opt/jre/bin/java","-Xshare:auto","-cp","/app","Healthcheck"]
ENTRYPOINT ["/opt/jre/bin/java","-jar","/app/api-regime-geral.jar"]

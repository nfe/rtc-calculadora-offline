package br.gov.serpro.rtc.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * Valida o nome do arquivo do banco de dados com base no profile ativo.
 */
@Slf4j
//@Component
// FIXME Descomentar a anotação @Component após os testes de RNF
public class DatabaseValidator {

    private static final String[] PRODUCTION_PROFILES = { "pro", "offline" };
    private static final String NONPRODUCTION_DB_FILENAME_SUFFIX = "-nonpro.db";
    private static final String PRODUCTION_DB_FILENAME_SUFFIX = "-pro.db";

    @Value("${application.db.filename}")
    private String dbFileName;

    @Autowired
    private Environment env;

    @PostConstruct
    public void validateDatabaseName() throws Exception {
        if (dbFileName == null || dbFileName.isBlank() || dbFileName.contains(" ")) {
            throw new IllegalStateException("Nome do arquivo do banco de dados não pode ser nulo, vazio ou conter espaços.");
        }
        log.info(">>> Banco de dados em uso: [{}] <<<", dbFileName);
        if (env.matchesProfiles(PRODUCTION_PROFILES)) {
            validateDatabaseName(PRODUCTION_DB_FILENAME_SUFFIX);
        } else {
            validateDatabaseName(NONPRODUCTION_DB_FILENAME_SUFFIX);
        }
    }

    private void validateDatabaseName(String dbSuffix) {
        if (!dbFileName.endsWith(dbSuffix)) {
            final String profilesActive = String.join(",", env.getActiveProfiles());
            final var msg = String.format(
                    "Nome do banco de dados '%s' inválido para profiles '%s'. Deve terminar com '%s'", dbFileName,
                    profilesActive, dbSuffix);
            throw new IllegalStateException(msg);
        }
    }

}
package integrationtests;

import org.epos.handler.dbapi.service.EntityManagerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.logging.Logger;

public class TestcontainersLifecycle {

    protected static Logger LOG = Logger.getGlobal();
    public static PostgreSQLContainer<?> METADATA_CATALOGUE;

    @BeforeAll
    static void startContainers() {
        METADATA_CATALOGUE = new PostgreSQLContainer<>(
                DockerImageName.parse("epos/metadata-database-deploy:latest")
                        .asCompatibleSubstituteFor("postgres")

        ).withDatabaseName("cerif").withUsername("postgres").withPassword("changeme");

        METADATA_CATALOGUE.start();

        new EntityManagerService.EntityManagerServiceBuilder()
                .setPostgresqlHost(METADATA_CATALOGUE.getHost())
                .setPostgresqlDBName(METADATA_CATALOGUE.getDatabaseName())
                .setPostgresqlUsername(METADATA_CATALOGUE.getUsername())
                .setPostgresqlPassword(METADATA_CATALOGUE.getPassword())
                .build();
    }

    @AfterAll
    static void stopContainers() {
        METADATA_CATALOGUE.stop();
    }

}


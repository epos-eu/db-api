package org.epos.handler.dbapi.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.HashMap;

public class EntityManagerFactoryProvider {

    private static final String PERSISTENCE_NAME_DEFAULT = "EPOSDataModel";
    private static final String CONNECTION_POOL_MAX_SIZE_DEFAULT = "10";
    private static final String CONNECTION_MAX_LIFETIME_DEFAULT = "60000";
    private static final String CONNECTION_TEST_IDLE_INTERVAL_TIME_DEFAULT = "30000";
    private static EntityManagerFactory instance;

    private EntityManagerFactoryProvider() {
    }

    public static synchronized EntityManagerFactory getInstance() {
        if (instance == null) {

            String persistenceName = System.getenv("PERSISTENCE_NAME");
            persistenceName = persistenceName == null ? PERSISTENCE_NAME_DEFAULT : persistenceName;

            String pool_max_size = System.getenv("CONNECTION_POOL_MAX_SIZE");
            pool_max_size = pool_max_size == null ? CONNECTION_POOL_MAX_SIZE_DEFAULT : pool_max_size;

            String max_connection_lifetime = System.getenv("CONNECTION_MAX_LIFETIME");
            max_connection_lifetime = max_connection_lifetime == null ? CONNECTION_MAX_LIFETIME_DEFAULT : max_connection_lifetime;

            String keep_alive_time = System.getenv("CONNECTION_TEST_IDLE_INTERVAL_TIME");
            keep_alive_time = keep_alive_time == null ? CONNECTION_TEST_IDLE_INTERVAL_TIME_DEFAULT : keep_alive_time;

            HikariConfig hikariConfig = new HikariConfig();
            HashMap<String, Object> properties = new HashMap<>();
            hikariConfig.setMaximumPoolSize(Integer.parseInt(pool_max_size));
            hikariConfig.setMaxLifetime(Long.parseLong(max_connection_lifetime));
            hikariConfig.setKeepaliveTime(Long.parseLong(keep_alive_time));

            hikariConfig.setDriverClassName("org.postgresql.Driver");
            hikariConfig.setPoolName("cerif");
            hikariConfig.setConnectionTestQuery("SELECT 1");
            hikariConfig.setConnectionTimeout(1000);
            hikariConfig.setInitializationFailTimeout(9000);

            String connectionString = System.getenv("POSTGRESQL_CONNECTION_STRING");

            if (connectionString != null) {
                hikariConfig.setJdbcUrl(connectionString);
            } else {
                String dburl = "jdbc:postgresql://";

                dburl += System.getenv("POSTGRESQL_HOST");
                dburl += "/";
                dburl += System.getenv("POSTGRESQL_DBNAME");

                String user = System.getenv("POSTGRESQL_USERNAME");
                String password = System.getenv("POSTGRESQL_PASSWORD");

                hikariConfig.setJdbcUrl(dburl);
                hikariConfig.setUsername(user);
                hikariConfig.setPassword(password);
            }


            DataSource hikariDataSource = new HikariDataSource(hikariConfig);

            properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, hikariDataSource);
            instance = Persistence.createEntityManagerFactory(persistenceName, properties);

        }
        return instance;
    }

}

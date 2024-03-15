package dev.alexandreoliveira.gft.rabobank.travels.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.databases.write")
public record WritePostgresConfigurationProperties(
        String url,
        String username,
        String password,
        WriteHikariProperties hikari,
        WriteHibernateProperties hibernate
) {

    public record WriteHikariProperties(
            Integer minimumIdle,
            Integer maximumPoolSize,
            Long idleTimeout,
            Long maxLifetime,
            Long connectionTimeout,
            String poolName
    ) {}

    public record WriteHibernateProperties(
            String ddlAuto,
            Boolean showSql
    ) {}
}

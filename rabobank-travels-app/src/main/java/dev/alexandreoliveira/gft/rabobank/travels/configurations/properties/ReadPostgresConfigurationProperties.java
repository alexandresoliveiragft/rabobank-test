package dev.alexandreoliveira.gft.rabobank.travels.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.databases.read")
public record ReadPostgresConfigurationProperties(
        String url,
        String username,
        String password,
        ReadHikariProperties hikari,
        ReadHibernateProperties hibernate
) {
    public record ReadHikariProperties(
            Integer minimumIdle,
            Integer maximumPoolSize,
            Long idleTimeout,
            Long maxLifetime,
            Long connectionTimeout,
            String poolName
    ) {}

    public record ReadHibernateProperties(
            String ddlAuto,
            Boolean showSql
    ) {}
}

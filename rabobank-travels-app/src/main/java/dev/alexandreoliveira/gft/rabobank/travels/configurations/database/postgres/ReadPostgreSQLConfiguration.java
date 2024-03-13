package dev.alexandreoliveira.gft.rabobank.travels.configurations.database.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.alexandreoliveira.gft.rabobank.travels.configurations.properties.ReadPostgresConfigurationProperties;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.DestinationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.ReservationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.UserEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.destinations.ReadDestinationsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.reservations.ReadReservationsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.users.ReadUsersRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        ReadDestinationsRepository.class,
        ReadUsersRepository.class,
        ReadReservationsRepository.class
})
public class ReadPostgreSQLConfiguration {

    private final ReadPostgresConfigurationProperties properties;

    public ReadPostgreSQLConfiguration(ReadPostgresConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setReadOnly(true);
        hikariConfig.setJdbcUrl(properties.url());
        hikariConfig.setUsername(properties.username());
        hikariConfig.setPassword(properties.password());

        hikariConfig.setMinimumIdle(properties.hikari().minimumIdle());
        hikariConfig.setMaximumPoolSize(properties.hikari().maximumPoolSize());
        hikariConfig.setIdleTimeout(properties.hikari().idleTimeout());
        hikariConfig.setMaxLifetime(properties.hikari().maxLifetime());
        hikariConfig.setConnectionTimeout(properties.hikari().connectionTimeout());
        hikariConfig.setPoolName(properties.hikari().poolName());

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> hibernateProperties = new HashMap<>();
        hibernateProperties.put(AvailableSettings.HBM2DDL_AUTO, properties.hibernate().ddlAuto());
        hibernateProperties.put(AvailableSettings.SHOW_SQL, properties.hibernate().showSql());
        entityManager.setJpaPropertyMap(hibernateProperties);

        return entityManager;
    }

    @Bean(name = "readJpaUsersRepository")
    public JpaRepository<UserEntity, UUID> readJpaUsersRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(UserEntity.class, jpaContext.getEntityManagerByManagedType(UserEntity.class));
    }

    @Bean(name = "readJpaDestinationsRepository")
    public JpaRepository<DestinationEntity, UUID> readJpaDestinationsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(DestinationEntity.class, jpaContext.getEntityManagerByManagedType(DestinationEntity.class));
    }

    @Bean(name = "readJpaReservationsRepository")
    public JpaRepository<ReservationEntity, UUID> readJpaReservationsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(ReservationEntity.class, jpaContext.getEntityManagerByManagedType(ReservationEntity.class));
    }
}

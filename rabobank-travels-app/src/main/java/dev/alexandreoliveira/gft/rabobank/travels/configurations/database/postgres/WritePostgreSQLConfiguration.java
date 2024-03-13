package dev.alexandreoliveira.gft.rabobank.travels.configurations.database.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.alexandreoliveira.gft.rabobank.travels.configurations.properties.WritePostgresConfigurationProperties;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.DestinationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.FlightEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.GuestEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.HotelEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.ReservationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.SeatEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.TransferEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.UserEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.destinations.WriteDestinationsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.reservations.WriteReservationsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.users.WriteUsersRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {
        WriteDestinationsRepository.class,
        WriteUsersRepository.class,
        WriteReservationsRepository.class
})
@EnableJpaAuditing
public class WritePostgreSQLConfiguration {

    private final WritePostgresConfigurationProperties properties;

    public WritePostgreSQLConfiguration(WritePostgresConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("TravelsApp");
    }

    @Bean
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();
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

    @Bean(name = "writePostgreSQLTransactionManager")
    public PlatformTransactionManager writePostgreSQLTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "writeJpaUsersRepository")
    public JpaRepository<UserEntity, UUID> writeJpaUsersRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(UserEntity.class, jpaContext.getEntityManagerByManagedType(UserEntity.class));
    }

    @Bean(name = "writeJpaDestinationsRepository")
    public JpaRepository<DestinationEntity, UUID> writeJpaDestinationsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(DestinationEntity.class, jpaContext.getEntityManagerByManagedType(DestinationEntity.class));
    }

    @Bean(name = "writeJpaReservationsRepository")
    public JpaRepository<ReservationEntity, UUID> writeJpaReservationsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(ReservationEntity.class, jpaContext.getEntityManagerByManagedType(ReservationEntity.class));
    }

    @Bean(name = "writeJpaHotelsRepository")
    public JpaRepository<HotelEntity, UUID> writeJpaHotelsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(HotelEntity.class, jpaContext.getEntityManagerByManagedType(HotelEntity.class));
    }

    @Bean(name = "writeJpaGuestsRepository")
    public JpaRepository<GuestEntity, UUID> writeJpaGuestsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(GuestEntity.class, jpaContext.getEntityManagerByManagedType(GuestEntity.class));
    }

    @Bean(name = "writeJpaFlightsRepository")
    public JpaRepository<FlightEntity, UUID> writeJpaFlightsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(FlightEntity.class, jpaContext.getEntityManagerByManagedType(FlightEntity.class));
    }

    @Bean(name = "writeJpaSeatsRepository")
    public JpaRepository<SeatEntity, UUID> writeJpaSeatsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(SeatEntity.class, jpaContext.getEntityManagerByManagedType(SeatEntity.class));
    }

    @Bean(name = "writeJpaTransfersRepository")
    public JpaRepository<TransferEntity, UUID> writeJpaTransfersRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(TransferEntity.class, jpaContext.getEntityManagerByManagedType(TransferEntity.class));
    }

}
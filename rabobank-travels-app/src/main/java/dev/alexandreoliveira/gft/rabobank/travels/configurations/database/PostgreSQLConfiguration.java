package dev.alexandreoliveira.gft.rabobank.travels.configurations.database;

import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.DestinationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.FlightEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.GuestEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.HotelEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.ReservationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.SeatEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.TransferEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories"})
@EnableJpaAuditing
public class PostgreSQLConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("TravelsApp");
    }

    @Bean(name = "jpaUsersRepository")
    public JpaRepository<UserEntity, UUID> jpaUsersRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(UserEntity.class, jpaContext.getEntityManagerByManagedType(UserEntity.class));
    }

    @Bean(name = "jpaDestinationsRepository")
    public JpaRepository<DestinationEntity, UUID> jpaDestinationsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(DestinationEntity.class, jpaContext.getEntityManagerByManagedType(DestinationEntity.class));
    }

    @Bean(name = "jpaReservationsRepository")
    public JpaRepository<ReservationEntity, UUID> jpaReservationsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(ReservationEntity.class, jpaContext.getEntityManagerByManagedType(ReservationEntity.class));
    }

    @Bean(name = "jpaHotelsRepository")
    public JpaRepository<HotelEntity, UUID> jpaHotelsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(HotelEntity.class, jpaContext.getEntityManagerByManagedType(HotelEntity.class));
    }

    @Bean(name = "jpaGuestsRepository")
    public JpaRepository<GuestEntity, UUID> jpaGuestsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(GuestEntity.class, jpaContext.getEntityManagerByManagedType(GuestEntity.class));
    }

    @Bean(name = "jpaFlightsRepository")
    public JpaRepository<FlightEntity, UUID> jpaFlightsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(FlightEntity.class, jpaContext.getEntityManagerByManagedType(FlightEntity.class));
    }

    @Bean(name = "jpaSeatsRepository")
    public JpaRepository<SeatEntity, UUID> jpaSeatsRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(SeatEntity.class, jpaContext.getEntityManagerByManagedType(SeatEntity.class));
    }

    @Bean(name = "jpaTransfersRepository")
    public JpaRepository<TransferEntity, UUID> jpaTransfersRepository(JpaContext jpaContext) {
        return new SimpleJpaRepository<>(TransferEntity.class, jpaContext.getEntityManagerByManagedType(TransferEntity.class));
    }
}
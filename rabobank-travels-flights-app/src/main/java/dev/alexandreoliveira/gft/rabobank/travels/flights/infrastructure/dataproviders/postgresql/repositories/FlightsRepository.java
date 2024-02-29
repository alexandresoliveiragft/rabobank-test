package dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.dataproviders.postgresql.repositories;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.FlightModel;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.flights.create.FlightsCreateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.flights.index.FlightsIndexRepository;
import dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.dataproviders.postgresql.entites.FlightEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FlightsRepository implements FlightsCreateRepository, FlightsIndexRepository {
    private final JpaRepository<FlightEntity, UUID> jpaRepository;
    private final EntityManager entityManager;

    public FlightsRepository(@Qualifier("FlightsRepository") JpaRepository<FlightEntity, UUID> jpaRepository, JpaContext jpaContext) {
        this.jpaRepository = jpaRepository;
        this.entityManager = jpaContext.getEntityManagerByManagedType(FlightEntity.class);
    }

    @Override
    public FlightModel save(FlightModel model) {
        var entity = (FlightEntity) model;
        return jpaRepository.save(entity);
    }

    @Override
    public List<? extends FlightModel> findAllByParams(FlightModel flightModel) {
        return entityManager.createNativeQuery("""
                        select * from tbl_flights tf where tf.origin ilike :origin and tf.destiny ilike :destiny
                        """, FlightEntity.class)
                .setParameter("origin", flightModel.getOrigin())
                .setParameter("destiny", flightModel.getDestiny())
                .getResultList();
    }
}

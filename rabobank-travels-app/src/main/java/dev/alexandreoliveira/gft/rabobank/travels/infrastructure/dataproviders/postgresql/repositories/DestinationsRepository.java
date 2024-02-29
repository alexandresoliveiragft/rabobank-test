package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.create.DestinationsCreateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.index.DestinationsIndexRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.update.DestinationsUpdateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.exceptions.DataProvidersException;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.DestinationEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DestinationsRepository implements DestinationsCreateRepository, DestinationsUpdateRepository, DestinationsIndexRepository {


    private final JpaRepository<DestinationEntity, UUID> jpaRepository;

    public DestinationsRepository(@Qualifier("jpaDestinationsRepository") JpaRepository<DestinationEntity, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public DestinationModel save(DestinationModel model) {
        var entity = (DestinationEntity) model;
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        List<DestinationEntity> destinations = jpaRepository.findAll(Example.of(entity, exampleMatcher));
        if (!destinations.isEmpty()) {
            throw new DataProvidersException("Destination exists");
        }
        return jpaRepository.save(entity);
    }

    @Override
    public DestinationModel update(DestinationModel model) {
        var actualEntity = (DestinationEntity) model;
        Optional<DestinationEntity> optionalDestination = jpaRepository.findById(actualEntity.getId());
        if (optionalDestination.isEmpty()) {
            return save(model);
        }
        DestinationEntity destination = optionalDestination.get();
        destination.setCity(model.getCity());
        destination.setState(model.getState());
        return jpaRepository.save(destination);
    }

    @Override
    public List<? extends DestinationModel> findAllByParams(DestinationModel model) {
        var entity = (DestinationEntity) model;
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase("city", "state");
        return jpaRepository.findAll(Example.of(entity, exampleMatcher));
    }
}

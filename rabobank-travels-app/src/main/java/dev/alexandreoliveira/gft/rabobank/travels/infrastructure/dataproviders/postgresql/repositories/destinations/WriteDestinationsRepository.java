package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.destinations;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.create.DestinationsCreateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.update.DestinationsUpdateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.exceptions.DataProvidersException;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.DestinationEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Repository
public class WriteDestinationsRepository implements DestinationsCreateRepository, DestinationsUpdateRepository {

    private final JpaRepository<DestinationEntity, UUID> jpaRepository;

    public WriteDestinationsRepository(@Qualifier("writeJpaDestinationsRepository") JpaRepository<DestinationEntity, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public DestinationModel save(DestinationModel model) {
        return jpaRepository.save((DestinationEntity) model);
    }

    @Override
    public DestinationModel update(DestinationModel model) {
        Optional<DestinationEntity> optionalDestination = jpaRepository.findById(model.getId());

        if (optionalDestination.isPresent()) {
            DestinationEntity entity = optionalDestination.get();
            entity.setCity(StringUtils.hasText(model.getCity()) ? model.getCity() : entity.getCity());
            entity.setState(StringUtils.hasText(model.getState())? model.getState() : entity.getState());
            return jpaRepository.save(entity);
        }

        throw new DataProvidersException("Destination not found");
    }
}

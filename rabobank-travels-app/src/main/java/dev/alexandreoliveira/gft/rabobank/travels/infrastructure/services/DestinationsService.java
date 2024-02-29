package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.services;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.create.DestinationsCreateUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.index.DestinationsIndexUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.update.DestinationsUpdateUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.DestinationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories.DestinationsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.create.DestinationsControllerCreateRequest;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.create.DestinationsControllerCreateResponse;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.index.DestinationsControllerIndexRequest;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.index.DestinationsControllerIndexResponse;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.update.DestinationsControllerUpdateRequest;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.update.DestinationsControllerUpdateResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Throwable.class})
public class DestinationsService extends BaseService {
    private final DestinationsCreateUseCase destinationsCreateUseCase;
    private final DestinationsUpdateUseCase destinationsUpdateUseCase;
    private final DestinationsIndexUseCase destinationsIndexUseCase;

    public DestinationsService(DestinationsRepository destinationsRepository) {
        this.destinationsCreateUseCase = new DestinationsCreateUseCase(destinationsRepository);
        this.destinationsUpdateUseCase = new DestinationsUpdateUseCase(destinationsRepository);
        this.destinationsIndexUseCase = new DestinationsIndexUseCase(destinationsRepository);
    }

    public DestinationsControllerCreateResponse create(
            DestinationsControllerCreateRequest request) {
        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setCity(request.city());
        destinationEntity.setState(request.state());
        OutputDTO<DestinationModel> output = destinationsCreateUseCase.execute(destinationEntity);
        hasErrors(output, "Erro ao criar um novo destino");
        return new DestinationsControllerCreateResponse(
                output.data().getId(),
                output.data().getCity(),
                output.data().getState());
    }

    public DestinationsControllerUpdateResponse update(
            DestinationsControllerUpdateRequest request) {
        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setId(request.id());
        destinationEntity.setCity(request.city());
        destinationEntity.setState(request.state());
        OutputDTO<DestinationModel> output = destinationsUpdateUseCase.execute(destinationEntity);
        hasErrors(output, "Erro ao atualizar o destino");
        return new DestinationsControllerUpdateResponse(output.data());
    }


    public DestinationsControllerIndexResponse index(DestinationsControllerIndexRequest request) {
        var entity = new DestinationEntity();
        entity.setCity(request.term());
        entity.setState(request.term());
        OutputDTO<List<? extends DestinationModel>> output = destinationsIndexUseCase.execute(entity);
        hasErrors(output, "Error to recover list of destinations");
        List<DestinationsControllerIndexResponse.Destination> destinations = output.data().stream().map(DestinationsControllerIndexResponse.Destination::new).toList();
        return new DestinationsControllerIndexResponse(destinations);
    }
}

package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.index;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.IUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.core.utils.validators.ModelValidatorUtil;
import dev.alexandreoliveira.gft.rabobank.travels.core.utils.validators.groups.OnDestinationsIndex;

import java.util.List;

public class DestinationsIndexUseCase implements IUseCase.InOut<DestinationModel, List<? extends DestinationModel>> {

    private final DestinationsIndexRepository repository;

    public DestinationsIndexUseCase(DestinationsIndexRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputDTO<List<? extends DestinationModel>> execute(DestinationModel model) {
        List<String> errors = ModelValidatorUtil.isValid(model, OnDestinationsIndex.class);
        if (!errors.isEmpty()) {
            return new OutputDTO<>(errors);
        }
        return new OutputDTO<>(repository.findAllByParams(model));
    }
}

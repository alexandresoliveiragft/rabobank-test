package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.update;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.IUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.core.utils.validators.ModelValidatorUtil;

import java.util.List;

public record DestinationsUpdateUseCase(
        DestinationsUpdateRepository repository
) implements IUseCase<DestinationModel, DestinationModel> {
    @Override
    public OutputDTO<DestinationModel> execute(DestinationModel input) {
        List<String> errors = ModelValidatorUtil.isValid(input);
        if (!errors.isEmpty()) {
            return new OutputDTO<>(errors);
        }
        DestinationModel model = repository.update(input);
        return new OutputDTO<>(model);
    }
}

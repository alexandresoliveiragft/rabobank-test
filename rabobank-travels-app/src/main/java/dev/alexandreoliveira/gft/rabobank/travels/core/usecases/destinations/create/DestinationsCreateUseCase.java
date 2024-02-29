package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.create;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.IUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.core.utils.validators.ModelValidatorUtil;

import java.util.List;

public record DestinationsCreateUseCase(
        DestinationsCreateRepository repository
) implements IUseCase<DestinationModel, DestinationModel> {
    @Override
    public OutputDTO<DestinationModel> execute(DestinationModel input) {
        List<String> errors = ModelValidatorUtil.isValid(input);
        if (!errors.isEmpty()) {
            return new OutputDTO<>(errors);
        }
        DestinationModel model = repository.save(input);
        return new OutputDTO<>(model);
    }
}

package dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.create;

import dev.alexandreoliveira.gft.aodev.travels.core.exceptions.CoreException;
import dev.alexandreoliveira.gft.aodev.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.aodev.travels.core.usecases.IUseCase;
import dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.commands.DestinationsCreateCommand;
import dev.alexandreoliveira.gft.aodev.travels.core.utils.validators.ModelValidatorUtil;

import java.util.List;

public class DestinationsCreateUseCase implements IUseCase.In<DestinationModel> {

    private final DestinationsCreateCommand command;

    public DestinationsCreateUseCase(DestinationsCreateCommand command) {
        this.command = command;
    }

    @Override
    public void execute(DestinationModel input) {
        List<String> errors = ModelValidatorUtil.isValid(input);
        if (!errors.isEmpty()) {
            throw new CoreException(
                    errors,
                    getClass().getName(),
                    "Error to validate input"
            );
        }
        command.publishCreate(input);
    }
}

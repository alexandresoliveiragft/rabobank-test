package dev.alexandreoliveira.gft.aodev.travels.core.usecases.users.show;

import dev.alexandreoliveira.gft.aodev.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.aodev.travels.core.models.UserModel;
import dev.alexandreoliveira.gft.aodev.travels.core.usecases.IUseCase;

import java.util.UUID;

public record UsersShowUseCase(
        UsersShowRepository repository
) implements IUseCase.InOut<UUID, UserModel> {

    @Override
    public OutputDTO<UserModel> execute(UUID id) {
        return new OutputDTO<>(repository.findUserById(id));
    }
}

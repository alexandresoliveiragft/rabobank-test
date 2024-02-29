package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.users.show;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.UserModel;

import java.util.UUID;

public interface UsersShowRepository {

    UserModel findUserById(UUID id);
}

package dev.alexandreoliveira.gft.aodev.travels.core.usecases.users.show;

import dev.alexandreoliveira.gft.aodev.travels.core.models.UserModel;

import java.util.UUID;

public interface UsersShowRepository {

    UserModel findUserById(UUID id);
}

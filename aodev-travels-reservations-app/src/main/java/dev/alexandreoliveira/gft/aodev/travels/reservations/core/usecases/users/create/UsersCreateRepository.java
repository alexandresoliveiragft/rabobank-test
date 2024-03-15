package dev.alexandreoliveira.gft.aodev.travels.core.usecases.users.create;

import dev.alexandreoliveira.gft.aodev.travels.core.models.UserModel;

public interface UsersCreateRepository {

    UserModel save(UserModel model);
}

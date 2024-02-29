package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.users.create;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.UserModel;

public interface UsersCreateRepository {

    UserModel save(UserModel model);
}

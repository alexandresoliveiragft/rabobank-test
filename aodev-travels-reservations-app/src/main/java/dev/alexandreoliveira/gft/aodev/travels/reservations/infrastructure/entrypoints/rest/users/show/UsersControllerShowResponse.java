package dev.alexandreoliveira.gft.aodev.travels.infrastructure.entrypoints.rest.users.show;

import dev.alexandreoliveira.gft.aodev.travels.core.models.UserModel;

import java.util.UUID;

public record UsersControllerShowResponse(
        UUID id,
        String name,
        String email
) {

    public UsersControllerShowResponse(UserModel userModel) {
        this(userModel.getId(), userModel.getName(), userModel.getEmail());
    }
}
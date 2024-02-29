package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.users.create;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.UserModel;

import java.util.UUID;

public record UsersControllerCreateResponse(
        UUID id,
        String name,
        String email
) {

    public UsersControllerCreateResponse(UserModel data) {
        this(data.getId(), data.getName(), data.getEmail());
    }
}

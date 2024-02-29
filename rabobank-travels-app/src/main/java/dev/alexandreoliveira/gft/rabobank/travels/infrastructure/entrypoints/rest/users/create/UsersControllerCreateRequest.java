package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.users.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UsersControllerCreateRequest(
        @NotNull @NotEmpty String name,
        @NotNull @Email String email
) {}

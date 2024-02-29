package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.users.create;

import java.util.UUID;

public record UsersCreateUseCaseOutput(
        UUID id,
        String name,
        String email
) {
}

package dev.alexandreoliveira.gft.aodev.travels.core.usecases.users.create;

import java.util.UUID;

public record UsersCreateUseCaseOutput(
        UUID id,
        String name,
        String email
) {
}

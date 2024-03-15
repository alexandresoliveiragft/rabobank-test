package dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.create;

import java.util.UUID;

public record DestinationsCreateUseCaseOutput(
        UUID id,
        String city,
        String country
) {
}

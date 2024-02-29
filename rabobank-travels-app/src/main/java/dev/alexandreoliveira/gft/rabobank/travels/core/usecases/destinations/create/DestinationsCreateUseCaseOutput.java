package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.create;

import java.util.UUID;

public record DestinationsCreateUseCaseOutput(
        UUID id,
        String city,
        String country
) {
}

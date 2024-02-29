package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.update;

import java.util.UUID;

public record DestinationsUpdateUseCaseOutput(
        UUID id,
        String city,
        String country
) {
}

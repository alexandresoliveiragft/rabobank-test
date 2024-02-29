package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.create;

import java.util.UUID;

public record DestinationsControllerCreateResponse(
        UUID id,
        String city,
        String state
) {
}

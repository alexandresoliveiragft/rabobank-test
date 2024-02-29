package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DestinationsControllerCreateRequest(
        @NotNull @NotEmpty String city,
        @NotNull @NotEmpty String state
) {
}

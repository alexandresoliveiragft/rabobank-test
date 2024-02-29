package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DestinationsControllerUpdateRequest(
        @NotNull UUID id,
        @NotNull @NotEmpty String city,
        @NotNull @NotEmpty String state
) {}

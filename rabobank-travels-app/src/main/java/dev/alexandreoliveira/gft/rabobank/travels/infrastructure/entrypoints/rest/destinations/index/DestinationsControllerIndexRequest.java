package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.rest.destinations.index;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DestinationsControllerIndexRequest(
        @NotNull @NotEmpty @Size(min = 3) String term
) {
}

package dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.entrypoints.rest.hotels.index;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HotelsIntexControllerRequest(
        @NotNull @NotEmpty @Size(min = 3) String term
) {
}

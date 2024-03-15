package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.events.dto.destinations;

import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.events.dto.Event;

public record CreateDestinationEvent(
        String city,
        String state
) implements Event {}

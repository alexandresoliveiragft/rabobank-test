package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.events.dto.destinations;

import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.events.dto.Event;

import java.util.UUID;

public record DeleteDestinationEvent(
        UUID id
) implements Event {}

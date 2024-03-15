package dev.alexandreoliveira.gft.aodev.travels.infrastructure.events.dto.destinations;

import dev.alexandreoliveira.gft.aodev.travels.infrastructure.events.dto.Event;

import java.util.UUID;

public record DeleteDestinationEvent(
        UUID id
) implements Event {}

package dev.alexandreoliveira.gft.aodev.travels.infrastructure.entrypoints.rest.reservations.create;

import java.util.UUID;

public record ReservationsCreateControllerResponse(
   UUID id,
   Boolean status
) {}

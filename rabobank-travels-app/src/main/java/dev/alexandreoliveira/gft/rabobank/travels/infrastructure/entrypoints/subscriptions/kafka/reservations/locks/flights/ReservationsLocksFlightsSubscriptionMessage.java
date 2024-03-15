package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.flights;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record ReservationsLocksFlightsSubscriptionMessage(
        List<ReservedSeats> reservations
) {
    public record ReservedSeats(
            UUID reservationId,
            UUID flightId,
            UUID seatId
    ) {}
}

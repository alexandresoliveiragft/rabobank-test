package dev.alexandreoliveira.gft.aodev.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.hotels;

import java.util.UUID;

public record ReservationsLocksHotelsSubscriptionMessage(
        UUID reservationId,
        UUID hotelId,
        UUID roomId
) {
}

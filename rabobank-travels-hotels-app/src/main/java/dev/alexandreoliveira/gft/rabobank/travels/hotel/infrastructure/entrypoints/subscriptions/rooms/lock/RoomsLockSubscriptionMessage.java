package dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.entrypoints.subscriptions.rooms.lock;

import java.util.UUID;

public record RoomsLockSubscriptionMessage(
        UUID roomId,
        UUID hotelId,
        UUID reservationId
) {
}

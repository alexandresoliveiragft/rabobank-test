package dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.entrypoints.subscriptions.rooms.unlock;

import java.util.UUID;

public record RoomsUnlockSubscriptionMessage(
        UUID roomId,
        UUID hotelId,
        UUID reservationId
) {
}

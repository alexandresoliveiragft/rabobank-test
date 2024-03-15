package dev.alexandreoliveira.gft.aodev.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.transfers;

import java.util.List;
import java.util.UUID;

public record ReservationsLocksTransfersSubscriptionMessage(
        List<ReservedTransfer> reservedTransfers
) {
    public record ReservedTransfer(
            UUID reservationId,
            UUID transferId
    ) {}
}

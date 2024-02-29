package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.transfers;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ReservationsLocksTransfersSubscriptionMessage {
    private List<ReservedTransfer> reservedTransfers;

    public List<ReservedTransfer> getReservedTransfers() {
        return reservedTransfers;
    }

    public void setReservedTransfers(List<ReservedTransfer> reservedTransfers) {
        this.reservedTransfers = reservedTransfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationsLocksTransfersSubscriptionMessage that = (ReservationsLocksTransfersSubscriptionMessage) o;
        return Objects.equals(reservedTransfers, that.reservedTransfers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservedTransfers);
    }

    public static class ReservedTransfer {
        private UUID reservationId;
        private UUID transferId;

        public UUID getReservationId() {
            return reservationId;
        }

        public void setReservationId(UUID reservationId) {
            this.reservationId = reservationId;
        }

        public UUID getTransferId() {
            return transferId;
        }

        public void setTransferId(UUID transferId) {
            this.transferId = transferId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReservedTransfer that = (ReservedTransfer) o;
            return Objects.equals(reservationId, that.reservationId) && Objects.equals(transferId, that.transferId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reservationId, transferId);
        }
    }
}

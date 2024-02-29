package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.flights;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ReservationsLocksFlightsSubscriptionMessage {

    private List<ReservedSeats> reservations;

    public List<ReservedSeats> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservedSeats> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationsLocksFlightsSubscriptionMessage that = (ReservationsLocksFlightsSubscriptionMessage) o;
        return Objects.equals(reservations, that.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservations);
    }

    public static class ReservedSeats {
        private UUID reservationId;
        private UUID flightId;
        private UUID seatId;

        public UUID getReservationId() {
            return reservationId;
        }

        public void setReservationId(UUID reservationId) {
            this.reservationId = reservationId;
        }

        public UUID getFlightId() {
            return flightId;
        }

        public void setFlightId(UUID flightId) {
            this.flightId = flightId;
        }

        public UUID getSeatId() {
            return seatId;
        }

        public void setSeatId(UUID seatId) {
            this.seatId = seatId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReservedSeats that = (ReservedSeats) o;
            return Objects.equals(reservationId, that.reservationId) && Objects.equals(flightId, that.flightId) && Objects.equals(seatId, that.seatId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reservationId, flightId, seatId);
        }
    }
}

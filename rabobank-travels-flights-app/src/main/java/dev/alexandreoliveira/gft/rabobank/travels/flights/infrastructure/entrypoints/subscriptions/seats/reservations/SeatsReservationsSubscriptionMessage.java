package dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.entrypoints.subscriptions.seats.reservations;

import java.util.List;
import java.util.UUID;

public class SeatsReservationsSubscriptionMessage {

    private List<Seat> seats;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public static class Seat {
        private UUID flightId;
        private UUID seatId;
        private UUID reservationId;

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

        public UUID getReservationId() {
            return reservationId;
        }

        public void setReservationId(UUID reservationId) {
            this.reservationId = reservationId;
        }
    }
}

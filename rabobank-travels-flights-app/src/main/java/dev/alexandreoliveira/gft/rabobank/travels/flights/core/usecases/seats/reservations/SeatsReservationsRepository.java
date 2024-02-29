package dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.seats.reservations;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.SeatModel;

import java.util.List;

public interface SeatsReservationsRepository {

    SeatModel reservationSeats(SeatModel seat);
}

package dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.seats.index;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.SeatModel;

import java.util.List;

public interface SeatsIndexRepository {

    List<? extends SeatModel> findAvailableSeatsByFlight(SeatModel seatModel);
}

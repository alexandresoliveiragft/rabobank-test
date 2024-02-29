package dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.seats.create;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.SeatModel;

public interface SeatsCreateRepository {

    SeatModel save(SeatModel model);
}

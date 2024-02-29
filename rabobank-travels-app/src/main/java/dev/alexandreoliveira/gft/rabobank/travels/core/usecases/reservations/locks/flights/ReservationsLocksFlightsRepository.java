package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.locks.flights;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.FlightModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.ReservationModel;

import java.util.List;
import java.util.UUID;

public interface ReservationsLocksFlightsRepository {

    ReservationModel lockFlightsForReservation(UUID reservationId, List<? extends FlightModel> flights);
}

package dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.flights.create;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.FlightModel;

public interface FlightsCreateRepository {

    FlightModel save(FlightModel model);
}

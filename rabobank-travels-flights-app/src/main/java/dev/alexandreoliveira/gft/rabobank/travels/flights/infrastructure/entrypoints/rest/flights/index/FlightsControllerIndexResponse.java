package dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.entrypoints.rest.flights.index;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.FlightModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FlightsControllerIndexResponse(
        List<Flight> flights
) {

    public record Flight(
            UUID id,
            String company,
            String origin,
            String destiny,
            List<Seat> availableSeats,
            LocalDateTime checkIn,
            LocalDateTime checkOut
    ) {

        public Flight(FlightModel model) {
            this(
                    model.getId(),
                    model.getCompany(),
                    model.getOrigin(),
                    model.getDestiny(),
                    model.getSeats().stream().map(seatModel -> new Seat(seatModel.getId(), seatModel.getSeatNumber())).toList(),
                    model.getCheckIn(),
                    model.getCheckOut());
        }
    }

    public record Seat(
            UUID id,
            String seatNumber
    ) {}
}

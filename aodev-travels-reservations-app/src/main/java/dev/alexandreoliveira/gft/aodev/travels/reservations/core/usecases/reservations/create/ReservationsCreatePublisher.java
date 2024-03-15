package dev.alexandreoliveira.gft.aodev.travels.core.usecases.reservations.create;

import dev.alexandreoliveira.gft.aodev.travels.core.models.FlightModel;
import dev.alexandreoliveira.gft.aodev.travels.core.models.HotelModel;
import dev.alexandreoliveira.gft.aodev.travels.core.models.TransferModel;

import java.util.List;
import java.util.UUID;

public interface ReservationsCreatePublisher {

    void lockHotels(UUID reservationId, HotelModel hotels);

    void lockTransfers(UUID reservationId, List<? extends TransferModel> transfers);

    void lockFlights(UUID reservationId, List<? extends FlightModel> flights);
}

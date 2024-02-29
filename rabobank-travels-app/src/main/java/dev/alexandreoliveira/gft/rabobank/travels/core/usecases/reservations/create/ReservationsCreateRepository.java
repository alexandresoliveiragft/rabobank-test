package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.create;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.FlightModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.HotelModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.ReservationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.TransferModel;

public interface ReservationsCreateRepository {

    ReservationModel save(ReservationModel model);

    HotelModel save(HotelModel model);

    FlightModel save(FlightModel model);

    TransferModel save(TransferModel model);
}

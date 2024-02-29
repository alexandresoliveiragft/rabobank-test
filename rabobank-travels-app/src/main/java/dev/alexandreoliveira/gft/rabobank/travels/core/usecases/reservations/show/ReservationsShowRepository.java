package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.show;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.ReservationModel;

public interface ReservationsShowRepository {

    ReservationModel findById(ReservationModel model);
}

package dev.alexandreoliveira.gft.aodev.travels.core.usecases.reservations.show;

import dev.alexandreoliveira.gft.aodev.travels.core.models.ReservationModel;

public interface ReservationsShowRepository {

    ReservationModel findById(ReservationModel model);
}

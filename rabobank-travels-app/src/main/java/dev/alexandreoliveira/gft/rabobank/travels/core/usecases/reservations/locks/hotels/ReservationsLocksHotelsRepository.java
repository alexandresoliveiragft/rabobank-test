package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.locks.hotels;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.ReservationModel;

import java.util.UUID;

public interface ReservationsLocksHotelsRepository {

    ReservationModel lockHotelForReservation(UUID reservationId, UUID hotelId);
}

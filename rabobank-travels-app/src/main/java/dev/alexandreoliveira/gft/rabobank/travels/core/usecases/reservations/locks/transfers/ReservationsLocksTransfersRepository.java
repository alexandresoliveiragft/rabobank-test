package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.locks.transfers;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.ReservationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.TransferModel;

import java.util.List;
import java.util.UUID;

public interface ReservationsLocksTransfersRepository {

    ReservationModel lockTransfersForReservation(UUID reservationId, List<? extends TransferModel> transfers);
}

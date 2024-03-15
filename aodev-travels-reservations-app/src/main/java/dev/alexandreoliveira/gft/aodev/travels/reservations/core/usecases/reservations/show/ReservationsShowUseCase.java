package dev.alexandreoliveira.gft.aodev.travels.core.usecases.reservations.show;

import dev.alexandreoliveira.gft.aodev.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.aodev.travels.core.models.ReservationModel;
import dev.alexandreoliveira.gft.aodev.travels.core.usecases.IUseCase;

public class ReservationsShowUseCase implements IUseCase.InOut<ReservationModel, ReservationModel> {

    private final ReservationsShowRepository repository;

    public ReservationsShowUseCase(ReservationsShowRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputDTO<ReservationModel> execute(ReservationModel reservationModel) {
        return new OutputDTO<>(repository.findById(reservationModel));
    }
}

package dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.services;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.SeatModel;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.seats.reservations.SeatsReservationsPublisher;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.seats.reservations.SeatsReservationsUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.dataproviders.postgresql.entites.FlightEntity;
import dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.dataproviders.postgresql.entites.SeatEntity;
import dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.dataproviders.postgresql.repositories.SeatsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.entrypoints.subscriptions.seats.reservations.SeatsReservationsSubscriptionMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Throwable.class})
public class SeatsService extends BaseService {

    private final SeatsReservationsUseCase seatsReservationsUseCase;

    public SeatsService(
            SeatsRepository seatsRepository,
            @Qualifier("seatsReservationsPublisher") SeatsReservationsPublisher seatsReservationsPublisher
    ) {
        this.seatsReservationsUseCase = new SeatsReservationsUseCase(seatsRepository, seatsReservationsPublisher);
    }

    public void reservation(SeatsReservationsSubscriptionMessage message) {
        List<SeatEntity> seats = message.getSeats().stream().map(seat -> {
            var flightEntity = new FlightEntity();
            flightEntity.setId(seat.getFlightId());

            var seatEntity = new SeatEntity();
            seatEntity.setId(seat.getSeatId());
            seatEntity.setExternalId(seat.getReservationId());
            seatEntity.setFlight(flightEntity);
            return seatEntity;
        }).toList();

        OutputDTO<List<SeatModel>> output = seatsReservationsUseCase.execute(seats);
        hasErrors(output, "Não foi possível reservar o assento.");
    }
}

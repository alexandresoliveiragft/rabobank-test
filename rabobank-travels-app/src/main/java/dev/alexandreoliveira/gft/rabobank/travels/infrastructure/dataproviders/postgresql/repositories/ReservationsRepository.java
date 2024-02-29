package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.FlightModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.GuestModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.HotelModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.ReservationModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.SeatModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.TransferModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.create.ReservationsCreateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.locks.flights.ReservationsLocksFlightsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.locks.hotels.ReservationsLocksHotelsRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.locks.transfers.ReservationsLocksTransfersRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.show.ReservationsShowRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.exceptions.DataProvidersException;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.FlightEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.GuestEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.HotelEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.ReservationEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.SeatEntity;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.TransferEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ReservationsRepository implements ReservationsCreateRepository, ReservationsLocksHotelsRepository, ReservationsLocksFlightsRepository, ReservationsLocksTransfersRepository, ReservationsShowRepository {

    private final JpaRepository<ReservationEntity, UUID> jpaReservationsRepository;
    private final JpaRepository<HotelEntity, UUID> jpaHotelsRepository;
    private final JpaRepository<GuestEntity, UUID> jpaGuestsRepository;
    private final JpaRepository<FlightEntity, UUID> jpaFlightsRepository;
    private final JpaRepository<SeatEntity, UUID> jpaSeatsRepository;
    private final JpaRepository<TransferEntity, UUID> jpaTransfersRepository;

    public ReservationsRepository(
            @Qualifier("jpaReservationsRepository") JpaRepository<ReservationEntity, UUID> jpaReservationsRepository,
            @Qualifier("jpaHotelsRepository") JpaRepository<HotelEntity, UUID> jpaHotelsRepository,
            @Qualifier("jpaGuestsRepository") JpaRepository<GuestEntity, UUID> jpaGuestsRepository,
            @Qualifier("jpaFlightsRepository") JpaRepository<FlightEntity, UUID> jpaFlightsRepository,
            @Qualifier("jpaSeatsRepository") JpaRepository<SeatEntity, UUID> jpaSeatsRepository,
            @Qualifier("jpaTransfersRepository") JpaRepository<TransferEntity, UUID> jpaTransfersRepository) {
        this.jpaReservationsRepository = jpaReservationsRepository;
        this.jpaHotelsRepository = jpaHotelsRepository;
        this.jpaGuestsRepository = jpaGuestsRepository;
        this.jpaFlightsRepository = jpaFlightsRepository;
        this.jpaSeatsRepository = jpaSeatsRepository;
        this.jpaTransfersRepository = jpaTransfersRepository;
    }

    @Override
    public ReservationModel save(ReservationModel model) {
        var entity = (ReservationEntity) model;
        return jpaReservationsRepository.save(entity);
    }

    @Override
    public HotelModel save(HotelModel model) {
        var entity = (HotelEntity) model;
        List<? extends GuestModel> guests = entity.getGuests().stream().toList();
        entity.setGuests(new ArrayList<>());
        HotelEntity savedHotel = jpaHotelsRepository.save(entity);
        List<GuestEntity> savedGuests = guests.stream().map(guest -> {
            var guestEntity = (GuestEntity) guest;
            guestEntity.setHotel(savedHotel);
            return jpaGuestsRepository.save(guestEntity);
        }).toList();
        savedHotel.setGuests(savedGuests);
        return savedHotel;
    }

    @Override
    public FlightModel save(FlightModel model) {
        var entity = (FlightEntity) model;
        List<? extends SeatModel> seats = entity.getSeats().stream().toList();
        entity.setSeats(new ArrayList<>());
        FlightEntity savedFlight = jpaFlightsRepository.save(entity);
        List<SeatEntity> savedSeats = seats.stream().map(seat -> {
            var seatEntity = (SeatEntity) seat;
            seatEntity.setFlight(savedFlight);
            return jpaSeatsRepository.save(seatEntity);
        }).toList();
        savedFlight.setSeats(savedSeats);
        return savedFlight;
    }

    @Override
    public TransferModel save(TransferModel model) {
        var entity = (TransferEntity) model;
        return jpaTransfersRepository.save(entity);
    }

    @Override
    public ReservationModel lockHotelForReservation(UUID reservationId, UUID hotelId) {
        Optional<ReservationEntity> optionalReservation = jpaReservationsRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new DataProvidersException("Reservation not found");
        }
        ReservationEntity reservationEntity = optionalReservation.get();
        var hotel = (HotelEntity) reservationEntity.getHotel();
        if (!hotel.getExternalId().equals(hotelId)) {
            throw new DataProvidersException("This hotel is not the same of reservation");
        }
        hotel.setStatus(true);
        return jpaReservationsRepository.save(reservationEntity);
    }

    @Override
    public ReservationModel lockFlightsForReservation(UUID reservationId, List<? extends FlightModel> flights) {
        Optional<ReservationEntity> optionalReservation = jpaReservationsRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new DataProvidersException("Reservation not found");
        }
        ReservationEntity reservationEntity = optionalReservation.get();
        reservationEntity.getFlights().forEach(flight -> {
            var flightEntity = (FlightEntity) flight;
            boolean hasFlightToReserve = flights.stream().anyMatch(model -> model.getExternalId().equals(flightEntity.getExternalId()));
            if (hasFlightToReserve) {
                flightEntity.setStatus(true);
            }
        });
        return jpaReservationsRepository.save(reservationEntity);
    }

    @Override
    public ReservationModel lockTransfersForReservation(UUID reservationId, List<? extends TransferModel> transfers) {
        Optional<ReservationEntity> optionalReservation = jpaReservationsRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new DataProvidersException("Reservation not found");
        }
        ReservationEntity reservationEntity = optionalReservation.get();
        reservationEntity.getTransfers().forEach(transfer -> {
            var transferEntity = (TransferEntity) transfer;
            boolean hasTransferToReserve = transfers.stream().anyMatch(model -> model.getExternalId().equals(transferEntity.getExternalId()));
            if (hasTransferToReserve) {
                transferEntity.setStatus(true);
            }
        });
        return jpaReservationsRepository.save(reservationEntity);
    }

    @Override
    public ReservationModel findById(ReservationModel model) {
        Optional<ReservationEntity> optionalReservation = jpaReservationsRepository.findById(model.getId());
        if (optionalReservation.isEmpty()) {
            throw new DataProvidersException("Reservation not found.");
        }
        return optionalReservation.get();
    }
}

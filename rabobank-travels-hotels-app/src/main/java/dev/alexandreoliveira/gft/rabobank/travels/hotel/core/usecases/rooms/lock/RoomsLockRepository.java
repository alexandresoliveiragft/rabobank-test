package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.rooms.lock;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.RoomModel;

import java.util.UUID;

public interface RoomsLockRepository {

    RoomModel lock(UUID roomId, UUID hotelId, UUID reservationId, Boolean available);
}

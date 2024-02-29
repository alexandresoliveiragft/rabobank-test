package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.rooms.unlock;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.RoomModel;

import java.util.UUID;

public interface RoomsUnlockRepository {

    RoomModel unlockRoom(UUID roomId, UUID hotelId, UUID reservationId, Boolean available);
}

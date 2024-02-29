package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.rooms.lock;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.RoomModel;

public interface RoomsLockReservationPublisher {

    void notifyForReservationLockRooms(RoomModel model);
}

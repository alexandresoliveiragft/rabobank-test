package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.rooms.create;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.RoomModel;

public interface RoomsCreateRepository {

    RoomModel save(RoomModel roomModel);
}

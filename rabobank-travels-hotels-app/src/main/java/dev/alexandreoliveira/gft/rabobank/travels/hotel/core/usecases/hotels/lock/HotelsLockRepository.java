package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.hotels.lock;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.HotelModel;

public interface HotelsLockRepository {

    HotelModel lockRoom(HotelModel hotelModel);
}

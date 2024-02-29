package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.hotels.create;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.HotelModel;

public interface HotelsCreateRepository {

    HotelModel save(HotelModel model);
}

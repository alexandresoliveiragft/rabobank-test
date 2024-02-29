package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.hotels.index;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.HotelModel;

import java.util.List;

public interface HotelsIndexRepository {

    List<? extends HotelModel> findByHotelParams(HotelModel model);
}

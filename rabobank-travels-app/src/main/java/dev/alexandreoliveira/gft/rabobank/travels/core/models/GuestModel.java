package dev.alexandreoliveira.gft.rabobank.travels.core.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface GuestModel extends BaseModel {

    @NotNull
    HotelModel getHotel();

    @NotNull
    @NotEmpty
    String getName();

    @NotNull
    Integer getAge();
}

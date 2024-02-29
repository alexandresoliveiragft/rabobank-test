package dev.alexandreoliveira.gft.rabobank.travels.flights.core.models;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.utils.validators.groups.OnFlightsIndex;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightModel extends BaseModel {

    @NotNull
    @NotEmpty
    String getCompany();

    @NotNull
    Integer getFlightNumber();

    List<? extends SeatModel> getSeats();

    @NotNull(groups = {OnFlightsIndex.class})
    @NotEmpty(groups = {OnFlightsIndex.class})
    String getOrigin();

    @NotNull(groups = {OnFlightsIndex.class})
    @NotEmpty(groups = {OnFlightsIndex.class})
    String getDestiny();

    @NotNull
    LocalDateTime getCheckIn();

    @NotNull
    LocalDateTime getCheckOut();

    Boolean getStatus();

    @NotNull
    BigDecimal getPrice();
}

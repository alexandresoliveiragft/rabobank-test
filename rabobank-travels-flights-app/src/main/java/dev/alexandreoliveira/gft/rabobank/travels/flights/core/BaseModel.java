package dev.alexandreoliveira.gft.rabobank.travels.flights.core;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BaseModel {

    UUID getId();

    LocalDateTime getCreatedAt();

    String getCreatedBy();

    LocalDateTime getUpdatedAt();

    String getUpdatedBy();

    LocalDateTime getVersion();
}

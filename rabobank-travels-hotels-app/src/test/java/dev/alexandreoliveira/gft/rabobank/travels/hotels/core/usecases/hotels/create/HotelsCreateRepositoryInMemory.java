package dev.alexandreoliveira.gft.rabobank.travels.hotels.core.usecases.hotels.create;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.HotelModel;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.hotels.create.HotelsCreateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.dateproviders.postgresql.entites.HotelEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class HotelsCreateRepositoryInMemory implements HotelsCreateRepository {
    private final Map<UUID, HotelEntity> store;

    public HotelsCreateRepositoryInMemory() {
        this.store = new HashMap<>();
    }

    @Override
    public HotelModel save(HotelModel model) {
        LocalDateTime now = LocalDateTime.now();

        var entity = (HotelEntity) model;
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(now);
        entity.setCreatedBy("Hotel Test App");
        entity.setVersion(now);

        store.put(entity.getId(), entity);

        return entity;
    }
}

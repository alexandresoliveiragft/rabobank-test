package dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.index;

import dev.alexandreoliveira.gft.aodev.travels.core.models.DestinationModel;

import java.util.List;

public interface DestinationsIndexRepository {

    List<? extends DestinationModel> findAllByParams(DestinationModel model);
}

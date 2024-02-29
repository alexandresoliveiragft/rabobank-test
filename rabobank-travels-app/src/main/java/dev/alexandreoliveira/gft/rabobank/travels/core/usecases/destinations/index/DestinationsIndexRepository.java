package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.index;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;

import java.util.List;

public interface DestinationsIndexRepository {

    List<? extends DestinationModel> findAllByParams(DestinationModel model);
}

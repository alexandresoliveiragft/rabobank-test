package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.update;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;

public interface DestinationsUpdateRepository {

    DestinationModel update(DestinationModel model);
}

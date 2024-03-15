package dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.update;

import dev.alexandreoliveira.gft.aodev.travels.core.models.DestinationModel;

public interface DestinationsUpdateRepository {

    DestinationModel update(DestinationModel model);
}

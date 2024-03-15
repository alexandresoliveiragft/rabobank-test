package dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.create;

import dev.alexandreoliveira.gft.aodev.travels.core.models.DestinationModel;

public interface DestinationsCreateRepository {

    DestinationModel save(DestinationModel model);
}

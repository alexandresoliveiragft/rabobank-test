package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.create;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;

public interface DestinationsCreateRepository {

    DestinationModel save(DestinationModel model);
}

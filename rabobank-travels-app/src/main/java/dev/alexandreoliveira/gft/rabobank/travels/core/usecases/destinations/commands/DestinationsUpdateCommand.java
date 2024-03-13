package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.destinations.commands;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.DestinationModel;

public interface DestinationsUpdateCommand {

    void publishUpdate(DestinationModel model);
}

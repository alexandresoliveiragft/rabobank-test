package dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.commands;

import dev.alexandreoliveira.gft.aodev.travels.core.models.DestinationModel;

public interface DestinationsCreateCommand {

    void publishCreate(DestinationModel model);
}

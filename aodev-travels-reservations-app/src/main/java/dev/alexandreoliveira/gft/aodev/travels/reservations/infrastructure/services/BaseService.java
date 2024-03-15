package dev.alexandreoliveira.gft.aodev.travels.infrastructure.services;

import dev.alexandreoliveira.gft.aodev.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.aodev.travels.infrastructure.services.exceptions.ServiceException;

class BaseService {

    void hasErrors(OutputDTO<?> output, String message) {
        if (!output.errors().isEmpty()) {
            throw new ServiceException(message, output.errors());
        }
    }
}

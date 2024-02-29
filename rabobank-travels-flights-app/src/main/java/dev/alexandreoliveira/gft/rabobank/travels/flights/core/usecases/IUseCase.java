package dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases;

import dev.alexandreoliveira.gft.rabobank.travels.flights.core.dtos.OutputDTO;

public interface IUseCase<Input, Output> {

    OutputDTO<Output> execute(Input input);
}

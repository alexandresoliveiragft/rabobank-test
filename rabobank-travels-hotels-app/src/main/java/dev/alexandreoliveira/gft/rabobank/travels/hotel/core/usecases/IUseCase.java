package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.dtos.OutputDTO;

public interface IUseCase<Input, Output> {

    OutputDTO<Output> execute(Input input);
}

package dev.alexandreoliveira.gft.rabobank.travels.core.usecases;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;

public interface IUseCase<Input, Output> {

    OutputDTO<Output> execute(Input input);
}

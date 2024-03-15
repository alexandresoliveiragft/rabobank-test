package dev.alexandreoliveira.gft.rabobank.travels.core.usecases;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;

public interface IUseCase {

    interface InOut<Input, Output> {
        OutputDTO<Output> execute(Input input);
    }

    interface In<Input> {
        void execute(Input input);
    }
}

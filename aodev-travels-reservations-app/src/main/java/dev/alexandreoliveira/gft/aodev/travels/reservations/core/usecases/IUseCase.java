package dev.alexandreoliveira.gft.aodev.travels.core.usecases;

import dev.alexandreoliveira.gft.aodev.travels.core.dtos.OutputDTO;

public interface IUseCase {

    interface InOut<Input, Output> {
        OutputDTO<Output> execute(Input input);
    }

    interface In<Input> {
        void execute(Input input);
    }
}

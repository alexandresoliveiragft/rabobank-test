package dev.alexandreoliveira.gft.rabobank.travels.flights.core.dtos;

import java.util.Collections;
import java.util.List;

public record OutputDTO<Data>(
        Data data,
        List<String> errors,
        Boolean status) {

    public OutputDTO(Data data) {
        this(data, Collections.emptyList(), true);
    }

    public OutputDTO(List<String> errors) {
        this(null, errors, false);
    }
}

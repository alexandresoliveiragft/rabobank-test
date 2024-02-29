package dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.hotels.index;

import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.HotelModel;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.IUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.utils.validators.ModelValidatorUtil;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.utils.validators.groups.OnHotelsIndex;

import java.util.List;

public class HotelsIndexUseCase implements IUseCase<HotelModel, List<? extends HotelModel>> {

    private final HotelsIndexRepository repository;

    public HotelsIndexUseCase(HotelsIndexRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputDTO<List<? extends HotelModel>> execute(HotelModel hotelModel) {
        List<String> errors = ModelValidatorUtil.isValid(hotelModel, OnHotelsIndex.class);
        if (!errors.isEmpty()) {
            return new OutputDTO<>(errors);
        }
        return new OutputDTO<>(repository.findByHotelParams(hotelModel));
    }
}

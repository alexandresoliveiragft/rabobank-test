package dev.alexandreoliveira.gft.rabobank.travels.core.usecases.users.create;

import dev.alexandreoliveira.gft.rabobank.travels.core.dtos.OutputDTO;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.UserModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.IUseCase;
import dev.alexandreoliveira.gft.rabobank.travels.core.utils.validators.ModelValidatorUtil;

import java.util.List;

public record UsersCreateUseCase(
        UsersCreateRepository repository
) implements IUseCase<UserModel, UserModel> {

    @Override
    public OutputDTO<UserModel> execute(UserModel userModel) {
        List<String> errors = ModelValidatorUtil.isValid(userModel);
        if (!errors.isEmpty()) {
            return new OutputDTO<>(errors);
        }
        return new OutputDTO<>(repository.save(userModel));
    }
}

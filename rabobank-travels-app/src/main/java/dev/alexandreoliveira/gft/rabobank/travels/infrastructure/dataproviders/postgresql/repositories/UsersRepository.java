package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.repositories;

import dev.alexandreoliveira.gft.rabobank.travels.core.models.UserModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.users.create.UsersCreateRepository;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.users.show.UsersShowRepository;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.exceptions.DataProvidersException;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.dataproviders.postgresql.entities.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UsersRepository implements UsersCreateRepository, UsersShowRepository {

    private final JpaRepository<UserEntity, UUID> jpaRepository;

    public UsersRepository(@Qualifier("jpaUsersRepository") JpaRepository<UserEntity, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UserModel save(UserModel model) {
        var entity = (UserEntity) model;
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase("name", "email");
        List<UserEntity> foundUsers = jpaRepository.findAll(Example.of(entity, exampleMatcher));
        if (!foundUsers.isEmpty()) {
            throw new DataProvidersException("User found. Please, create with other parameters.");
        }
        return jpaRepository.save(entity);
    }

    @Override
    public UserModel findUserById(UUID id) {
        return jpaRepository.findById(id).orElse(new UserEntity());
    }
}

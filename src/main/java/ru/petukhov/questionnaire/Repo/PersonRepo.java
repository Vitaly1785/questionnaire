package ru.petukhov.questionnaire.Repo;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.questionnaire.Entity.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepo extends CrudRepository<Person, UUID> {
    Optional<Person> findByLogin(String login);

}

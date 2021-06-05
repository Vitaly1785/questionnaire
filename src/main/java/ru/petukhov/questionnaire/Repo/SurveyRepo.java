package ru.petukhov.questionnaire.Repo;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.questionnaire.Entity.Survey;

import java.util.UUID;

public interface SurveyRepo extends CrudRepository<Survey, UUID> {

    Iterable<Survey> findAllByActiveTrue();

    Survey findByActiveTrueAndId(UUID id);

}

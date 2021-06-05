package ru.petukhov.questionnaire.Services;

import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Person;
import ru.petukhov.questionnaire.Entity.Survey;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface PersonService {

    Iterable<Survey> findAllActiveSurveys();

    Survey findSurveyById(UUID id);

    Person startSurveyByPerson(UUID id, Principal principal, List<Answer> answers);

    Iterable<Survey> findSurveysByPerson(UUID id);

}

package ru.petukhov.questionnaire.Repo;


import org.springframework.data.repository.CrudRepository;
import ru.petukhov.questionnaire.Entity.Question;
import ru.petukhov.questionnaire.Entity.Survey;



public interface QuestionRepo extends CrudRepository<Question, Long> {

        Iterable<Question> findAllBySurvey(Survey survey);
}

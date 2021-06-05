package ru.petukhov.questionnaire.Repo;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Question;

public interface AnswerRepo extends CrudRepository<Answer, Long> {
    Iterable<Answer> findAllByQuestion(Question question);
}

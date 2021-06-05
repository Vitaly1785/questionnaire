package ru.petukhov.questionnaire.Services;

import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Question;
import ru.petukhov.questionnaire.Entity.Survey;

import java.util.UUID;

public interface AdminService {

    Iterable<Survey> findAllSurveys();

    Survey findSurveyById(UUID id);

    Survey createSurvey(Survey survey);

    Survey updateSurvey(Survey survey, UUID id);

    void deleteSurvey(UUID id);

    Iterable<Question> findAllQuestions();

    Question findQuestionById(Long id);

    Question createQuestion(Question question, Survey survey);

    Question updateQuestion(Question question, Long id);

    void deleteQuestion(Long id);

    Iterable<Question> findQuestionsBySurvey(Survey survey);

    Iterable<Answer> findAnswersByQuestion(Question question);

    Answer findAnswerById(Long id);

    Answer createAnswer(Answer answer, Question question);

    Answer updateAnswer(Answer answer, Long id);

    void deleteAnswer(Long id);
}

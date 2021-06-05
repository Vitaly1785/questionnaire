package ru.petukhov.questionnaire.Services;

import org.springframework.stereotype.Service;
import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Question;
import ru.petukhov.questionnaire.Entity.Survey;
import ru.petukhov.questionnaire.Exceptions.AnswerNotFoundException;
import ru.petukhov.questionnaire.Exceptions.QuestionNotFoundException;
import ru.petukhov.questionnaire.Exceptions.SurveyNotFoundException;
import ru.petukhov.questionnaire.Repo.AnswerRepo;
import ru.petukhov.questionnaire.Repo.QuestionRepo;
import ru.petukhov.questionnaire.Repo.SurveyRepo;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService{
    private final SurveyRepo surveyRepo;
    private final QuestionRepo questionRepo;
    private final AnswerRepo answerRepo;



    public AdminServiceImpl(SurveyRepo adminRepo, QuestionRepo questionRepo, AnswerRepo answerRepo) {
        this.surveyRepo = adminRepo;
        this.questionRepo = questionRepo;
        this.answerRepo = answerRepo;
    }

    @Override
    public Iterable<Survey> findAllSurveys() {
        return surveyRepo.findAll();
    }

    @Override
    public Survey findSurveyById(UUID id) {
        return surveyRepo.findById(id).orElseThrow(()-> new SurveyNotFoundException(String.format("Survey with id = %s not found", id)));
    }

    @Override
    public Survey createSurvey(Survey survey) {
        Survey createSurvey =  new Survey().toBuilder().title(survey.getTitle())
                .startTime(LocalDateTime.now()).active(survey.getActive())
                .questions(survey.getQuestions()).persons(survey.getPersons()).build();
        surveyRepo.save(createSurvey);
        return createSurvey;
    }

    @Override
    public Survey updateSurvey(Survey survey, UUID id) {
        Survey updateSurvey = surveyRepo.findById(id).orElseThrow(()-> new SurveyNotFoundException(String.format("Survey with id = %s not found", id)));
        updateSurvey.toBuilder().title(survey.getTitle()).active(survey.getActive())
                .questions(survey.getQuestions()).startTime(survey.getStartTime()).endTime(survey.getEndTime()).build();
        surveyRepo.save(updateSurvey);
        return updateSurvey;
    }

    @Override
    public void deleteSurvey(UUID id) {
        surveyRepo.delete(findSurveyById(id));
    }

    @Override
    public Iterable<Question> findAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionRepo.findById(id).orElseThrow(()-> new QuestionNotFoundException(String.format("Question with id = %s not found", id)));
    }

    @Override
    public Question createQuestion(Question question, Survey survey) {
        Question createQuestion = new Question().toBuilder().text(question.getText()).survey(survey)
                .answer(question.getAnswer()).build();
        questionRepo.save(createQuestion);
        return createQuestion;
    }

    @Override
    public Question updateQuestion(Question question, Long id) {
        Question updateQuestion = questionRepo.findById(id)
                .orElseThrow(()-> new QuestionNotFoundException(String.format("Question with id = %s not found", id)));
        updateQuestion.toBuilder().text(question.getText()).survey(question.getSurvey()).answer(question.getAnswer()).build();
        questionRepo.save(updateQuestion);
        return null;
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepo.delete(findQuestionById(id));
    }

    @Override
    public Iterable<Question> findQuestionsBySurvey(Survey survey) {
        return questionRepo.findAllBySurvey(survey);
    }

    @Override
    public Iterable<Answer> findAnswersByQuestion(Question question) {
        return answerRepo.findAllByQuestion(question);
    }

    @Override
    public Answer findAnswerById(Long id) {
        return answerRepo.findById(id).orElseThrow(()-> new AnswerNotFoundException(String.format("Answer with id = %s not found", id)));
    }

    @Override
    public Answer createAnswer(Answer answer, Question question) {
        Answer createAnswer = new Answer().toBuilder().text(answer.getText()).typeAnswer(answer.getTypeAnswer())
                .question(question).build();
        answerRepo.save(createAnswer);
        return createAnswer;
    }

    @Override
    public Answer updateAnswer(Answer answer, Long id) {
        Answer updateAnswer = findAnswerById(id).toBuilder().text(answer.getText()).typeAnswer(answer.getTypeAnswer())
                .question(answer.getQuestion()).build();
        answerRepo.save(updateAnswer);
        return updateAnswer;
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepo.delete(findAnswerById(id));
    }


}

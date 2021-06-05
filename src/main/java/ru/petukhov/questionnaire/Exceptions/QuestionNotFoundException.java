package ru.petukhov.questionnaire.Exceptions;

public class QuestionNotFoundException extends NotFoundException{
    public QuestionNotFoundException(String message) {
        super(message);
    }
}

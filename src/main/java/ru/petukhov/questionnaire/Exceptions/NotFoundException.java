package ru.petukhov.questionnaire.Exceptions;

public class NotFoundException extends RuntimeException{
        public NotFoundException(String message){
            super(message);
        }
}

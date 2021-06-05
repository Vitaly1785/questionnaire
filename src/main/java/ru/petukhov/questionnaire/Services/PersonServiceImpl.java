package ru.petukhov.questionnaire.Services;

import org.springframework.stereotype.Service;
import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Person;
import ru.petukhov.questionnaire.Entity.Survey;
import ru.petukhov.questionnaire.Exceptions.PersonNotFoundException;
import ru.petukhov.questionnaire.Exceptions.SurveyNotFoundException;
import ru.petukhov.questionnaire.Repo.PersonRepo;
import ru.petukhov.questionnaire.Repo.SurveyRepo;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService{

    private final SurveyRepo surveyRepo;
    private final PersonRepo personRepo;


    public PersonServiceImpl(SurveyRepo surveyRepo, PersonRepo personRepo) {
        this.surveyRepo = surveyRepo;
        this.personRepo = personRepo;
    }

    @Override
    public Iterable<Survey> findAllActiveSurveys() {
        return surveyRepo.findAllByActiveTrue();
    }

    @Override
    public Survey findSurveyById(UUID id) {
        return surveyRepo.findByActiveTrueAndId(id);
    }



    @Override
    public Person startSurveyByPerson(UUID id, Principal principal, List<Answer> answers) {
        Person person = personRepo.findByLogin(principal.getName()).orElseThrow(()-> new PersonNotFoundException(String.format("Person %s not found", principal.getName())));
        Optional<Survey> startSurvey = surveyRepo.findById(id);
        if (startSurvey.isPresent()){
            person.setSurveys((List<Survey>) startSurvey.get());
            person.setUserAnswers(answers);
            personRepo.save(person);
            return person;
        } else{
            throw new SurveyNotFoundException(String.format("Survey with id = %s not found", id));
        }
    }

    @Override
    public Iterable<Survey> findSurveysByPerson(UUID id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (personOptional.isPresent()){
           return personOptional.get().getSurveys();
        } else{
            throw new PersonNotFoundException(String.format("Person with id  = %s not found", id));
        }
    }
}

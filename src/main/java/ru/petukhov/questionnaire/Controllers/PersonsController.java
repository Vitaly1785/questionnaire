package ru.petukhov.questionnaire.Controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Person;
import ru.petukhov.questionnaire.Entity.Survey;
import ru.petukhov.questionnaire.Services.PersonService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@SessionScope
@RequestMapping("/api/v1/surveys")
public class PersonsController {

    private final PersonService personService;

    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ApiOperation("Show all active surveys for person")
    public Iterable<Survey> showActiveSurveys(){
        return personService.findAllActiveSurveys();
    }

    @GetMapping("/{id}")
    @ApiOperation("Show selected survey")
    public Survey showSurvey(@PathVariable UUID id){
        return personService.findSurveyById(id);
    }

    @PostMapping("/{id}")
    @ApiOperation("Start the survey")
    public Person startSurvey(@PathVariable UUID id, List<Answer> answers, Principal principal){
        return personService.startSurveyByPerson(id, principal, answers);
    }

    @GetMapping("/{idPerson}")
    @ApiOperation("Show completed surveys")
    public Iterable<Survey> showCompletedSurveys(@PathVariable UUID idPerson){
        return personService.findSurveysByPerson(idPerson);
    }
}

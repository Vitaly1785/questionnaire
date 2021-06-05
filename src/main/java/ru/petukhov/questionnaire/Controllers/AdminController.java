package ru.petukhov.questionnaire.Controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import ru.petukhov.questionnaire.Entity.Answer;
import ru.petukhov.questionnaire.Entity.Question;
import ru.petukhov.questionnaire.Entity.Survey;
import ru.petukhov.questionnaire.Services.AdminService;

import java.util.UUID;
//очень длинная портянка получилась I'm sorry
//можно было развести по разным эндпоинтам
@RestController
@SessionScope
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a survey by id")
    public Survey findSurveyById(@PathVariable UUID id) {
        return adminService.findSurveyById(id);
    }

    @GetMapping
    @ApiOperation("Search all surveys")
    public Iterable<Survey> findAllSurveys() {
        return adminService.findAllSurveys();
    }

    @PostMapping("/add")
    @ApiOperation("Creating a survey")
    public Survey createSurvey(@RequestBody Survey survey) {
        return adminService.createSurvey(survey);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Updating the survey")
    public Survey updateSurvey(@PathVariable UUID id, @RequestBody Survey survey) {
        return adminService.updateSurvey(survey, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleting a survey")
    public void deleteSurvey(@PathVariable UUID id) {
        adminService.deleteSurvey(id);
    }

    @GetMapping("/questions")
    @ApiOperation("Search all questions")
    public Iterable<Question> findAllQuestions() {
        return adminService.findAllQuestions();
    }

    @GetMapping("/questions/{id}")
    @ApiOperation("Search the question by id")
    public Question findQuestionById(@PathVariable Long id) {
        adminService.findAnswersByQuestion(adminService.findQuestionById(id));
        return adminService.findQuestionById(id);
    }

    @GetMapping("/{id}/questions")
    @ApiOperation("Search for a list of questions by survey id")
    public Iterable<Question> findQuestionsBySurvey(@PathVariable UUID id) {
        return adminService.findQuestionsBySurvey(adminService.findSurveyById(id));
    }

    @PostMapping("/{id}/questions/add")
    @ApiOperation("Creating a question")
    public Question createQuestion(@RequestBody Question question, @PathVariable UUID id) {
        adminService.createAnswer((Answer) adminService.findAnswersByQuestion(question), question);
        return adminService.createQuestion(question, adminService.findSurveyById(id));
    }

    @PatchMapping("/{id}/questions/{idQuestion}")
    @ApiOperation("Updating the question")
    public Question updateQuestion(@RequestBody Question question, @PathVariable Long idQuestion) {
        return adminService.updateQuestion(question, idQuestion);
    }

    @DeleteMapping("/{id}/questions/{idQuestion}")
    @ApiOperation("Deleting a question")
    public void deleteQuestion(@PathVariable Long idQuestion) {
        adminService.deleteQuestion(idQuestion);
    }

    @GetMapping("/{id}/question/{idQuestion}/answers")
    @ApiOperation("Search for a list of answers by question id")
    public Iterable<Answer> findAnswersByQuestion(@PathVariable Long idQuestion,@PathVariable Long id){
        return adminService.findAnswersByQuestion(adminService.findQuestionById(idQuestion));
    }

    @GetMapping("/{id}/questions/{id}/answers/{idAnswer}")
    @ApiOperation("Search the answer")
    public Answer findAnswerById(@PathVariable Long idAnswer, @PathVariable Long id ){
        return adminService.findAnswerById(idAnswer);
    }

    @PostMapping("/{id}/questions/{idQuestion}/answers/add")
    @ApiOperation("Creating an answer")
    public Answer createAnswer(@RequestBody Answer answer, @PathVariable Long idQuestion) {
        return adminService.createAnswer(answer, adminService.findQuestionById(idQuestion));
    }

    @PatchMapping("/{id}/questions/{id}/answers/{idAnswer}")
    @ApiOperation("Updating the answer")
    public Answer updateAnswer(@RequestBody Answer answer, @PathVariable Long idAnswer) {
        return adminService.updateAnswer(answer, idAnswer);
    }

    @DeleteMapping("/{id}/questions/{id}/answers/{idAnswer}")
    @ApiOperation("Deleting an answer")
    public void deleteAnswer(@PathVariable Long idAnswer, @PathVariable Long id) {
        adminService.deleteAnswer(idAnswer);
    }
}

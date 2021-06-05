package ru.petukhov.questionnaire.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Person {

    @Id
    @Column(name = "id", length = 36, unique = true, nullable = false)
    private UUID id = UUID.randomUUID();

    private String login;
    private String password;

    @ManyToMany
    @JoinTable(name = "persons_surveys",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id"))
    private List<Survey> surveys;

    @ManyToMany
    @JoinTable(name = "persons_answers",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> userAnswers;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

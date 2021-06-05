package ru.petukhov.questionnaire.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 36, unique = true, nullable = false)
    private Long id;

    @Column(name = "text", length = 1024)
    private String text;

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<Answer> answer;


    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false, updatable = false)
    private Survey survey;
}

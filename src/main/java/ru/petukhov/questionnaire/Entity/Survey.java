package ru.petukhov.questionnaire.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class Survey {
    @Id
    @Column(name = "id", length = 36, unique = true, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "title", length = 64)
    private String title;
    @Column(name = "strat_time", columnDefinition = "TIME WITH TIME ZONE")
    private LocalDateTime startTime;
    @Column(name = "end_time", columnDefinition = "TIME WITH TIME ZONE")
    private LocalDateTime endTime;

    @Column(name = "question_id")
    @OneToMany(mappedBy = "survey", orphanRemoval = true)
    private List<Question> questions;

    private Boolean active;

    @Column(name = "user_id")
    @ManyToMany@JoinTable(name = "persons_surveys",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;
}

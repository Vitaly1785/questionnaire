package ru.petukhov.questionnaire.Entity;


import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum TypeAnswer {
    TEXT_ANSWER, ONE_ANSWER, MULTI_ANSWER;

    private String text;
    private Boolean answer;
    private List<Boolean> answers;
}

package com.ganesh.quizmonolithic.DTO;

import com.ganesh.quizmonolithic.Enums.Subject;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class QuizGetDTO {
    Integer id,noOfQsns;
    Subject subject;String name;
    List<QuestionWithoutAnswer> questionWithoutAnswerList;
}

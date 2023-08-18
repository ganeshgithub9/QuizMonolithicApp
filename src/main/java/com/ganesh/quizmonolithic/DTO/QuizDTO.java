package com.ganesh.quizmonolithic.DTO;

import com.ganesh.quizmonolithic.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizDTO {
    Subject subject;
    Integer noOfQsns;
}

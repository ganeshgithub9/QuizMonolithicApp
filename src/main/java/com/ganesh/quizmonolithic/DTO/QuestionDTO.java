package com.ganesh.quizmonolithic.DTO;

import com.ganesh.quizmonolithic.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    Subject subject;
    String statement,option1,option2,option3,option4,answer;
}

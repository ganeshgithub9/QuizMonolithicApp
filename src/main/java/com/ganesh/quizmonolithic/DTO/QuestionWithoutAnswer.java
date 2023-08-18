package com.ganesh.quizmonolithic.DTO;

import com.ganesh.quizmonolithic.Models.Quiz;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class QuestionWithoutAnswer {
    Integer id;
    String statement,option1,option2,option3,option4;
}

package com.ganesh.quizmonolithic.Models;

import com.ganesh.quizmonolithic.Enums.Subject;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@Getter
@Table(name = "quizzes")
public class Quiz {
   // static Map<String,Integer> mapToStoreQuizCountperCategory=new HashMap<>();
    @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    Subject subject;
    Integer noOfQsns;

    @ManyToMany
            @JoinTable(name = "question_quiz",joinColumns = @JoinColumn(name = "quiz_id"),inverseJoinColumns = @JoinColumn(name =
            "question_id"))
    List<Question> questions;

    @Setter(AccessLevel.NONE)
    String name;

    public void setName(){
      //  String subCap= StringUtils.capitalize(subject);
//        if(mapToStoreQuizCountperCategory.containsKey(subject))
//            mapToStoreQuizCountperCategory.put(subject,mapToStoreQuizCountperCategory.get(subject)+1);
//        else
//            mapToStoreQuizCountperCategory.put(subject,1);
        name=subject+" Quiz";
    }
}

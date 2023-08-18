package com.ganesh.quizmonolithic.Controllers;

import com.ganesh.quizmonolithic.DTO.QuestionWithoutAnswer;
import com.ganesh.quizmonolithic.DTO.QuizDTO;
import com.ganesh.quizmonolithic.DTO.QuizGetDTO;
import com.ganesh.quizmonolithic.DTO.ResponseDTO;
import com.ganesh.quizmonolithic.Enums.Subject;
import com.ganesh.quizmonolithic.Models.Question;
import com.ganesh.quizmonolithic.Models.Quiz;
import com.ganesh.quizmonolithic.Services.QuestionService;
import com.ganesh.quizmonolithic.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    QuizService quizService;
    QuestionService questionService;
    @Autowired
    QuizController(QuizService qs,QuestionService qus){
        quizService=qs;
        questionService=qus;
    }
    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
       //String subL=quizDTO.getSubject().toLowerCase();
       boolean result=isRequiredQsnCountPresentForGivenSubject(quizDTO.getSubject(),quizDTO.getNoOfQsns());
//        if(!result)
//            return new ResponseEntity<>(subL+" questions are not present in database",HttpStatus.PRECONDITION_FAILED);
//        result=checkRequiredQsnCount(quizDTO.getNoOfQsns(),subL);
        if(!result)
            return new ResponseEntity<>("Sufficient number of questions are not present in database",HttpStatus.PRECONDITION_FAILED);
        result=quizService.createQuiz(quizDTO);
        String res=result?"Quiz created":"Quiz not created";
        HttpStatus stat=result?HttpStatus.OK:HttpStatus.CONFLICT;
        return new ResponseEntity<>(res,stat);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizGetDTO> getQuiz(@PathVariable Integer id){
        Quiz quiz=quizService.getQuiz(id);
        if(quiz==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        List<QuestionWithoutAnswer> qwa=quiz.getQuestions().stream().map(question -> QuestionWithoutAnswer.builder().id(question.getId())
                .statement(question.getStatement()).option1(question.getOption1()).option2(question.getOption2())
                .option3(question.getOption3()).option4(question.getOption4()).build()).toList();
        QuizGetDTO quizGetDTO=QuizGetDTO.builder().id(quiz.getId()).noOfQsns(quiz.getNoOfQsns()).subject(quiz.getSubject())
                .name(quiz.getName()).questionWithoutAnswerList(qwa).build();
        return new ResponseEntity<>(quizGetDTO,HttpStatus.OK);
    }

    @GetMapping("/{id}/score")
    public long getScore(@RequestBody List<String> list,@PathVariable Integer id){
        return quizService.getScore(list,id);
    }

    private boolean isRequiredQsnCountPresentForGivenSubject(Subject subject, Integer qsnCount) {
        return questionService.isRequiredQsnCountPresentForGivenSubject(subject,qsnCount);
        //return Question.mapToStoreQsnCountPerCategory.containsKey(subL);
    }

//    private boolean checkRequiredQsnCount(Integer noOfQsns,String subject) {
//        int req=(int)noOfQsns,present=(int)Question.mapToStoreQsnCountPerCategory.get(subject);
//        return req>present?false:true;
//    }
}

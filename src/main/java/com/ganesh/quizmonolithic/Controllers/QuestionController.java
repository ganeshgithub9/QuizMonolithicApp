package com.ganesh.quizmonolithic.Controllers;

import com.ganesh.quizmonolithic.DTO.QuestionDTO;
import com.ganesh.quizmonolithic.DTO.QuestionWithoutAnswer;
import com.ganesh.quizmonolithic.Enums.Subject;
import com.ganesh.quizmonolithic.Models.Question;
import com.ganesh.quizmonolithic.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    QuestionService questionService;

    @Autowired
    QuestionController(QuestionService qs){
        questionService=qs;
    }
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionDTO questionDTO){
        boolean result= questionService.createQuestion(questionDTO);
        if(result)
            return new ResponseEntity<>("Question created", HttpStatus.OK);
        return new ResponseEntity<>("Question not created",HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionWithoutAnswer> getQuestion(@PathVariable Integer id){
        Optional<Question> qu=questionService.getQuestion(id);
        return qu.map((q) -> new ResponseEntity<>(QuestionWithoutAnswer.builder().id(q.getId()).statement(q.getStatement()).option1(q.getOption1()).option2(q.getOption2())
                        .option3(q.getOption3()).option4(q.getOption4()).build(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

//    @GetMapping
//    public Question getQuestionBySubject(@RequestParam Subject subject){
//
//    }

    //@GetMapping("/countMap")
//    public ResponseEntity<List<Integer>> getQsnIdsByTopic(@RequestParam String subject){
//        String subL=subject.toLowerCase();
//         List<Integer> result=Question.mapToStoreQsnIdPerCategory.getOrDefault(subL, null);
//         return new ResponseEntity<>(result,HttpStatus.OK);
//    }
//    public Map<String,Integer> getCountMap(){
//        //return Question.mapToStoreQsnIdPerCategory;
//        return Question.mapToStoreQsnCountPerCategory;
//    }
//
//    @GetMapping("/idMap")
//    public Map<String,List<Integer>> getIdMap(){
//        return Question.mapToStoreQsnIdPerCategory;
//    }
}

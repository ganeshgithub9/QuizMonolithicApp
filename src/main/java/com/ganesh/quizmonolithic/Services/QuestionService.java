package com.ganesh.quizmonolithic.Services;

import com.ganesh.quizmonolithic.DTO.QuestionDTO;
import com.ganesh.quizmonolithic.Enums.Subject;
import com.ganesh.quizmonolithic.Models.Question;
import com.ganesh.quizmonolithic.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    @Autowired
    QuestionService(QuestionRepository qr){
        questionRepository=qr;
    }

    public boolean createQuestion(QuestionDTO questionDTO){
        boolean result;
        try{
            Question q=new Question();
            q.setSubject(questionDTO.getSubject());
            q.setStatement(questionDTO.getStatement());
            q.setOption1(questionDTO.getOption1());
            q.setOption2(questionDTO.getOption2());
            q.setOption3(questionDTO.getOption3());
            q.setOption4(questionDTO.getOption4());
            q.setAnswer(questionDTO.getAnswer());
            questionRepository.save(q);
            //q.updateMaps();
            result=true;
        }
        catch (Exception e){
            result=false;
        }
        return result;
    }

    public Optional<Question> getQuestion(Integer id) {
        return questionRepository.findById(id);
    }
    public boolean isRequiredQsnCountPresentForGivenSubject(Subject subject, Integer qsnCount){
        Integer count=questionRepository.findCountBySubject(subject);
        return (int)count>=(int)qsnCount;
    }
}

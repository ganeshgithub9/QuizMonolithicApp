package com.ganesh.quizmonolithic.Services;

import com.ganesh.quizmonolithic.DTO.QuizDTO;
import com.ganesh.quizmonolithic.DTO.ResponseDTO;
import com.ganesh.quizmonolithic.Enums.Subject;
import com.ganesh.quizmonolithic.Models.Question;
import com.ganesh.quizmonolithic.Models.Quiz;
import com.ganesh.quizmonolithic.Repositories.QuestionRepository;
import com.ganesh.quizmonolithic.Repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    QuizRepository quizRepository;
    QuestionRepository questionRepository;
    @Autowired
    QuizService(QuizRepository qr,QuestionRepository qur){
        quizRepository=qr;
        questionRepository=qur;
    }
    public boolean createQuiz(QuizDTO quizDTO){
        try {
            Quiz quiz = new Quiz();
            quiz.setSubject(quizDTO.getSubject());
            quiz.setNoOfQsns(quizDTO.getNoOfQsns());
            quiz.setName();
            List<Question> que=findQuestionsRandomly(quizDTO.getNoOfQsns(),quizDTO.getSubject());
            quiz.setQuestions(que);
            quizRepository.save(quiz);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<Question> findQuestionsRandomly(Integer qsnCount, Subject subject){
        List<Question> subjectQsns=questionRepository.findAllBySubject(subject);
        List<Question> answer=new ArrayList<>();
        int size=0,subjectQsnsSize=subjectQsns.size();
        Set<Integer> qsns=new HashSet<>();
        Random random=new Random();
        while(size<qsnCount){
            Integer randomIndex=random.nextInt(subjectQsnsSize);
            if(!qsns.contains(randomIndex)){
                answer.add(subjectQsns.get(randomIndex));
                qsns.add(randomIndex);
                size++;
            }
        }
        return answer;
    }

    public Quiz getQuiz(Integer id) {
        return quizRepository.findById(id).map(quiz -> quiz).orElseGet(null);
    }

    public long getScore(List<String> list,Integer id) {
        Quiz q=quizRepository.findById(id).map(quiz -> quiz).orElseGet(null);
        if(q==null)
            return 0;
        long count=0;int i;
        List<Question> li=q.getQuestions();
        for(i=0;i<q.getNoOfQsns();i++){
            if(li.get(i).getAnswer().equals(list.get(i)))
                count++;
        }
        return count;
    }
}

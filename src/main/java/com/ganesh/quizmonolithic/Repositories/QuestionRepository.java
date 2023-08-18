package com.ganesh.quizmonolithic.Repositories;

import com.ganesh.quizmonolithic.Enums.Subject;
import com.ganesh.quizmonolithic.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Query("select count(q.id) from Question q where q.subject=:subject")
    Integer findCountBySubject(Subject subject);

    List<Question> findAllBySubject(Subject subject);

    String findAnswerById(Integer id);
}

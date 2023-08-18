package com.ganesh.quizmonolithic.Repositories;

import com.ganesh.quizmonolithic.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}

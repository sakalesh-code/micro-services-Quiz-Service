package com.learn.Quiz_service.QuestionDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.Quiz_service.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

}

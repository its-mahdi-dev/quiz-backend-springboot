package com.example.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}

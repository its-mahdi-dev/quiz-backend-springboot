package com.example.question.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String> {
    Optional<Question> findById(Long questionId);
}
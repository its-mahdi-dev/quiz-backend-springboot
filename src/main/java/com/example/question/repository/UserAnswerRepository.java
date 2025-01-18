package com.example.question.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, String> {
    Optional<UserAnswer> findByUserIdAndQuestionId(Long userId, Long questionId);
}
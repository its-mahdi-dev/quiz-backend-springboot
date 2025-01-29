package com.example.question.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.question.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, String> {
    Optional<UserAnswer> findByUserIdAndQuestionId(Long userId, Long questionId);

    @Query("SELECT ua FROM UserAnswer ua WHERE ua.user.id = :userId AND (:search IS NULL OR ua.question.body LIKE %:search%)")
    Page<UserAnswer> findByUserId(@Param("userId") Long userId, Pageable pageable, @Param("search") String search);

    List<UserAnswer> findByUserId(Long userId);

    @Query("SELECT DISTINCT ua.question.id FROM UserAnswer ua WHERE ua.user.id = :userId")
    List<Long> findAnsweredQuestionIds(@Param("userId") Long userId);

    @Query("SELECT ua FROM UserAnswer ua " +
            "JOIN FETCH ua.question q " +
            "LEFT JOIN FETCH q.category c " +
            "LEFT JOIN FETCH q.answers ans " +
            "LEFT JOIN FETCH ua.answer a " +
            "WHERE ua.user.id = :userId " +
            "AND (:search IS NULL OR LOWER(q.body) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "ORDER BY ua.id")
    List<UserAnswer> findPlayerQuestions(@Param("userId") Long userId,
            @Param("search") String search,
            Pageable pageable);

    @Query("SELECT COUNT(ua) FROM UserAnswer ua " +
            "JOIN ua.question q " +
            "LEFT JOIN q.category c " +
            "WHERE ua.user.id = :userId " +
            "AND (:search IS NULL OR LOWER(q.body) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    long countPlayerQuestions(@Param("userId") Long userId,
            @Param("search") String search);

            

}
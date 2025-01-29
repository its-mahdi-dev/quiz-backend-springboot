package com.example.question.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.question.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findById(Long questionId);
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE q.id = :questionId")
    Optional<Question> findByIdWithDetails(@Param("questionId") Long questionId);

    @Query("SELECT q.id FROM Question q WHERE q.id NOT IN :answeredIds AND q.startTime < :now AND q.endTime > :now ORDER BY RAND()")
    Optional<Long> findRandomQuestionId(@Param("answeredIds") List<Long> answeredIds, @Param("now") Date now);

    List<Question> findBySearch(String search, int offset, int limit);

    long countBySearch(String search);

    Optional<Question> findRandomQuestion(List<Long> answeredQuestionIds, Date now);
}
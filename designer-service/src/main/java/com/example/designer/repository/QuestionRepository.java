package com.example.designer.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.designer.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
        Optional<Question> findById(Long questionId);

        @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE q.id = :questionId")
        Optional<Question> findByIdWithDetails(@Param("questionId") Long questionId);

        @Query("SELECT q FROM Question q WHERE q.id NOT IN :answeredQuestionIds " +
                        "AND q.startTime < :now AND q.endTime > :now ORDER BY RAND()")
        Page<Question> findRandomQuestionNotAnsweredByUser(@Param("answeredQuestionIds") List<Long> answeredQuestionIds,
                        @Param("now") LocalDateTime now, Pageable pageable);

        @Query("SELECT q FROM Question q WHERE LOWER(q.body) LIKE LOWER(CONCAT('%', :search, '%'))")
        Page<Question> findBySearch(@Param("search") String search, Pageable pageable);

        @Query("SELECT q FROM Question q " +
                        "JOIN FETCH q.category c " +
                        "JOIN FETCH q.user u " +
                        "JOIN FETCH q.answers a " +
                        "WHERE (LOWER(q.body) LIKE LOWER(CONCAT('%', :search, '%'))) " +
                        "ORDER BY a.order ASC")
        Page<Question> findDesignerQuestions(@Param("search") String search, Pageable pageable);
}
package com.example.player.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.player.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
        Optional<Question> findById(Long questionId);

        @Query("SELECT q FROM Question q WHERE q.id NOT IN :answeredQuestionIds " +
                        "AND q.startTime < :now AND q.endTime > :now ORDER BY RAND()")
        Page<Question> findRandomQuestionNotAnsweredByUser(@Param("answeredQuestionIds") List<Long> answeredQuestionIds,
                        @Param("now") LocalDateTime now, Pageable pageable);
}
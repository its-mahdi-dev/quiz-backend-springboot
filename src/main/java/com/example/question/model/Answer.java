package com.example.question.model;

import jakarta.persistence.*;

import java.io.Serializable;
import lombok.*;


@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    private Integer order;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

}

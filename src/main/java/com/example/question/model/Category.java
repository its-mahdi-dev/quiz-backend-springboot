package com.example.question.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import lombok.*;
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Question> questions;

    // Getters and Setters
}

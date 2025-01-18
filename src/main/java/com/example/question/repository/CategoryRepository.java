package com.example.question.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.question.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories WHERE name LIKE %:search% LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Category> findByNameContaining(String search, int offset, int limit);

    @Query(value = "SELECT COUNT(*) FROM categories WHERE name LIKE %:search%", nativeQuery = true)
    long countByNameContaining(String search);

    @Query(value = "SELECT * FROM categories LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Category> findAll(int offset, int limit);

    @Query(value = "SELECT COUNT(*) FROM categories", nativeQuery = true)
    long count();
}


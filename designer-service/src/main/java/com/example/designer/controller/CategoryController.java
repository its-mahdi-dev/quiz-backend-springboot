package com.example.designer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.designer.dto.CategoryRequest;
import com.example.designer.service.CategoryService;

import java.util.Map;

@RestController
@RequestMapping("/api/designer/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String search) {
        try {
            Map<String, Object> response = categoryService.getCategories(page, limit, search);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "message", "Server error",
                    "error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@Validated @RequestBody CategoryRequest requestBody) {
        categoryService.addCategory(requestBody.getName());
        return ResponseEntity.status(201).body(Map.of("message", "دسته بندی با موفقیت اضافه شد"));
    }
}

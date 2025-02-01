package com.example.designer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.designer.dto.QuestionDTO;
import com.example.designer.model.Question;
import com.example.designer.service.QuestionService;

@RestController
@RequestMapping("/api/designer/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity<?> getDesignerQuestion(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "search", defaultValue = "") String search) {
        return ResponseEntity.ok(questionService.getDesignerQuestions(page, limit, search));
    }

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestAttribute("userId") Long userId,
            @Validated @RequestBody QuestionDTO questionDTO) {
        Question createdQuestion = questionService.createQuestion(questionDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }
}

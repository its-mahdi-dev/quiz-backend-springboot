package com.example.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.question.service.QuestionService;

@RestController
@RequestMapping("/api/player")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<?> getPlayerQuestions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String search,
            @RequestAttribute("userId") Long userId) {
        return ResponseEntity.ok(questionService.getPlayerQuestions(userId, page, limit, search));
    }

    @GetMapping("/scores")
    public ResponseEntity<?> getPlayersScores(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(questionService.getPlayersScores(page, limit, search));
    }

    // @GetMapping("/questions/{questionId}")
    // public ResponseEntity<?> getSingleQuestion(@PathVariable("questionId") Long questionId,
    //         @RequestAttribute("userId") Long userId) {
    //     return ResponseEntity.ok(questionService.getSingleQuestion(userId, questionId));
    // }

    // @GetMapping("/random")
    // public ResponseEntity<?> getRandomQuestion(@RequestAttribute("userId") Long userId) {
    //     return ResponseEntity.ok(questionService.getRandomQuestion(userId));
    // }
}

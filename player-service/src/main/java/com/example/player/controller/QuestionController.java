package com.example.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.player.dto.QuestionDTO;
import com.example.player.dto.Response;
import com.example.player.service.QuestionService;

@RestController
@RequestMapping("/api/player/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity<?> getPlayerQuestions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String search,
            @RequestAttribute("userId") Long userId) {
        return ResponseEntity.ok(questionService.getPlayerQuestions(userId, page, limit, search));
    }

    @GetMapping("/scores")
    public ResponseEntity<?> getPlayersScores(
            @RequestAttribute("userId") Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(questionService.getPlayersScores(userId, page, limit, search));
    }

    @GetMapping("/random")
    public ResponseEntity<Long> getRandomQuestion(@RequestAttribute("userId") Long userId) {
        Long questionId = questionService.getRandomQuestion(userId);
        return ResponseEntity.ok(questionId);
    }

    @GetMapping("/{question_id}")
    public ResponseEntity<QuestionDTO> getSingleQuestion(@RequestAttribute("userId") Long userId,
            @PathVariable Long question_id) {

        QuestionDTO questionDTO = questionService.getSingleQuestion(userId, question_id);
        return ResponseEntity.ok(questionDTO);
    }
    @PostMapping("/{questionId}")
    public ResponseEntity<?> submitPlayerAnswer(
            @PathVariable("questionId") Long questionId,
            @RequestParam("answer_id") Long answer_id,
            @RequestAttribute("userId") Long userId) {

        String responseMessage = questionService.submitPlayerAnswer(userId, questionId, answer_id);

        return ResponseEntity.status(201).body(new Response(responseMessage, 201));
    }

}

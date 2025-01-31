package com.example.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.question.dto.Response;
import com.example.question.service.AnswerService;

@RestController
@RequestMapping("/api/player")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/questions/{questionId}")
    public ResponseEntity<?> submitPlayerAnswer(
            @PathVariable("questionId") Long questionId,
            @RequestParam("answer_id") Long answer_id,
            @RequestAttribute("userId") Long userId) {

        String responseMessage = answerService.submitPlayerAnswer(userId, questionId, answer_id);

        return ResponseEntity.status(201).body(new Response(responseMessage, 201));
    }
}

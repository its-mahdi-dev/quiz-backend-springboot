package com.example.question.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerQuestionDTO {
    private Long id;
    private String body;
    private Long duration;
    private Long categoryId;
    private String categoryName;
    private List<AnswerDTO> answers;
    private AnswerDTO answer;
    private Long correctAnswerId;
    private Long selectedAnswer;
}

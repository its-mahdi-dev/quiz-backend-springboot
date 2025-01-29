package com.example.question.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerDTO {
    private Long id;    
    private String answerText;
    private Integer order;

}

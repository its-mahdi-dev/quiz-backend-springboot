package com.example.player.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerDTO {
    private Long id;
    private String body;
    private Integer order;

}

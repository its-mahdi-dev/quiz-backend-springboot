package com.example.designer.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;

    private Long userId;

    @NotBlank
    private String body;

    @NotNull
    private Integer duration;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;
    private String categoryName;

    @NotNull
    private Long categoryId;

    @NotEmpty
    @Size(min = 4, max = 4)
    private List<AnswerDTO> answers;

    private UserDTO user;

    @NotNull
    private Long correctAnswerId;

}

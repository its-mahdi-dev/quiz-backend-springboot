package com.example.question.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerScoreDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer score;
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Boolean isFollowing;

}

package com.example.player.dto;

import java.util.List;

import com.example.player.model.UserType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DesignerDTO {
    private Long Id;
    private String first_name;
    private String last_name;
    private List<QuestionDTO> questions;
    private Double dificultly;

}

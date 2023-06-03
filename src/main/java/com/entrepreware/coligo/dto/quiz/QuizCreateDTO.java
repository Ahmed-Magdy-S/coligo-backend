package com.entrepreware.coligo.dto.quiz;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class QuizCreateDTO {

    @NotBlank(message = "You need to specify the quiz unit")
    private int unit;

    @NotBlank(message = "You need to specify the quiz course")
    private String course;

    @NotBlank(message = "You need to specify the quiz topic")
    private String topic;

    @NotBlank(message = "You need to specify the quiz due to date")
    private Date dueTo;
}

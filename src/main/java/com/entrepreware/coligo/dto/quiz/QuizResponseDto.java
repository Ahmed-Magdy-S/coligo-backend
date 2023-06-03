package com.entrepreware.coligo.dto.quiz;


import lombok.Data;

import java.util.Date;

@Data
public class QuizResponseDto {

    private int id;
    private int unit;
    private String course;
    private String topic;
    private Date dueTo;
}

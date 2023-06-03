package com.entrepreware.coligo.utils;

import com.entrepreware.coligo.dto.announcement.AnnouncementCreateDto;
import com.entrepreware.coligo.dto.announcement.AnnouncementResponseDto;
import com.entrepreware.coligo.dto.quiz.QuizCreateDTO;
import com.entrepreware.coligo.dto.quiz.QuizResponseDto;
import com.entrepreware.coligo.entity.Announcement;
import com.entrepreware.coligo.entity.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValueMapper {
    private ValueMapper(){}

    public static Quiz convertToEntity(QuizCreateDTO quizCreateDTO){
        Quiz quiz = new Quiz();
        quiz.setUnit(quizCreateDTO.getUnit());
        quiz.setCourse(quizCreateDTO.getCourse());
        quiz.setTopic(quizCreateDTO.getTopic());
        quiz.setDueTo(quizCreateDTO.getDueTo());
        return quiz;
    }

    public static QuizResponseDto convertToDTO(Quiz quiz){
        QuizResponseDto quizResponseDto = new QuizResponseDto();
        quizResponseDto.setCourse(quiz.getCourse());
        quizResponseDto.setUnit(quiz.getUnit());
        quizResponseDto.setTopic(quiz.getTopic());
        quizResponseDto.setDueTo(quiz.getDueTo());
        quizResponseDto.setId(quiz.getId());
        return quizResponseDto;
    }

    public static Announcement convertToEntity(AnnouncementCreateDto announcementCreateDto){
        Announcement announcement = new Announcement();
        announcement.setDescription(announcementCreateDto.getDescription());
        announcement.setEmployeeId(announcement.getEmployeeId());
        return announcement;
    }

    public static AnnouncementResponseDto convertToDTO(Announcement announcement){
        AnnouncementResponseDto announcementResponseDto = new AnnouncementResponseDto();
        announcementResponseDto.setDescription(announcement.getDescription());
        announcementResponseDto.setEmployee(announcement.getEmployee());
        return announcementResponseDto;
    }


    public static String jsonAsString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

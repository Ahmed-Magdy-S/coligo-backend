package com.entrepreware.coligo.dto.announcement;


import com.entrepreware.coligo.entity.Employee;
import lombok.Data;

@Data
public class AnnouncementResponseDto {
    private int id;
    private String description;
    private Employee employee;

}

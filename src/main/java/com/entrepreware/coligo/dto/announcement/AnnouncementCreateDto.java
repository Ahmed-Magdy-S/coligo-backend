package com.entrepreware.coligo.dto.announcement;

import com.entrepreware.coligo.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnnouncementCreateDto {

    @NotBlank(message = "You need to provide announcement description")
    private String description;

    @NotBlank(message = "You need to provide employee id")
    private int employeeId;

}

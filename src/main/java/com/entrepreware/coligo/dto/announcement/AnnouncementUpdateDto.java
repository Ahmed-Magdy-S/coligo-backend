package com.entrepreware.coligo.dto.announcement;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnnouncementUpdateDto {

    @NotBlank(message = "You didn't provide the updated description")
    private String description;

}

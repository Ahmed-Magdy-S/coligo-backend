package com.entrepreware.coligo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String field;
    private String errorMessage;
}

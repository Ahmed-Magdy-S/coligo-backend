package com.entrepreware.coligo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class APIResponse<T> {
    private String status;
    private List<ErrorDTO> errors;
    private T results;
}

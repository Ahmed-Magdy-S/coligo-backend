package com.entrepreware.coligo.handler;

import com.entrepreware.coligo.dto.APIResponse;
import com.entrepreware.coligo.dto.ErrorDTO;
import com.entrepreware.coligo.exception.AnnouncementNotFoundException;
import com.entrepreware.coligo.exception.AnnouncementServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class AnnouncementServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }
    @ExceptionHandler(AnnouncementServiceBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<?> handleServiceException(AnnouncementServiceBusinessException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(AnnouncementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse<?> handleProductNotFoundException(AnnouncementNotFoundException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }
}

package com.entrepreware.coligo.handler;

import com.entrepreware.coligo.dto.APIResponse;
import com.entrepreware.coligo.dto.ErrorDTO;
import com.entrepreware.coligo.exception.QuizNotFoundException;
import com.entrepreware.coligo.exception.QuizServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class QuizServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<APIResponse<?>> handleMethodArgumentException(MethodArgumentNotValidException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return new ResponseEntity<>(serviceResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(QuizServiceBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<APIResponse<?>> handleServiceException(QuizServiceBusinessException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return new ResponseEntity<>(serviceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<APIResponse<?>> handleProductNotFoundException(QuizNotFoundException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return new ResponseEntity<>(serviceResponse,HttpStatus.NOT_FOUND);
    }

}

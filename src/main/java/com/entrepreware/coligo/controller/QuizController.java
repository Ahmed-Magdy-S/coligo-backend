package com.entrepreware.coligo.controller;

import com.entrepreware.coligo.dto.APIResponse;
import com.entrepreware.coligo.dto.quiz.QuizCreateDTO;
import com.entrepreware.coligo.dto.quiz.QuizResponseDto;
import com.entrepreware.coligo.dto.quiz.QuizUpdateDto;
import com.entrepreware.coligo.service.QuizService;
import com.entrepreware.coligo.utils.ValueMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quizzes")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class QuizController {

    private final QuizService _quizService;
    public static final String SUCCESS = "Success";


    @Operation(summary = "Create a new quiz")
    @PostMapping
    public ResponseEntity<APIResponse<QuizResponseDto>> createNewQuiz(@RequestBody @Valid QuizCreateDTO quizCreateDTO){
        log.info("QuizController:createNewQuiz has been executed");
        log.info("QuizController:createNewQuiz request parameters {}", ValueMapper.jsonAsString(quizCreateDTO));

        QuizResponseDto quizResponseDto = _quizService.createNewQuiz(quizCreateDTO);

        APIResponse<QuizResponseDto> responseDto = APIResponse
                .<QuizResponseDto>builder()
                .status(SUCCESS)
                .results(quizResponseDto)
                .build();
        log.info("QuizController:createNewQuiz response {}", ValueMapper.jsonAsString(responseDto));
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all quizzes")
    @GetMapping
    public ResponseEntity<APIResponse<List<QuizResponseDto>>> getAllQuizzes() {
        log.info("QuizController:getAllQuizzes has been executed");
        List<QuizResponseDto> quizResponseDtoList = _quizService.getAllQuizzes();
        APIResponse<List<QuizResponseDto>> responseDTO = APIResponse
                .<List<QuizResponseDto>>builder()
                .status(SUCCESS)
                .results(quizResponseDtoList)
                .build();

        log.info("QuizController::getAllQuizzes response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get a quiz by its id")
    @GetMapping("/{quizId}")
    public ResponseEntity<APIResponse<QuizResponseDto>> getQuizById(@PathVariable int quizId) {
        log.info("QuizController:getQuizById has been executed");
        log.info("QuizController:getQuizById quizId {}", quizId);


        QuizResponseDto quizResponseDTO = _quizService.getQuizById(quizId);
        APIResponse<QuizResponseDto> responseDTO = APIResponse
                .<QuizResponseDto>builder()
                .status(SUCCESS)
                .results(quizResponseDTO)
                .build();

        log.info("QuizController::getQuizById by id  {} response {}", quizId,ValueMapper
                .jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "update a quiz with specific id")
    @PutMapping("/{quizId}")
    public ResponseEntity<APIResponse<QuizResponseDto>> updateQuiz(@PathVariable int quizId, @RequestBody @Valid QuizUpdateDto quizUpdateDto) {
        log.info("QuizController:updateQuiz has been executed");
        log.info("QuizController:updateQuiz with request body {}", ValueMapper.jsonAsString(quizUpdateDto));

        QuizResponseDto quizResponseDTO = _quizService.updateQuiz(quizId,quizUpdateDto);
        APIResponse<QuizResponseDto> responseDTO = APIResponse
                .<QuizResponseDto>builder()
                .status(SUCCESS)
                .results(quizResponseDTO)
                .build();

        log.info("QuizController::updateQuiz response {}", ValueMapper
                .jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "delete a quiz with specific id")
    @DeleteMapping({"/{quizId}"})
    public ResponseEntity<APIResponse<String>> deleteQuiz(@PathVariable int quizId) {
        log.info("QuizController:deleteQuiz has been executed");
        log.info("QuizController:deleteQuiz with id {}", quizId);

        _quizService.deleteQuizById(quizId);
        APIResponse<String> responseDTO = APIResponse
                .<String>builder()
                .status(SUCCESS)
                .results("Quiz with id " + quizId + " has been deleted successfully")
                .build();

        log.info("QuizController::deleteQuiz response {}", ValueMapper
                .jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}

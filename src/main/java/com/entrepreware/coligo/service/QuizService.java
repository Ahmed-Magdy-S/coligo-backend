package com.entrepreware.coligo.service;

import com.entrepreware.coligo.dto.quiz.QuizCreateDTO;
import com.entrepreware.coligo.dto.quiz.QuizResponseDto;
import com.entrepreware.coligo.dto.quiz.QuizUpdateDto;
import com.entrepreware.coligo.entity.Quiz;
import com.entrepreware.coligo.exception.QuizNotFoundException;
import com.entrepreware.coligo.exception.QuizServiceBusinessException;
import com.entrepreware.coligo.repository.QuizRepository;
import com.entrepreware.coligo.utils.ValueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository _quizRepository;

    public QuizResponseDto createNewQuiz(QuizCreateDTO quizCreateDTO) throws QuizServiceBusinessException {
        QuizResponseDto quizResponseDto;

        try {
            log.info("QuizService:createNewQuiz execution started.");
            Quiz quiz = ValueMapper.convertToEntity(quizCreateDTO);
            log.debug("QuizService:createNewQuiz request parameters {}", ValueMapper.jsonAsString(quizCreateDTO));

            Quiz savedQuizResult = _quizRepository.save(quiz);

            quizResponseDto = ValueMapper.convertToDTO(savedQuizResult);
            log.debug("QuizService:createNewQuiz received response from database {}", ValueMapper.jsonAsString(savedQuizResult));
        } catch (Exception e) {
            log.error("Exception occurred while persisting quiz to database , exception message {}", e.getMessage());
            throw new QuizServiceBusinessException("Exception occurred while create a new quiz");
        }

        log.info("QuizService:createNewQuiz execution ended");
        return quizResponseDto;

    }

    public List<QuizResponseDto> getAllQuizzes() throws QuizServiceBusinessException {

        List<QuizResponseDto> quizResponseDtoList = new ArrayList<>();

        try {
            log.info("QuizService:getAllQuizzes method execution started");
            Iterable<Quiz> quizList = _quizRepository.findAll();
            for (var quiz : quizList) {
                quizResponseDtoList.add(ValueMapper.convertToDTO(quiz));
            }

            log.debug("QuizService:getAllQuizzes retrieved quizzes from database {}", ValueMapper.jsonAsString(quizResponseDtoList));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving quizzes from database , Exception message {}", e.getMessage());
            throw new QuizServiceBusinessException("Exception occurred while fetch all quizzes from Database");
        }
        log.info("QuizService:getAllQuizzes execution ended");
        return quizResponseDtoList;
    }

    public QuizResponseDto getQuizById(int quizId) throws QuizServiceBusinessException {
        QuizResponseDto quizResponseDto;

        try {
            log.info("QuizService:getQuizById execution started");
            Quiz quiz = _quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException("Cannot find the quiz with the id: " + quizId));
            quizResponseDto = ValueMapper.convertToDTO(quiz);
            log.debug("A quiz with id: " + quizId + " has been retrieved successfully from database {}", ValueMapper.jsonAsString(quizResponseDto));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving quiz with id {} from database , Exception message {}", quizId, e.getMessage());
            throw new QuizServiceBusinessException("Error occurred when fetching the quiz with id " + quizId + " from database");
        }

        log.info("QuizService:getQuizById execution ended");
        return quizResponseDto;
    }

    public QuizResponseDto updateQuiz(int quizId, QuizUpdateDto quizUpdateDto) throws QuizServiceBusinessException{
        QuizResponseDto quizResponseDto;
        try {
            log.info("QuizService:updateQuiz execution started");
            Quiz quiz = _quizRepository.findById(quizId).orElseThrow(()->new QuizNotFoundException("Cannot find the quiz with the id " +quizId));
            quiz.setUnit(quizUpdateDto.getUnit());
            quiz.setCourse(quizUpdateDto.getCourse());
            quiz.setUnit(quizUpdateDto.getUnit());
            quiz.setTopic(quizUpdateDto.getTopic());
            quiz.setDueTo(quizUpdateDto.getDueTo());
            _quizRepository.save(quiz);
            quizResponseDto = ValueMapper.convertToDTO(quiz);
            log.debug("A quiz with id {} has been saved successfully {}", quiz.getId(), ValueMapper.jsonAsString(quizResponseDto));
        }
        catch (Exception ex){
            log.error("Error occurred while updating the quiz with the id {}", quizId);
            throw new QuizServiceBusinessException("Failed to update quiz with the id " + quizId);
        }

        log.info("QuizService:updateQuiz execution ended");
        return quizResponseDto;
    }

    public void deleteQuizById(int quizId) throws QuizServiceBusinessException{
        log.info("QuizService:deleteQuizById method has been started");

        try{
            _quizRepository.deleteById(quizId);
            log.debug("A quiz with id {} has been deleted successfully", quizId);
        }
        catch (Exception ex){
            log.error("Exception occurred while trying to remove a quiz from database with the id {}", quizId);
            throw new QuizServiceBusinessException("Error occurred while deleting a quiz from database with the id " + quizId);
        }

        log.info("QuizService:deleteQuizById execution ended");
    }
}


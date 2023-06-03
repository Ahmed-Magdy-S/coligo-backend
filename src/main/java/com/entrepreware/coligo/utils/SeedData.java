package com.entrepreware.coligo.utils;

import com.entrepreware.coligo.entity.Announcement;
import com.entrepreware.coligo.entity.Employee;
import com.entrepreware.coligo.entity.Quiz;
import com.entrepreware.coligo.repository.AnnouncementRepository;
import com.entrepreware.coligo.repository.EmployeeRepository;
import com.entrepreware.coligo.repository.QuizRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedData implements ApplicationRunner {

    private final EmployeeRepository _employeeRepository;
    private final QuizRepository _quizRepository;
    private final AnnouncementRepository _announcementRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //get json data
        log.info("Reading data resources");
        Resource quizResource = resourceLoader.getResource("classpath:seeding_data/quiz.json");
        Resource announcementResource = resourceLoader.getResource("classpath:seeding_data/announcement.json");
        Resource employeeResource = resourceLoader.getResource("classpath:seeding_data/employee.json");

        //get data stream
        log.info("preparing data resources");
        InputStream quizStream = quizResource.getInputStream();
        InputStream announcementStream = announcementResource.getInputStream();
        InputStream employeeStream = employeeResource.getInputStream();

        //get real data
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        log.info("Mapping data resources from json to entities");
        List<Quiz> quizzes = objectMapper.readValue(quizStream, new TypeReference<List<Quiz>>(){});
        List<Employee> employees = objectMapper.readValue(employeeStream, new TypeReference<List<Employee>>(){});
        List<Announcement> announcements = objectMapper.readValue(announcementStream, new TypeReference<List<Announcement>>(){});

        //save to db
        log.info("seeding data to database");
        _quizRepository.saveAll(quizzes);
        _employeeRepository.saveAll(employees);
        _announcementRepository.saveAll(announcements);
        log.info("database has been seeded successfully");
    }
}

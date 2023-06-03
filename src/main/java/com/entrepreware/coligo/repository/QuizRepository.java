package com.entrepreware.coligo.repository;

import com.entrepreware.coligo.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends CrudRepository<Quiz,Integer> {
}

package com.entrepreware.coligo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Quiz {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private int unit;
    private String course;
    private String topic;
    private Date dueTo;


}

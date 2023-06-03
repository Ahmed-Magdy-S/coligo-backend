package com.entrepreware.coligo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name;
    private String department;
}

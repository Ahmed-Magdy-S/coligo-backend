package com.entrepreware.coligo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Announcement {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false,  insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "employee_id")
    private int employeeId;

}

package com.course.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PrePersist
    private void init() {
        setDateCreated(LocalDate.now());
    }

    private String task;
    private LocalDate dueDate;
    private boolean isCompleted;
    private LocalDate dateCompleted;
    private LocalDate dateCreated;


}

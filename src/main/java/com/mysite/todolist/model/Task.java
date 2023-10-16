package com.mysite.todolist.model;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String description;

    TaskStatus status;
    
    LocalDate creationDate;
    
    LocalDate completionDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

}

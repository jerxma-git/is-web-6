package com.mysite.todolist.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.todolist.model.Task;
import com.mysite.todolist.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndCreationDate(User user, LocalDate date);
    List<Task> findByUserAndCompletionDate(User user, LocalDate date);
}

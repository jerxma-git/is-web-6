package com.mysite.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.todolist.model.Task;
import com.mysite.todolist.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}

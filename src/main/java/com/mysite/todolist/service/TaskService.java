package com.mysite.todolist.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mysite.todolist.model.Task;
import com.mysite.todolist.model.TaskStatus;
import com.mysite.todolist.model.User;
import com.mysite.todolist.repository.TaskRepository;
import com.mysite.todolist.repository.UserRepository;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User currentUser = userRepository.findByUsername(currentUsername);
        return currentUser;
    }

    public Task createTask(Task task) {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            task.setUser(currentUser);
            task.setCreationDate(LocalDate.now());
            if (task.getStatus() == null) {
                task.setStatus(TaskStatus.IN_PROGRESS); 
            }
            return taskRepository.save(task);
        }

        return null;
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public boolean markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return false;
        }
        task.setStatus(TaskStatus.COMPLETED);
        task.setCompletionDate(LocalDate.now());
        taskRepository.save(task);
        return true;
    }

    public List<Task> findAll() {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            return taskRepository.findByUser(currentUser);
        }
        return List.of();
    }

    public List<Task> findAllCreatedByDay(LocalDate date) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            return taskRepository.findByUserAndCreationDate(currentUser, date);
        }
        return List.of();
    }

    public List<Task> findAllCompletedByDay(LocalDate date) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            return taskRepository.findByUserAndCompletionDate(currentUser, date);
        }
        return List.of();
    }

    public List<Task> findAllInProgress() {
        return findAll().stream().filter(task -> task.getStatus() == TaskStatus.IN_PROGRESS).toList();
    }

    public List<Task> findAllCompleted() {
        return findAll().stream().filter(task -> task.getStatus() == TaskStatus.COMPLETED).toList();
    }

    public List<Task> findAllCompletedBetween(LocalDate start, LocalDate end) {
        return findAll()
            .stream()
            .filter(task -> task.getStatus() == TaskStatus.COMPLETED 
                && !task.getCompletionDate().isBefore(start)
                && !task.getCompletionDate().isAfter(end)
                ).toList();
    }

    public List<Task> findAllCompletedThisDay() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        return findAllCompletedBetween(start, end);
    }

    public List<Task> findAllCompletedThisWeek() {
        LocalDate start = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = LocalDate.now(); // надеюсь в будущее не умеем глядеть
        return findAllCompletedBetween(start, end);
    }

    public List<Task> findAllCompletedThisMonth() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();
        return findAllCompletedBetween(start, end);
    }

    public List<Task> findAllByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }
}

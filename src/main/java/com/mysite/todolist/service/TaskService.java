package com.mysite.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    // public Task createTask(Task task) {
    //     return taskRepository.save(task);
    // }


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the user by username (you may adjust this based on your user model)
        User currentUser = userRepository.findByUsername(currentUsername);
        return currentUser;
    }

    public Task createTask(Task task) {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            // Set the user as the owner of the task
            task.setUser(currentUser);
            return taskRepository.save(task);
        }
        // Handle the case where the user is not found
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
        taskRepository.save(task);
        return true;
    }

    public List<Task> findAll() {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            // Retrieve tasks associated with the current user
            return taskRepository.findByUser(currentUser);
        }
        return List.of();

    }

    public List<Task> findAllInProgress() {
        return findAll().stream().filter(task -> task.getStatus() == TaskStatus.IN_PROGRESS).toList();
    }

    public List<Task> findAllCompleted() {
        return findAll().stream().filter(task -> task.getStatus() == TaskStatus.COMPLETED).toList();
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

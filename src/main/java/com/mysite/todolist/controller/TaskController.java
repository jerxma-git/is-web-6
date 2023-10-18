package com.mysite.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.todolist.model.Task;
import com.mysite.todolist.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    
    @Operation(summary = "Get a specific task create by the authenticated user by ID")
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    
    @Operation(summary = "Get a list of all tasks created by the authenticated user ")
    @GetMapping("/list/all")
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @Operation(summary = "List all, created by the authenticated user that were marked as completed")
    @GetMapping("/list/completed")
    public List<Task> getCompletedTasks() {
        return taskService.findAllCompleted();
    }

    @Operation(summary = "List all tasks, created by the authenticated user that were completed today")
    @GetMapping("/list/completed/this-day")
    public List<Task> getCompletedThisDayTasks() {
        return taskService.findAllCompletedThisDay();
    }

    @Operation(summary = "List all tasks, created by the authenticated user that were completed this week")
    @GetMapping("/list/completed/this-week")
    public List<Task> getCompletedThisWeekTasks() {
        return taskService.findAllCompletedThisWeek();
    }

    @Operation(summary = "List all tasks, created by the authenticated user that were completed this month")
    @GetMapping("/list/completed/this-month")
    public List<Task> getCompletedThisMonthTasks() {
        return taskService.findAllCompletedThisMonth();
    }


    @Operation(summary = "List all tasks, created by the authenticated user that are now IN_PROGRESSS")
    @GetMapping("/list/in-progress")
    public List<Task> getInProgressTasks() {
        return taskService.findAllInProgress();
    }

    @Operation(summary = "Create a new task as the authenticated user")
    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) { 
        return taskService.createTask(task);
    }

    
    @Operation(summary = "update an existing task by id")
    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updated) {
        Task result = taskService.updateTask(taskId, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete an existing task by its ID")
    @DeleteMapping("delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        boolean deleted = taskService.deleteTask(taskId);

        if (deleted) {
            return ResponseEntity.ok("Task with id " + taskId.toString() + " deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with id " + taskId.toString() + " wasn't found");
    }



    @PostMapping("complete/{taskId}")
    public ResponseEntity<String> completeTask(@PathVariable Long taskId) {
        boolean successful = taskService.markTaskAsCompleted(taskId);
        if (successful) {
            return ResponseEntity.ok("Task " + taskId + " completed successfully");
        } 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to mark the task " + taskId + " as completed.");
    }
}

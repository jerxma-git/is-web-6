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

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;


    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping("/list/all")
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @GetMapping("/list/completed")
    public List<Task> getCompletedTasks() {
        return taskService.findAllCompleted();
    }

    @GetMapping("/list/completed/this-day")
    public List<Task> getCompletedThisDayTasks() {
        return taskService.findAllCompletedThisDay();
    }

    @GetMapping("/list/completed/this-week")
    public List<Task> getCompletedThisWeekTasks() {
        return taskService.findAllCompletedThisWeek();
    }

    @GetMapping("/list/completed/this-month")
    public List<Task> getCompletedThisMonthTasks() {
        return taskService.findAllCompletedThisMonth();
    }



    @GetMapping("/list/in-progress")
    public List<Task> getInProgressTasks() {
        return taskService.findAllInProgress();
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updated) {
        Task existing = taskService.getTask(taskId);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());

        Task updatedTask = taskService.save(existing);

        return ResponseEntity.ok(updatedTask);
    }


    @DeleteMapping("delete/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        // TODO: check for existence
        taskService.deleteTask(taskId);
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

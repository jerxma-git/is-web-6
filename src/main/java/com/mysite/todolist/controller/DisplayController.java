package com.mysite.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.todolist.model.Task;
import com.mysite.todolist.model.User;
import com.mysite.todolist.service.TaskService;
import com.mysite.todolist.service.UserService;

@Controller
public class DisplayController {
    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @GetMapping("/show-tasks")
    public String showTasks(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_user", userService.getCurrentUser());
        return "task_display";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Task> tasks = taskService.findAllInProgress();
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_user", userService.getCurrentUser());
        return "dashboard";
    }

    @GetMapping("/show-users")
    public String showUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("current_user", userService.getCurrentUser());
        return "list-users";
    }
}
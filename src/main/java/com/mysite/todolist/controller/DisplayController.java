package com.mysite.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.todolist.model.DailyStatistics;
import com.mysite.todolist.model.Task;
import com.mysite.todolist.model.User;
import com.mysite.todolist.service.StatisticsService;
import com.mysite.todolist.service.TaskService;
import com.mysite.todolist.service.UserService;

@Controller
public class DisplayController {
    @Autowired
    TaskService taskService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Task> tasks = taskService.findAllInProgress();
        model.addAttribute("tasks", tasks);
        return "dashboard";
    }


    //TODO: remove
    @GetMapping("/show-users")
    public String showUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list-users";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<DailyStatistics> tasks = statisticsService.getWeeklyStatistics();
        model.addAttribute("stats", tasks);
        return "statistics";
    }

    @GetMapping("/registration")
    public String showRegistration(Model model) {
        return "registration";
    }

    @GetMapping("/chat")
    public String showChat(Model model) {
        return "chat";
    }

}

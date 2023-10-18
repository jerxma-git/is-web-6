package com.mysite.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.todolist.model.User;
import com.mysite.todolist.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @Operation(summary = "Creates a user")
    @PostMapping("/create")
    public User createUser(@ModelAttribute User user) {
        return userService.createUser(user);
    }
}

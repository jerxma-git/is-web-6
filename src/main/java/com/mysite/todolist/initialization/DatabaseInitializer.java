package com.mysite.todolist.initialization;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysite.todolist.model.User;
import com.mysite.todolist.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    private final UserRepository userRepository;

    public DatabaseInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        
        User testUser = User.builder()
            .username("testuser")
            .password("pasvord")
            .roles(List.of("ROLE_USER"))
            .build();
        User adminUser = User.builder()
            .username("adminuser")
            .password("pasvord")
            .roles(List.of("ROLE_USER", "DELETE"))
            .build();
        if (userRepository.findByUsername("testuser") != null) {
            userRepository.save(testUser);
        
        }
        if (userRepository.findByUsername("adminuser") != null) {
            userRepository.save(adminUser);        
        }

        // Other initialization tasks can be added here
    }
}
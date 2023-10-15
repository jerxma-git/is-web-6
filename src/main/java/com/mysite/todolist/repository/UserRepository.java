package com.mysite.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.todolist.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);   
}

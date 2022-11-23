package com.project.blogapi.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapi.blogapi.entities.User;

public interface UserRepo  extends JpaRepository<User,Integer>{
    Optional<User> findByEmail(String email);
}

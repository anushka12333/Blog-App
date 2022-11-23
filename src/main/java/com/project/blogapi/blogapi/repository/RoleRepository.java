package com.project.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapi.blogapi.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{
   
}

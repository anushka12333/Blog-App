package com.project.blogapi.blogapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.project.blogapi.blogapi.payloads.ApiResponse;
import com.project.blogapi.blogapi.payloads.UserDto;

import com.project.blogapi.blogapi.services.UserService;
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

  

    //POST-createUser
    @PostMapping("/")
   public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
       UserDto createUserDto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
        
    }
    //PUT-update user
   @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto,@PathVariable("userId" ) Integer  userId ){
       UserDto updateUser=this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updateUser);
    }
 


    //DELETE -delete user
    @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
  
    }
    //GET user-get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users= this.userService.getAllUsers();
        return ResponseEntity.ok(users);
        
    }
    //GET user-get
   
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser (@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
  

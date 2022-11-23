package com.project.blogapi.blogapi.services.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blogapi.blogapi.config.AppConstants;
import com.project.blogapi.blogapi.entities.Role;
import com.project.blogapi.blogapi.entities.User;
import com.project.blogapi.blogapi.exceptions.*;
import com.project.blogapi.blogapi.payloads.UserDto;
import com.project.blogapi.blogapi.repository.RoleRepository;
import com.project.blogapi.blogapi.repository.UserRepo;
import com.project.blogapi.blogapi.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
     private UserRepo userRepo;
     @Autowired
     private ModelMapper modelMapper;
     @Autowired
     private PasswordEncoder passwordEncoder;
     @Autowired
     private RoleRepository roleRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
       User user=userRepo.findById(userId)
       .orElseThrow( ()-> new ResourceNotFoundException("User"," id ",userId));
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setAbout(userDto.getAbout());
       user.setPassword(userDto.getPassword());
       User updateUser=this.userRepo.save(user);
      UserDto userDto1= this.userToDto(updateUser);
       
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
       User user= userRepo.findById(userId)
        .orElseThrow( ()-> new ResourceNotFoundException("User"," id ",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
     List<User> users = this.userRepo.findAll();
     List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
     return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepo.findById(userId)
        .orElseThrow( ()-> new ResourceNotFoundException("User"," id ",userId));
        this.userRepo.delete(user);
    }
    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        
        return userDto;
       
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
      User user= this.modelMapper.map(userDto,User.class);
      user.setPassword(this.passwordEncoder.encode(user.getPassword()));

      //roles
     Role role= this.roleRepository.findById(AppConstants.NORMAL_USER).get();    

     user.getRoles().add(role);
     User newUser=this.userRepo.save(user);
     return this.modelMapper.map(newUser, UserDto.class);
    }
    
}

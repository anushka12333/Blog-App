package com.project.blogapi.blogapi.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogapi.blogapi.Security.JwtTokenHelper;
import com.project.blogapi.blogapi.entities.User;
import com.project.blogapi.blogapi.exceptions.ApiException;
import com.project.blogapi.blogapi.payloads.JwtAuthRequest;
import com.project.blogapi.blogapi.payloads.JwtAuthResponse;
import com.project.blogapi.blogapi.payloads.UserDto;
import com.project.blogapi.blogapi.services.UserService;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerr {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
   @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;
  
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
      this.authenticate(request.getUsername(), request.getPassword());
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
      String token = this.jwtTokenHelper.generateToken(userDetails);  
      JwtAuthResponse response = new JwtAuthResponse();
      response.setToken(token);
      System.out.println("token plz");
      response.setUser(this.mapper.map((User) userDetails, UserDto.class));
      return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
      try {
        this.authenticationManager.authenticate(authenticationToken);
      } catch (BadCredentialsException e) {
        System.out.println("Invalid details");
        throw new ApiException("Invalid username or password !!");
      }
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
         UserDto registerUser=this.userService.registerNewUser(userDto);
         return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
    }



}

package com.project.blogapi.blogapi.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.blogapi.blogapi.entities.User;
import com.project.blogapi.blogapi.exceptions.ResourceNotFoundException;
import com.project.blogapi.blogapi.repository.UserRepo;

@Service
public class CustomUserDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User ", " email : "+ username ,0));
       //loading user from database by username

        return user;
    }

    
}

package com.project.blogapi.blogapi;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.project.blogapi.blogapi.config.AppConstants;
import com.project.blogapi.blogapi.entities.Role;
import com.project.blogapi.blogapi.repository.RoleRepository;


@SpringBootApplication
public class BlogapiApplication implements CommandLineRunner{

	public static void main(String[] args)  {
		SpringApplication.run(BlogapiApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		try{
			Role role= new Role();
			role.setId(AppConstants.NORMAL_USER);
			role.setName("NORMAL_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");
			List<Role> roles=List.of(role,role1);
			List<Role>result=this.roleRepository.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getName());
			});


		}
		catch(Exception e){

		}
		System.out.println(this.passwordEncoder.encode("astuti"));
		
	}


}


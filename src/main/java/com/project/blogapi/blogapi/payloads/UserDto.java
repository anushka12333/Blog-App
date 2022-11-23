package com.project.blogapi.blogapi.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id ;
   @NotEmpty
   @Size(min=4,message = "Username must be of 4 characters !!")
    private String name;
    @NotEmpty
    @Email(message =  "Email address is not valid !!")
    private String email;
    @NotEmpty
    private String about;
    @NotEmpty
    @Size(min=3,max=10, message = "Password must be min of 5 chars and max of 10 chars !!")
    //@Pattern(regex=)
    private String password;

    private Set<RoleDto> roles=new HashSet<>();

    @JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}

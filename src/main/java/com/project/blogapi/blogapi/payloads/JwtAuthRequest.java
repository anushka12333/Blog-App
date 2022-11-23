package com.project.blogapi.blogapi.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;

    private String password;


}

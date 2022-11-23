package com.project.blogapi.blogapi.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException() {

    }
    
    
}

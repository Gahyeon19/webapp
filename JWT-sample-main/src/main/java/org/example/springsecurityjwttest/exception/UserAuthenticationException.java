package org.example.springsecurityjwttest.exception;

import org.springframework.http.HttpStatus;

public class UserAuthenticationException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
    private  String title;
    public UserAuthenticationException(String message, String title){
        this(message , title, HttpStatus.UNAUTHORIZED);//
    }
    public UserAuthenticationException(String message, String title, HttpStatus httpStatus){
        this.message=message;
        this.title=title;
        this.httpStatus=httpStatus;
    }
}

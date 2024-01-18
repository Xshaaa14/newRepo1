package com.blogger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String message;

    public ResourceNotFoundException(String message) {
        super(message); //super keyword will call parent class constructor
    }

    //create an object of this class in service when post is not found, it will throw an exception




}
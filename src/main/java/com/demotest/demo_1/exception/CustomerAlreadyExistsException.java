package com.demotest.demo_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}

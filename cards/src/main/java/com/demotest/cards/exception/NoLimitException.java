package com.demotest.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoLimitException extends RuntimeException{

    public NoLimitException(String msg){
        super(msg);
    }
}
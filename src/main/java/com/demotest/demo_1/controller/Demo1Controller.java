package com.demotest.demo_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo1Controller {

    @GetMapping("sayHello")
    public String sayHello(){
        return "Hello World!";
    }
}

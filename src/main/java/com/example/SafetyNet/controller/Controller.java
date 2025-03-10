package com.example.SafetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class Controller {
    @GetMapping("/h")
    public String sayHello() {
        return "Hello";
    }
}

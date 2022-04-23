package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloWorld {

    @GetMapping("/hello-world")
    public String index() {
        return "Hello, World";
    }
}

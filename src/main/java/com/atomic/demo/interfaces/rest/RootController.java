package com.atomic.demo.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public String handleRootRequest() {
        return "Welcome to the application!"; // or redirect to another endpoint
    }
}

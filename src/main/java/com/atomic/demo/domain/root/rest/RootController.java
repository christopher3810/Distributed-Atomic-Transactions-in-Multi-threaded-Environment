package com.atomic.demo.domain.root.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Root ", description = "Root")
public class RootController {

    @Operation(summary = "Root 요청", description = "Root 요청 처리")
    @GetMapping("/")
    public String handleRootRequest() {
        return "Welcome to the application!"; // or redirect to another endpoint
    }
}

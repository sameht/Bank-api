package com.kobita.banking.health;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthController {

    @GetMapping("api/health")
    public Map<String, String> get(){
        return Map.of(
            "status", "UP",
            "service", "Bank Rest API",
            "timestamp", LocalDateTime.now().toString()
        );
    }
}

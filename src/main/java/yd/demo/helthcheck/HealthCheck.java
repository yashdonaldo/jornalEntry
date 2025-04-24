package yd.demo.helthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    
    @GetMapping("/heath-check")
    public String checkHealth() {
        // Check if the system is healthy
        // For example, check if the database is connected
        // For example, check if the API is responding correctly
        return "System is healthy";
    }
}

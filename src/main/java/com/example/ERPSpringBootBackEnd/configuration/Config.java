package com.example.ERPSpringBootBackEnd.configuration;

import com.example.ERPSpringBootBackEnd.dto.UserDto;
import com.example.ERPSpringBootBackEnd.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.save(new UserDto("Rehnuma", "Reza", "bakbik", "1234", "ADMIN"));
            userService.save(new UserDto("Rehnuma", "Reza", "bakbik22", "1234", "USER"));

        };
    }
}

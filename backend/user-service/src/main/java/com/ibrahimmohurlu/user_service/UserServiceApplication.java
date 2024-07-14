package com.ibrahimmohurlu.user_service;

import com.ibrahimmohurlu.user_service.model.User;
import com.ibrahimmohurlu.user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner userServiceInitData(UserRepository userRepository) {
        return args -> {
            User u = new User();
            u.setEmail("demo@mail.com");
            u.setPassword("secret123");
            userRepository.save(u);
        };
    }
}

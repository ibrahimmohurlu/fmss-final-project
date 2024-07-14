package com.ibrahimmohurlu.user_service.controller;

import com.ibrahimmohurlu.user_service.model.User;
import com.ibrahimmohurlu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String get() {
        return "hello from user service";
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> userByEmail = userService.getUserByEmail(email);
        return userByEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

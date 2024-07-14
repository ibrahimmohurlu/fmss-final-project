package com.ibrahimmohurlu.user_service.controller;

import com.ibrahimmohurlu.user_service.converters.UserPackageResponseDtoConverter;
import com.ibrahimmohurlu.user_service.converters.UserResponseDtoConverter;
import com.ibrahimmohurlu.user_service.dto.UserPackageResponseDto;
import com.ibrahimmohurlu.user_service.dto.UserResponseDto;
import com.ibrahimmohurlu.user_service.model.User;
import com.ibrahimmohurlu.user_service.model.UserPackage;
import com.ibrahimmohurlu.user_service.service.UserService;
import com.ibrahimmohurlu.user_service.utils.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserResponseDto userResponseDto = UserResponseDtoConverter.userToUserResponseDto(optionalUser.get());
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/packages")
    public ResponseEntity<List<UserPackageResponseDto>> getUserPackages(@RequestHeader("Authorization") String authorizationHeader) {

        String userEmail = AuthorizationUtils.getUsernameFromAuthHeader(authorizationHeader);
        Optional<User> optionalUser = userService.getUserByEmail(userEmail);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<UserPackageResponseDto> userPackages = UserPackageResponseDtoConverter.UserPackageListToUserPackageDtoList(user.getUserPackages());
        return ResponseEntity.ok(userPackages);

    }

    @GetMapping("/packages/active")
    public ResponseEntity<List<UserPackageResponseDto>> getUserActivePackages(@RequestHeader("Authorization") String authorizationHeader) {
        String userEmail = AuthorizationUtils.getUsernameFromAuthHeader(authorizationHeader);
        List<UserPackage> activePackages = userService.getActiveUserPackagesByEmail(userEmail);
        List<UserPackageResponseDto> activePackagesDto = UserPackageResponseDtoConverter.UserPackageListToUserPackageDtoList(activePackages);
        return ResponseEntity.ok(activePackagesDto);
    }
}
